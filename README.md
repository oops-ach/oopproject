## 🏦 Online Store System

### 📌 Project Overview
This **Online Store System** is a Java-based application designed to manage product listings, handle user accounts, process orders, and maintain secure transactions. It provides functionalities for users to register, log in, browse products, add items to the cart, and complete purchases. The system utilizes a **PostgreSQL** database for efficient data storage and retrieval.

---

### 🔧 Features

#### 📄 User Management
- User registration with name, surname, email, password, and phone number.
- Secure login authentication.
- Password reset and change functionality.
- Profile update options (name, surname, phone number).
- User deletion feature.

#### 🛋️ Product & Order Management
- View all products and filter by categories.
- Add products to cart and manage cart items.
- Process orders with payment integration.
- Automatic stock update after purchase.

#### 🔒 Security
- Password hashing for secure storage.
- Email uniqueness check to prevent duplicate registrations.
- Validation for phone numbers and other user inputs.
- Secured endpoints with role-based access (Admin, Manager, User).

---

### 📁 Project Structure
```
OnlineStore/src
├── models.Main.java
├── models.User.java
├── models.Product.java
├── models.Order.java
├── Interfaces.IUserService.java
├── Interfaces.IProductService.java
├── Implementation.UserServiceImpl.java
├── Implementation.ProductServiceImpl.java
├── Repository.UserRepository.java
├── Repository.ProductRepository.java
├── Repository.OrderRepository.java
├── Controller.UserController.java
├── Controller.ProductController.java
└── README.md
```

---

### 🛠️ Technologies Used
- **Java** (Core logic & OOP principles)
- **JDBC** (Java Database Connectivity) for database interaction
- **PostgreSQL** as the relational database

---

### 🔐 Security Implementations
- Passwords are securely hashed before storage.
- Unique email checks prevent duplicate accounts.
- Validation ensures correct phone number formats and secure user data handling.
- Role-based access control for different user functionalities.


