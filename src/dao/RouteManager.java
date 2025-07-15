package dao;

import db.DatabaseManager;
import java.sql.*;

public class RouteManager {
    public static int addRoute(String source, String destination, String stops) {
        String sql = "INSERT INTO routes (source, destination, stops) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setString(3, stops);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  
            }

        } catch (SQLException e) {
            System.out.println("❌ Error adding route:");
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean mapTrainToRoute(int trainId, int routeId) {
        String sql = "INSERT INTO train_route_map (train_id, route_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trainId);
            ps.setInt(2, routeId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error mapping train to route:");
            e.printStackTrace();
        }
        return false;
    }
}
