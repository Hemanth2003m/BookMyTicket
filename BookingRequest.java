public class BookingRequest implements Comparable<BookingRequest> {
    private String userId;
    private String userType; // "senior", "disabled", "general"
    private long timestamp;
    private String trainId;
    private String preferredBerth;

    // Constructor
    public BookingRequest(String userId, String userType, String trainId, String preferredBerth) {
        this.userId = userId;
        this.userType = userType.toLowerCase();
        this.trainId = trainId;
        this.preferredBerth = preferredBerth;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUserType() { return userType; }
    public String getTrainId() { return trainId; }
    public String getPreferredBerth() { return preferredBerth; }
    public long getTimestamp() { return timestamp; }

    // Priority logic for queue ordering
    @Override
    public int compareTo(BookingRequest other) {
        int p1 = getPriority(this.userType);
        int p2 = getPriority(other.userType);

        if (p1 != p2) return Integer.compare(p1, p2);
        return Long.compare(this.timestamp, other.timestamp);
    }

    // Define priority levels
    private int getPriority(String userType) {
        return switch (userType) {
            case "senior" -> 1;
            case "disabled" -> 2;
            case "general" -> 3;
            default -> 4; // fallback
        };
    }

    // For testing/logging
    @Override
    public String toString() {
        return "[" + userType + " | " + userId + " | " + preferredBerth + " | " + timestamp + "]";
    }
}
