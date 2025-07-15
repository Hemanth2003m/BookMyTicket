public class Seat {
    private String berthType;
    private boolean isAvailable;
    private double price;

    public Seat(String berthType, double price) {
        this.berthType = berthType;
        this.price = price;
        this.isAvailable = true;
    }

    public String getBerthType() {
        return berthType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void bookSeat() {
        if (!isAvailable) throw new IllegalStateException("Seat already booked!");
        isAvailable = false;
    }

    public void cancelBooking() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return berthType + " - " + (isAvailable ? "Available" : "Booked") + " - ₹" + price;
    }
}
