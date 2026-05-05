# 🍽️ Resturant Web Application (RWA)

A **Spring Boot REST API** for managing a food ordering system.
This project demonstrates real-world backend concepts like **entity relationships, DTO mapping, validation, and business logic handling**.

---

## 🚀 Features

* 👤 Customer Management
* 🍔 Menu Item Management
* 🏪 Restaurant Management
* 📦 Order Placement (with multiple items)
* 💳 Payment Handling
* 📊 Order Status Tracking
* 🔍 Search & Filter APIs

---

## 🧱 Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA (Hibernate)**
* **MySQL**
* **Maven**
* **Lombok (optional)**

---

## 📂 Project Structure

```
com.project.foms
│
├── controller        # REST Controllers
├── service           # Business logic
├── repository        # JPA Repositories
├── entity            # Database entities
├── dto               # Request & Response DTOs
├── enums             # Enum classes
├── exception         # Global exception handling
```

---

## 🧠 Core Concepts Implemented

* ✅ One-to-Many & Many-to-One mappings
* ✅ DTO pattern (Request/Response separation)
* ✅ Validation using `@Valid`
* ✅ Transaction management (`@Transactional`)
* ✅ Custom query methods in JPA
* ✅ Exception handling with `ResponseStatusException`

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

```bash
git clone https://github.com/rajshres11/foms.git
cd foms
```

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foms
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

---

## 📌 API Endpoints (Sample)

### 🧾 Order APIs

| Method | Endpoint               | Description            |
| ------ | ---------------------- | ---------------------- |
| POST   | `/order/place`         | Place a new order      |
| GET    | `/order/{id}`          | Get order by ID        |
| GET    | `/order/customer/{id}` | Get orders by customer |

---

## 🧾 Sample Request (Place Order)

```json
{
  "customerId": 1,
  "items": [
    { "itemId": 2, "quantity": 2 },
    { "itemId": 3, "quantity": 1 }
  ]
}
```

---

## 🧾 Sample Response

```json
{
  "orderId": 101,
  "totalAmount": 700,
  "orderStatus": "CREATED",
  "items": [
    {
      "itemId": 2,
      "itemName": "Burger",
      "price": 200,
      "quantity": 2,
      "subTotal": 400
    }
  ]
}
```

---

## ⚠️ Important Notes

* `application.properties` is ignored using `.gitignore` (for security)
* `Order` table renamed to `orders` to avoid SQL keyword conflict
* Validation is handled at both DTO and Service level

---

## 🔥 Future Improvements

* JWT Authentication 🔐
* Role-based access (Admin/User)
* Payment gateway integration 💳
* Pagination & Sorting
* Docker support

---

## 👨‍💻 Author

**Shresth Raj**

---

## ⭐ If you like this project

Give it a star ⭐ on GitHub!
