## 👥 Team Structure & Work Distribution

### 👤 Task 1 – **Authentication & User Module**
- Design Login/Register Swing UI
- Handle user roles (Admin, VIP, Regular)
- Connect to database for authentication via JDBC
- Validate user input and session handling

---

### 👤 Task 2 – **Event Management**
- Event creation and listing forms (Admin view)
- Event browsing interface (User view)
- Store event data in database
- Link event data to seat maps and bookings

---

### 👤 Task 3 – **Seat Structure (Tree Data Structure)**
- Build hierarchical venue structure (Section → Row → Seat)
- Implement tree traversal for seat availability
- Map seat nodes to pricing and availability status
- Create helper methods for seat rendering

---

### 👤 Task 4 – **Booking Logic & Priority Queue**
- Implement booking request with priority
- Use `PriorityQueue` to manage bookings (VIP > Early Bird > Regular)
- Create `BookingRequest.java` and `BookingManager.java`
- Integrate multithreading for concurrent request handling

---

### 👤 Task 5 – **Seat Availability & HashMap**
- Design real-time seat lookup using `HashMap`
- Update seat status dynamically
- Display seat availability in the GUI
- Sync data with the tree and booking system

---

### 👤 Task 6 – **Seat Recommendation System**
- Create `SeatRecommendationEngine.java`
- Suggest seats based on user preferences (price, location)
- Implement graph/search algorithms for best-fit matching
- Provide UI integration for recommended seats

---

### 👤 Task 7 – **Waiting List Management**
- Implement `Queue` for managing waitlisted users
- Handle auto-promotion when a seat becomes available
- Update seat status and notify users via UI
- Create `WaitlistManager.java`

---

### 👤 Task 8 – **Database Integration**
- Design ER model and implement schema in MySQL/Oracle
- Write `DatabaseManager.java` for CRUD operations
- Handle bookings, seat status, user info, and events
- Provide connection pooling and error handling

---

### 👤 Task 9 – **Admin Dashboard**
- Build dashboard UI using Swing
- Display booking logs, waitlists, and occupancy stats
- Enable admin to cancel/reschedule bookings
- Integrate with `BookingManager` and database

---

### 👤 Task 10 – **Payment & Confirmation Module**
- Simulate payment process (dummy gateway)
- Handle rollback on booking failure
- Generate ticket summary
- Provide booking confirmation popup (receipt-style)

---

## 📦 Integration Tasks (Shared Responsibilities)
- Merge Swing UIs with backend logic
- Coordinate thread-safe operations (seat locking, updates)
- Perform system testing (unit + integration)
- Handle UI consistency, look & feel
- Weekly review and conflict resolution

---

## 📋 Files and Components per Task

| Task | File(s) to Build |
|--------|------------------|
| 1 | `LoginForm.java`, `User.java`, `AuthManager.java` |
| 2 | `EventForm.java`, `Event.java`, `EventManager.java` |
| 3 | `SeatNode.java`, `SeatTree.java` |
| 4 | `BookingRequest.java`, `BookingManager.java` |
| 5 | `SeatMap.java`, `SeatAvailabilityManager.java` |
| 6 | `SeatRecommendationEngine.java` |
| 7 | `WaitlistManager.java`, `WaitlistQueue.java` |
| 8 | `DatabaseManager.java`, SQL scripts |
| 9 | `AdminDashboard.java`, `AdminManager.java` |
| 10 | `PaymentProcessor.java`, `TicketSummary.java` |

---

> ✨ Each Task should unit test their module before integration. Shared components like `DatabaseManager.java` and `SeatTree.java` must follow agreed interfaces.

