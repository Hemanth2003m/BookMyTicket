package dao;

import db.DatabaseManager;
import java.sql.*;

public class TrainManager {

    public static int addTrain(String trainName, String trainNumber) {
        String sql = "INSERT INTO trains (train_name, train_number) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, trainName);
            ps.setString(2, trainNumber);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  
            }

        } catch (SQLException e) {
            System.out.println("❌ Error adding train:");
            e.printStackTrace();
        }
        return -1;
    }
}
