import java.io.*;
import java.util.Scanner;

/** Order - data model for one customer order. */
class Order {
    private String orderId;
    private String customer;
    private String dish;
    private int quantity;

    Order(String orderId, String customer, String dish, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.dish = dish;
        this.quantity = quantity;
    }

    String getOrderId()  { return orderId; }
    String getCustomer() { return customer; }
    String getDish()     { return dish; }
    int getQuantity()    { return quantity; }

    public String toString() {
        return orderId + " | " + customer + " | " + dish + " x" + quantity;
    }
}

/** OrderQueue - FIFO queue implemented with a singly linked list. */
class OrderQueue {
    private class Node {
        Order data;
        Node next;
        Node(Order data) { this.data = data; }
    }

    private Node front, rear;
    private int size;

    void enqueue(Order o) {
        Node n = new Node(o);
        if (rear == null) { front = rear = n; }
        else { rear.next = n; rear = n; }
        size++;
    }

    Order dequeue() {
        if (front == null) return null;
        Order o = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return o;
    }

    boolean remove(String id) {
        Node prev = null, cur = front;
        while (cur != null) {
            if (cur.data.getOrderId().equalsIgnoreCase(id)) {
                if (prev == null) front = cur.next; else prev.next = cur.next;
                if (cur == rear) rear = prev;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    Order[] toArray() {
        Order[] arr = new Order[size];
        Node cur = front;
        for (int i = 0; i < size; i++) { arr[i] = cur.data; cur = cur.next; }
        return arr;
    }

    boolean isEmpty() { return size == 0; }
    int size()        { return size; }
}

/** FileHandler - loads and saves orders in orders.txt. */
class FileHandler {
    private static final String FILE_NAME = "orders.txt";

    static void load(OrderQueue q) {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length == 4) {
                    q.enqueue(new Order(p[0], p[1], p[2], Integer.parseInt(p[3])));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not read " + FILE_NAME + ": " + e.getMessage());
        }
    }

    static void save(OrderQueue q) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Order o : q.toArray()) {
                pw.println(o.getOrderId() + "|" + o.getCustomer() + "|"
                        + o.getDish() + "|" + o.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Could not save " + FILE_NAME + ": " + e.getMessage());
        }
    }
}

/** RestaurantOrderQueue - main class with the menu-driven console. */
public class RestaurantOrderQueue {
    private static OrderQueue queue = new OrderQueue();
    private static Scanner sc = new Scanner(System.in);
    private static int nextId = 101;

    public static void main(String[] args) {
        FileHandler.load(queue);
        setNextId();
        int choice;
        do {
            showMenu();
            choice = readInt();
            switch (choice) {
                case 1: placeOrder();   break;
                case 2: serveNext();    break;
                case 3: viewPending();  break;
                case 4: cancelOrder();  break;
                case 0: break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        FileHandler.save(queue);
        System.out.println("Orders saved to orders.txt. Goodbye!");
    }

    private static void showMenu() {
        System.out.println("\n===== RESTAURANT ORDER QUEUE =====");
        System.out.println("1. Place Order");
        System.out.println("2. Serve Next Order");
        System.out.println("3. View Pending Orders");
        System.out.println("4. Cancel Order by ID");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void setNextId() {
        for (Order o : queue.toArray()) {
            int n = Integer.parseInt(o.getOrderId().substring(1));
            if (n >= nextId) nextId = n + 1;
        }
    }

    private static void placeOrder() {
        System.out.print("Customer: ");
        String customer = sc.nextLine().trim();
        System.out.print("Dish: ");
        String dish = sc.nextLine().trim();
        System.out.print("Quantity: ");
        int qty = readInt();
        if (qty < 1) {
            System.out.println("Invalid quantity. Quantity must be at least 1.");
            return;
        }
        if (customer.isEmpty() || dish.isEmpty()) {
            System.out.println("Customer name and dish cannot be empty.");
            return;
        }
        Order o = new Order("O" + nextId++, customer, dish, qty);
        queue.enqueue(o);
        System.out.println("Order placed. ID: " + o + " | Position: " + queue.size());
    }

    private static void serveNext() {
        Order o = queue.dequeue();
        if (o == null) {
            System.out.println("No pending orders to serve.");
            return;
        }
        System.out.println("Order " + o.getOrderId() + " served. Pending orders: " + queue.size());
    }

    private static void viewPending() {
        Order[] list = queue.toArray();
        System.out.println("Pending: " + list.length);
        for (int i = 0; i < list.length; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    private static void cancelOrder() {
        System.out.print("Enter Order ID to cancel: ");
        String id = sc.nextLine().trim();
        if (queue.remove(id)) {
            System.out.println("Order " + id.toUpperCase() + " cancelled. Pending orders: " + queue.size());
        } else {
            System.out.println("Order ID not found.");
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
