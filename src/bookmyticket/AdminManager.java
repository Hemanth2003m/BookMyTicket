package bookmyticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class AdminManager {
    private Connection conn;

    public AdminManager() {
        try {
//        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyticket", "kowshik", "Kowshik@0105");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getTrainList() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT train_id, name FROM trains");
            Vector<String> trains = new Vector<>();
            while (rs.next()) {
                trains.add(rs.getString("train_id"));
            }
            return trains.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public DefaultTableModel getBookingsForTrain(String trainId) {
        String[] columns = {"Booking ID", "Username", "Train ID", "Seat ID", "Status", "Timestamp"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT b.booking_id, u.username, b.train_id, b.seat_id, b.status, b.timestamp " +
                "FROM bookings b JOIN users u ON b.user_id = u.id WHERE b.train_id = ?");
            ps.setString(1, trainId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getString("booking_id"),
                    rs.getString("username"),
                    rs.getString("train_id"),
                    rs.getString("seat_id"),
                    rs.getString("status"),
                    rs.getTimestamp("timestamp")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public void cancelBooking(String bookingId) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE bookings SET status = 'Cancelled' WHERE booking_id = ?");
            ps.setString(1, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rescheduleBooking(String bookingId, String newTrainId, String newSeatId) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE bookings SET train_id = ?, seat_id = ?, status = 'Rescheduled' WHERE booking_id = ?");
            ps.setString(1, newTrainId);
            ps.setString(2, newSeatId);
            ps.setString(3, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showWaitlist(String trainId) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM waitlist WHERE train_id = ? ORDER BY priority ASC");
            ps.setString(1, trainId);
            ResultSet rs = ps.executeQuery();

            StringBuilder waitlist = new StringBuilder("Waitlist / RAC for Train " + trainId + ":\n");
            while (rs.next()) {
                waitlist.append("User ID: ").append(rs.getString("user_id"))
                        .append(", Priority: ").append(rs.getInt("priority"))
                        .append(", Added on: ").append(rs.getTimestamp("time_added"))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, waitlist.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
