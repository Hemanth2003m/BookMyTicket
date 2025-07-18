## 👥 Team Structure & Work Distribution (Train Ticket Booking System)

---

### 👤 Task 1 – **Authentication & User Module**
Assigned to: Hemanth2003m
- Design Login/Register Swing UI
- Handle user roles (Admin, Regular, Senior Citizen, Differently-abled)
- Connect to database for authentication via JDBC
- Validate user input and manage session flow

---

### 👤 Task 2 – **Train & Route Management**
Assigned to: saiprabhakar08
- Train creation form and route definition (Admin view)
- Display available trains by source → destination (User view)
- Store train, class, and route data in the database
- Link train details to compartments and seats

---

### 👤 Task 3 – **Compartment & Seat Structure (Tree Data Structure)**
Assigned to: koppisettiBhardwajsai
- Build hierarchical structure: Train → Class → Compartment → Berths
- Implement tree traversal for seat assignment and search
- Map berth nodes to pricing and availability
- Create reusable helper methods for traversal

---

### 👤 Task 4 – **Booking Logic & Priority Queue**
Assigned to: Nithishsrisatya
- Implement booking request object with passenger type and timestamp
- Use `PriorityQueue` to manage bookings (Senior > Differently-abled > General)
- Create `BookingRequest.java` and `BookingManager.java`
- Use multithreading to simulate real-time booking conflicts

---

### 👤 Task 5 – **Seat Availability & Status Tracking**
Assigned to: PRajesh999
- Use `HashMap` to track seat availability per train/class/berth
- Update and synchronize seat status during booking/cancellation
- Display current availability in real time within the GUI
- Sync with seat tree structure

---

### 👤 Task 6 – **Berth Recommendation System**
Assigned to: sai1234-png
- Implement `SeatRecommendationEngine.java`
- Suggest optimal berths (e.g., lower berth for senior citizens)
- Use graph/search algorithms to find best-fit seats
- Provide integration into seat selection UI

---

### 👤 Task 7 – **Waiting List & RAC Management**
Assigned to: 22p31a05e6
- Implement queue-based waiting list logic
- Simulate RAC (Reservation Against Cancellation) handling
- Auto-promote users from waitlist when cancellations occur
- Create `WaitlistManager.java` and RAC view UI

---

### 👤 Task 8 – **Database Design & Integration**
Assigned to: SudheerPabolu
- Design ER model and SQL schema for trains, routes, seats, users, bookings
- Implement `DatabaseManager.java` for all JDBC operations
- Handle data consistency and rollback logic
- Provide reusable database utility functions

---

### 👤 Task 9 – **Admin Dashboard & Control Panel**
Assigned to: kowshikjallipalli0105
- Build a dashboard UI for Admin to:
  - View all trains and bookings
  - Manage cancellations and seat releases
  - See occupancy statistics, waitlists
- Integrate with database and booking modules

---

### 👤 Task 10 – **Payment Simulation & Ticket Summary**
Assigned to: prasanthkarthik25305
- Simulate payment confirmation flow (dummy payment gateway)
- Handle payment success/failure rollback
- Generate printable ticket summary/receipt
- Display confirmation UI with booking details

---

## 📦 Integration Tasks (Shared Responsibilities)
- Merge individual Swing UIs with backend logic
- Ensure thread-safe booking logic (seat locking, updates)
- Perform unit and integration testing
- Maintain consistent UI design and UX
- Weekly integration review and merge planning

---

## 📋 Files and Components per Task

| Task | File(s) to Build |
|--------|------------------|
| 1 | `LoginForm.java`, `User.java`, `AuthManager.java` |
| 2 | `TrainForm.java`, `Train.java`, `RouteManager.java` |
| 3 | `CompartmentNode.java`, `SeatTree.java`, `Seat.java` |
| 4 | `BookingRequest.java`, `BookingManager.java` |
| 5 | `SeatMap.java`, `SeatAvailabilityManager.java` |
| 6 | `SeatRecommendationEngine.java` |
| 7 | `WaitlistManager.java`, `RACQueue.java` |
| 8 | `DatabaseManager.java`, SQL Scripts |
| 9 | `AdminDashboard.java`, `AdminManager.java` |
| 10 | `PaymentProcessor.java`, `TicketSummary.java` |

---

> ✨ Each module owner must write and test their components independently before system integration. Shared components like `SeatTree.java` and `DatabaseManager.java` must follow team-agreed interfaces and design contracts.

