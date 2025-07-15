package ui;

import db.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TrainSearchUI extends JFrame {
    private JTextField sourceField, destinationField;
    private JButton searchButton;
    private JTable trainTable;
    private DefaultTableModel tableModel;

    public TrainSearchUI() {
        setTitle("Search Available Trains");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search by Route"));

        inputPanel.add(new JLabel("Source Station:"));
        sourceField = new JTextField();
        inputPanel.add(sourceField);

        inputPanel.add(new JLabel("Destination Station:"));
        destinationField = new JTextField();
        inputPanel.add(destinationField);

        add(inputPanel, BorderLayout.NORTH);

        // Table to display train results
        tableModel = new DefaultTableModel();
        trainTable = new JTable(tableModel);
        tableModel.addColumn("Train Name");
        tableModel.addColumn("Train Number");
        tableModel.addColumn("From Station");
        tableModel.addColumn("To Station");

        JScrollPane scrollPane = new JScrollPane(trainTable);
        add(scrollPane, BorderLayout.CENTER);

        // Search Button
        searchButton = new JButton("Search");
        add(searchButton, BorderLayout.SOUTH);

        // Button Action
        searchButton.addActionListener(e -> {
            String source = sourceField.getText().trim().toLowerCase();
            String destination = destinationField.getText().trim().toLowerCase();

            if (source.isEmpty() || destination.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both source and destination stations.");
                return;
            }

            if (source.equals(destination)) {
                JOptionPane.showMessageDialog(null, "Source and destination cannot be the same.");
                return;
            }

            fetchTrains(source, destination);
        });

        setVisible(true);
    }

    // Fetch trains with source before destination in order
    private void fetchTrains(String source, String destination) {
        tableModel.setRowCount(0); // Clear previous results

        String query =
                "SELECT DISTINCT t.train_name, t.train_number, rs1.station_name AS from_station, rs2.station_name AS to_station " +
                "FROM trains t " +
                "JOIN train_route_map trm ON t.train_id = trm.train_id " +
                "JOIN routes r ON r.route_id = trm.route_id " +
                "JOIN route_stations rs1 ON rs1.route_id = r.route_id " +
                "JOIN route_stations rs2 ON rs2.route_id = r.route_id " +
                "WHERE LOWER(rs1.station_name) = ? " +
                "AND LOWER(rs2.station_name) = ? " +
                "AND rs1.station_order < rs2.station_order";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                tableModel.addRow(new Object[]{
                        rs.getString("train_name"),
                        rs.getString("train_number"),
                        rs.getString("from_station"),
                        rs.getString("to_station")
                });
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "No trains found in this direction.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching train data.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrainSearchUI::new);
    }
}
