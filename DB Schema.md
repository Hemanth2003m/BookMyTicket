

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'user') DEFAULT 'user',
    type ENUM('VIP', 'Regular', 'Senior', 'Differently-abled') DEFAULT 'Regular'
);



CREATE TABLE trains (
    train_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL
);


CREATE TABLE compartments (
    compartment_id INT AUTO_INCREMENT PRIMARY KEY,
    train_id VARCHAR(20),
    class VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE
);


CREATE TABLE seats (
    seat_id VARCHAR(20) PRIMARY KEY,
    compartment_id INT,
    seat_number VARCHAR(10),
    is_booked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (compartment_id) REFERENCES compartments(compartment_id) ON DELETE CASCADE
);


CREATE TABLE bookings (
    booking_id VARCHAR(20) PRIMARY KEY,
    user_id INT,
    train_id VARCHAR(20),
    seat_id VARCHAR(20),
    status ENUM('Confirmed', 'Cancelled', 'Rescheduled', 'RAC', 'Waitlist') DEFAULT 'Confirmed',
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id) ON DELETE CASCADE
);



CREATE TABLE waitlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    train_id VARCHAR(20),
    requested_class VARCHAR(50),
    priority INT,
    time_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE
);



INSERT INTO users (username, password, role, type) VALUES
('admin1', 'adminpass', 'admin', 'Regular'),
('vipuser1', 'vip123', 'user', 'VIP'),
('regularuser1', 'reg123', 'user', 'Regular'),
('senioruser1', 'senior123', 'user', 'Senior');


INSERT INTO trains (train_id, name, source, destination, departure_time) VALUES
('T001', 'Express Line', 'City A', 'City B', '08:00:00'),
('T002', 'Coastal Cruiser', 'City C', 'City D', '12:30:00');


INSERT INTO compartments (train_id, class, total_seats) VALUES
('T001', 'First Class', 20),
('T001', 'Sleeper', 50),
('T002', 'First Class', 15),
('T002', 'Sleeper', 40);


-- Seats for Compartment 1
INSERT INTO seats (seat_id, compartment_id, seat_number) VALUES
('S001', 1, '1A'),
('S002', 1, '1B'),
('S003', 1, '2A'),
('S004', 1, '2B');

-- Seats for Compartment 2
INSERT INTO seats (seat_id, compartment_id, seat_number) VALUES
('S005', 2, '3A'),
('S006', 2, '3B'),
('S007', 2, '4A'),
('S008', 2, '4B');

-- Seats for Compartment 3 and 4 similarly
INSERT INTO seats (seat_id, compartment_id, seat_number) VALUES
('S009', 3, '1A'),
('S010', 3, '1B');

INSERT INTO seats (seat_id, compartment_id, seat_number) VALUES
('S011', 4, '2A'),
('S012', 4, '2B');


INSERT INTO bookings (booking_id, user_id, train_id, seat_id, status) VALUES
('B001', 2, 'T001', 'S001', 'Confirmed'),
('B002', 3, 'T001', 'S002', 'Confirmed'),
('B003', 4, 'T002', 'S009', 'Confirmed');


INSERT INTO waitlist (user_id, train_id, requested_class, priority) VALUES
(3, 'T001', 'Sleeper', 2),
(4, 'T001', 'First Class', 1),
(3, 'T002', 'First Class', 1);
