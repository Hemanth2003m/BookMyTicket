import java.util.*;

public class SeatMap {
    private HashMap<String, List<Seat>> seatLayout;

    public SeatMap() {
        seatLayout = new HashMap<>();
    }

    public void addClassSeats(String className, List<Seat> seats) {
        seatLayout.put(className, seats);
    }

    public List<Seat> getSeatsByClass(String className) {
        return seatLayout.get(className);
    }

    public HashMap<String, List<Seat>> getAllSeats() {
        return seatLayout;
    }
}
