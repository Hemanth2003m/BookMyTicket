public class Seat {
    private String seatId;
    private String berthType;
    private boolean isAvailable;

    public Seat(String seatId, String berthType) {
        this.seatId = seatId;
        this.berthType = berthType;
        this.isAvailable = true; // All seats are free initially
    }

    public String getSeatId() {
        return seatId;
    }

    public String getBerthType() {
        return berthType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookSeat() {
        isAvailable = false;
    }

    public void cancelSeat() {
        isAvailable = true;
    }
}
