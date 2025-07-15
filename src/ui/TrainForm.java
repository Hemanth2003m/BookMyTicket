package ui;

import db.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TrainForm extends JFrame {
    private JTextField trainNameField, trainNumberField, sourceField, destinationField, stopsField;
    private JButton saveButton;

    public TrainForm() {
        setTitle("Train & Route Entry Form");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Train Name:"));
        trainNameField = new JTextField();
//        trainNameField.setPreferredSize(new Dimension(50, 5));
        add(trainNameField);

        add(new JLabel("Train Number:"));
        trainNumberField = new JTextField();
        add(trainNumberField);

        add(new JLabel("Source Station:"));
        sourceField = new JTextField();
        add(sourceField);

        add(new JLabel("Destination Station:"));
        destinationField = new JTextField();
        add(destinationField);

        add(new JLabel("Intermediate Stops (comma-separated):"));
        stopsField = new JTextField();
        add(stopsField);

        saveButton = new JButton("Save Train & Route");
        add(new JLabel()); // Empty cell
        add(saveButton);

        saveButton.addActionListener(e -> saveTrainAndRoute());

        setVisible(true);
    }

    private void saveTrainAndRoute() {
        String trainName = trainNameField.getText().trim();
        String trainNumber = trainNumberField.getText().trim();
        String source = sourceField.getText().trim();
        String destination = destinationField.getText().trim();
        String stops = stopsField.getText().trim(); // Optional comma-separated list

        if (trainName.isEmpty() || trainNumber.isEmpty() || source.isEmpty() || destination.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try (Connection conn = DatabaseManager.getConnection()) {
            conn.setAutoCommit(false); // For safe rollback on error

            // Step 1: Insert into routes
            String insertRoute = "INSERT INTO routes (source, destination) VALUES (?, ?)";
            PreparedStatement routeStmt = conn.prepareStatement(insertRoute, Statement.RETURN_GENERATED_KEYS);
            routeStmt.setString(1, source);
            routeStmt.setString(2, destination);
            routeStmt.executeUpdate();

            ResultSet routeKeys = routeStmt.getGeneratedKeys();
            int routeId = -1;
            if (routeKeys.next()) {
                routeId = routeKeys.getInt(1);
            }

            // Step 2: Insert into trains
            String insertTrain = "INSERT INTO trains (train_name, train_number) VALUES (?, ?)";
            PreparedStatement trainStmt = conn.prepareStatement(insertTrain, Statement.RETURN_GENERATED_KEYS);
            trainStmt.setString(1, trainName);
            trainStmt.setString(2, trainNumber);
            trainStmt.executeUpdate();

            ResultSet trainKeys = trainStmt.getGeneratedKeys();
            int trainId = -1;
            if (trainKeys.next()) {
                trainId = trainKeys.getInt(1);
            }

            // Step 3: Map train to route
            String mapQuery = "INSERT INTO train_route_map (train_id, route_id) VALUES (?, ?)";
            PreparedStatement mapStmt = conn.prepareStatement(mapQuery);
            mapStmt.setInt(1, trainId);
            mapStmt.setInt(2, routeId);
            mapStmt.executeUpdate();

            // Step 4: Insert stations into route_stations
            String[] intermediateStops = stops.isEmpty() ? new String[0] : stops.split(",");
            int order = 1;

            String insertStation = "INSERT INTO route_stations (route_id, station_name, station_order) VALUES (?, ?, ?)";
            PreparedStatement stationStmt = conn.prepareStatement(insertStation);

            // Insert source station
            stationStmt.setInt(1, routeId);
            stationStmt.setString(2, source);
            stationStmt.setInt(3, order++);
            stationStmt.executeUpdate();

            // Insert intermediate stops
            for (String stop : intermediateStops) {
                String stopTrimmed = stop.trim();
                if (!stopTrimmed.isEmpty()) {
                    stationStmt.setInt(1, routeId);
                    stationStmt.setString(2, stopTrimmed);
                    stationStmt.setInt(3, order++);
                    stationStmt.executeUpdate();
                }
            }

            // Insert destination station
            stationStmt.setInt(1, routeId);
            stationStmt.setString(2, destination);
            stationStmt.setInt(3, order++);
            stationStmt.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Train and route saved successfully!");
            clearFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
        }
    }

    private void clearFields() {
        trainNameField.setText("");
        trainNumberField.setText("");
        sourceField.setText("");
        destinationField.setText("");
        stopsField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrainForm::new);
    }
}
