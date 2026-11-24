🍽️ Restaurant Order Queue Management System

A Java-based Restaurant Order Queue Management System that manages customer food orders using the Queue Data Structure.

This project demonstrates how restaurants can efficiently manage incoming orders, process them in First Come First Serve (FCFS) order, and maintain an organized order management system.

---

📌 Project Overview

In a restaurant, customer orders are received continuously. Managing these orders manually can be difficult.

This project uses the Queue Data Structure to manage restaurant orders efficiently.

The system follows the FIFO (First In, First Out) principle, meaning the first order placed is the first order processed.

---

🚀 Features

- Add new customer orders.
- Process the next pending order.
- Display all pending orders.
- View the next order in the queue.
- Maintain order sequence.
- Remove completed orders from the queue.
- Menu-driven interface.
- Efficient order management.

---

🧠 Data Structure Used

Queue (FIFO)

Queue follows the First In, First Out (FIFO) principle.

The order that enters first will be processed first.

Queue Operations

Operation| Description
Enqueue| Add a new restaurant order
Dequeue| Process and remove the first order
Peek| View the next pending order
Display| Show all pending orders
isEmpty| Check whether the queue is empty

---

🛠️ Technologies Used

- Programming Language: Java
- IDE: Visual Studio Code
- Data Structure: Queue
- Version Control: Git
- Repository Hosting: GitHub

---

📂 Project Structure

Restaurant-Order-Queue/
│
├── RestaurantOrderQueue.java
├── README.md
└── .gitignore

---

⚙️ Installation and Setup

Step 1: Clone the Repository

git clone https://github.com/YOUR-USERNAME/Restaurant-Order-Queue.git

Step 2: Open the Project Folder

cd Restaurant-Order-Queue

Step 3: Compile the Java Program

javac RestaurantOrderQueue.java

Step 4: Run the Program

java RestaurantOrderQueue

---

📋 Sample Menu

===== RESTAURANT ORDER QUEUE SYSTEM =====

1. Add New Order
2. Process Next Order
3. View Next Order
4. Display All Orders
5. Exit

Enter your choice:

---

📊 Example

Suppose three customers place orders:

Order ID| Customer| Food Item
101| Rahul| Pizza
102| Aman| Burger
103| Priya| Pasta

Queue Representation

FRONT                         REAR
  |                             |
  v                             v
[101]  --->  [102]  --->  [103]
 Pizza       Burger      Pasta

When the restaurant processes an order, Order 101 will be processed first.

After processing:

FRONT                  REAR
  |                      |
  v                      v
[102]  --->  [103]
 Burger      Pasta

---

🎯 Learning Outcomes

Through this project, we learn:

- Understanding Queue Data Structure.
- Implementing FIFO operations in Java.
- Using Enqueue and Dequeue operations.
- Managing restaurant orders.
- Applying Data Structures to real-world problems.
- Developing menu-driven Java applications.

---

🔮 Future Enhancements

- Add customer billing system.
- Add food menu and pricing.
- Implement Priority Queue for urgent orders.
- Add order status tracking.
- Add graphical user interface (GUI).
- Store order history in a database.
- Add separate queues for dine-in and takeaway orders.

---

👨‍💻 Author

Priyanshu

Java | Data Structures | Queue Management

---

⭐ Support

If you find this project useful, please give this repository a ⭐ on GitHub!

Made with ❤️ using Java