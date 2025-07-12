# 🎟️ Real-Time Ticket Booking System

A desktop-based real-time ticket booking system for multiple events with priority-based booking and intelligent seat management. Built using **Java Swing** and developed in **NetBeans IDE**.

---

## 📌 Project Synopsis

This system enables real-time, concurrent ticket bookings for multiple events with a hierarchical venue structure and intelligent seat recommendations. VIP and early bird users get booking priority. Seat availability is shown and updated in real-time using custom data structures and algorithms.

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
  - Browse events
  - Book tickets with category (VIP, Regular)
- **Booking Logic**
  - Seat availability check
  - Priority-based booking queue
  - Waiting list management
  - Booking confirmation and payment simulation
- **Admin Module**
  - Manage events/seats
  - View bookings and waiting lists
- **Seat Recommendation**
  - Suggest best available seats by preference

---

## 🧩 Components to Build

### Swing UI Screens
- Login / Registration
- Event Browser
- Seat Selection UI
- Booking Confirmation Popup
- Admin Dashboard
- Waitlist Notification

---

### Java Classes
- `User.java` – User data model
- `Event.java` – Event details
- `Seat.java` – Seat status, price, row info
- `BookingRequest.java` – Request with timestamp & priority
- `BookingManager.java` – Handles booking queues and seat locking
- `SeatTree.java` – Tree-based seat hierarchy
- `DatabaseManager.java` – JDBC operations
- `AdminManager.java` – Admin actions
- `SeatRecommendationEngine.java` – Suggests best-fit seats

---

### Core Data Structures
- `PriorityQueue<BookingRequest>` – For booking priority handling
- `HashMap<String, Boolean>` – For seat availability lookup
- `Custom Tree` – Venue > Section > Row > Seat
- `Queue<BookingRequest>` – For waiting list

---

## 🗃️ Database Schema

### Tables
- `users`
  - `id`, `username`, `password`, `role` (admin/user), `type` (VIP/Regular)
- `events`
  - `id`, `name`, `date`, `time`, `venue`
- `seats`
  - `seat_id`, `event_id`, `row`, `number`, `price`, `is_booked`
- `bookings`
  - `booking_id`, `user_id`, `event_id`, `seat_id`, `status`, `timestamp`
- `waitlist`
  - `user_id`, `event_id`, `requested_seat`, `priority`, `time_added`

---

## ⚙️ Algorithms Implemented
- **Heap (Priority Queue)** – VIP / early access booking handling
- **Graph/Tree Traversal** – For intelligent seat recommendations
- **Sorting** – For price or proximity-based seat ordering
- **Load Balancing Simulation** – Booking queue management using threads

---

## 🧪 Future Enhancements
- Real-time sync using Java RMI or WebSocket
- Payment Gateway Integration
- QR Code-based E-tickets
- Admin Reporting and Analytics Dashboard

---

## 🧑‍💻 Contributors
- Wait for the reveal of the contributors

---

## 📄 License
This project is open source and available under the [MIT License](LICENSE).

