import java.util.*;

public class SeatAvailabilityManager {
    private HashMap<String, SeatMap> trainSeats;

    public SeatAvailabilityManager() {
        trainSeats = new HashMap<>();
    }

    public void addTrain(String trainNo, SeatMap seatMap) {
        trainSeats.put(trainNo, seatMap);
    }

    public boolean bookSeat(String trainNo, String className, String seatId) {
        SeatMap seatMap = trainSeats.get(trainNo);
        if (seatMap == null) return false;

        for (Seat seat : seatMap.getSeatsByClass(className)) {
            if (seat.getSeatId().equals(seatId) && seat.isAvailable()) {
                seat.bookSeat();
                return true;
            }
        }
        return false;
    }

    public boolean cancelSeat(String trainNo, String className, String seatId) {
        SeatMap seatMap = trainSeats.get(trainNo);
        if (seatMap == null) return false;

        for (Seat seat : seatMap.getSeatsByClass(className)) {
            if (seat.getSeatId().equals(seatId) && !seat.isAvailable()) {
                seat.cancelSeat();
                return true;
            }
        }
        return false;
    }

    public SeatMap getSeatMap(String trainNo) {
        return trainSeats.get(trainNo);
    }
}
