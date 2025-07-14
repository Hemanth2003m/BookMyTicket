package bookmyticket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class AdminDashboard extends JFrame {

    private JTable bookingTable;
    private JButton refreshButton, cancelButton, rescheduleButton, loadWaitlistButton;
    private JComboBox<String> trainSelector;
    private AdminManager adminManager;

    public AdminDashboard() {
        adminManager = new AdminManager();

        setTitle("Admin Dashboard - Train Booking System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Train Selector
        trainSelector = new JComboBox<>(adminManager.getTrainList());
        trainSelector.addActionListener(e -> loadBookings());

        // Booking Table
        bookingTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(bookingTable);

        // Buttons
        refreshButton = new JButton("Refresh Bookings");
        cancelButton = new JButton("Cancel Booking");
        rescheduleButton = new JButton("Reschedule Booking");
        loadWaitlistButton = new JButton("View Waitlist/RAC");

        refreshButton.addActionListener(e -> loadBookings());
        cancelButton.addActionListener(e -> cancelSelectedBooking());
        rescheduleButton.addActionListener(e -> rescheduleBooking());
        loadWaitlistButton.addActionListener(e -> showWaitlist());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Train:"));
        topPanel.add(trainSelector);
        topPanel.add(refreshButton);
        topPanel.add(loadWaitlistButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(cancelButton);
        bottomPanel.add(rescheduleButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadBookings();
    }

    private void loadBookings() {
        String selectedTrain = (String) trainSelector.getSelectedItem();
        if (selectedTrain != null) {
            bookingTable.setModel(adminManager.getBookingsForTrain(selectedTrain));
        }
    }

    private void cancelSelectedBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookingId = bookingTable.getValueAt(selectedRow, 0).toString();
            adminManager.cancelBooking(bookingId);
            JOptionPane.showMessageDialog(this, "Booking Cancelled.");
            loadBookings();
        }
    }

    private void rescheduleBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookingId = bookingTable.getValueAt(selectedRow, 0).toString();
            String newTrainId = JOptionPane.showInputDialog(this, "Enter new Train ID:");
            String newSeatId = JOptionPane.showInputDialog(this, "Enter new Seat ID:");

            adminManager.rescheduleBooking(bookingId, newTrainId, newSeatId);
            JOptionPane.showMessageDialog(this, "Booking Rescheduled.");
            loadBookings();
        }
    }

    private void showWaitlist() {
        String selectedTrain = (String) trainSelector.getSelectedItem();
        if (selectedTrain != null) {
            adminManager.showWaitlist(selectedTrain);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true);
        });
    }
}
