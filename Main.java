import java.util.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Create seats
        List<Seat> sleeperSeats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            sleeperSeats.add(new Seat("S1-Lower-" + i, "Lower"));
            sleeperSeats.add(new Seat("S1-Upper-" + i, "Upper"));
        }

        SeatMap seatMap = new SeatMap();
        seatMap.addClassSeats("Sleeper", sleeperSeats);

        SeatAvailabilityManager manager = new SeatAvailabilityManager();
        manager.addTrain("101", seatMap);

        // Launch UI
        SwingUtilities.invokeLater(() -> {
            SeatBookingUI ui = new SeatBookingUI(manager);
            ui.setVisible(true);
        });
    }
}
