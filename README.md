# 🚆 Real-Time Train Ticket Booking System

A desktop-based real-time train ticket booking system supporting multiple routes, train classes (e.g., Sleeper, AC, General), and priority-based seat allocation. Built using **Java Swing** and developed in **NetBeans IDE**.

---

## 📌 Project Synopsis

This system enables real-time, concurrent train ticket bookings with hierarchical train compartments and intelligent seat recommendations. Senior citizens, differently-abled passengers, and VIP users get booking priority. Seat availability is shown and updated in real-time using efficient data structures and algorithms.

---

## 🛠️ Tech Stack

### Frontend (GUI):
- **Java Swing** – GUI framework
- **NetBeans (ONTL)** – Development IDE

### Backend / Core:
- **Java (OOP + Collections Framework)**
- **Multithreading** – For concurrent booking processing
- **Java Sockets (optional)** – For real-time server-client simulation

### Database:
- **MySQL / Oracle DB**
- **JDBC** – Java database connection

### Other Tools:
- **Java Logging API** – For audit logs
- **Java Serialization (optional)** – For object persistence
- **Timer / Thread.sleep** – For delay simulation

---

## ✅ Requirements

### Functional Requirements
- **User Module**
  - Register/Login
  - Search trains by source-destination
  - Book tickets in categories (AC, Sleeper, General)
- **Booking Logic**
  - Seat availability check per train/class
  - Priority-based booking queue (Senior Citizen > Differently-abled > General)
  - Waiting list and RAC (Reservation Against Cancellation)
  - Booking confirmation and payment simulation
- **Admin Module**
  - Add trains, define routes and compartments
  - View bookings and manage cancellations
- **Seat Recommendation**
  - Suggest best available seats based on preference (lower berth, AC class, etc.)

---

## 🧩 Components to Build

### Swing UI Screens
- Login / Registration
- Train Search and List View
- Seat Selection UI (Class-wise view)
- Booking Confirmation Popup
- Admin Dashboard
- Waitlist and RAC Notification

---

### Java Classes
- `User.java` – User data model
- `Train.java` – Train details (number, route, compartments)
- `Compartment.java` – Class-wise compartments (AC, Sleeper, etc.)
- `Seat.java` – Seat ID, class, status
- `BookingRequest.java` – Request with timestamp & priority
- `BookingManager.java` – Handles booking queues and seat locking
- `TrainRoute.java` – Source → Destination mapping
- `DatabaseManager.java` – JDBC operations
- `AdminManager.java` – Admin actions
- `SeatRecommendationEngine.java` – Suggests optimal seats

---

### Core Data Structures
- `PriorityQueue<BookingRequest>` – Priority-based ticket booking
- `HashMap<String, Boolean>` – Seat availability check
- `Tree` – Train → Class → Compartment → Seat
- `Queue<BookingRequest>` – Waiting list and RAC

---

## 🗃️ Database Schema

### Tables
- `users`
  - `id`, `username`, `password`, `role` (admin/user), `category` (senior citizen, general)
- `trains`
  - `train_id`, `name`, `source`, `destination`, `departure_time`, `arrival_time`
- `compartments`
  - `compartment_id`, `train_id`, `class` (AC/Sleeper), `total_seats`
- `seats`
  - `seat_id`, `compartment_id`, `number`, `status`, `berth_type` (lower/upper)
- `bookings`
  - `booking_id`, `user_id`, `train_id`, `seat_id`, `status`, `timestamp`
- `waitlist`
  - `user_id`, `train_id`, `requested_class`, `priority`, `time_added`

---

## ⚙️ Algorithms Implemented
- **Heap (Priority Queue)** – Priority ticket processing
- **Tree Traversal** – Class-wise seat assignment logic
- **Graph Search (optional)** – Route planning or dynamic train linking
- **Sorting** – For berth/seat recommendations
- **Load Balancing (Threading)** – Handling concurrent bookings per train

---

## 🧪 Future Enhancements
- Real-time sync using Java RMI or WebSocket
- Integration with IRCTC-like APIs
- QR Code-based E-tickets
- Cancellation and refund workflow
- Mobile view using JavaFX (optional)

---

## 🧑‍💻 Contributors
- wait for the reveal of developers

---

## 📄 License
This project is open source and available under the [MIT License](LICENSE).
