import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookingManager {
    private final PriorityQueue<BookingRequest> bookingQueue;
    private final ExecutorService executor;
    private final SeatTree seatTree;
    private final WaitlistManager waitlistManager;
    private final AtomicBoolean isProcessing;

    public BookingManager() {
        bookingQueue = new PriorityQueue<>();
        executor = Executors.newSingleThreadExecutor();
        seatTree = new SeatTree("T001");
        waitlistManager = new WaitlistManager();
        isProcessing = new AtomicBoolean(false);
        setupTree();
    }

    private void setupTree() {
        SeatNode sleeper = new SeatNode("Sleeper");
        sleeper.addChild(new SeatNode("lower"));
        sleeper.addChild(new SeatNode("middle"));
        sleeper.addChild(new SeatNode("upper"));

        SeatNode ac = new SeatNode("AC");
        ac.addChild(new SeatNode("lower"));
        ac.addChild(new SeatNode("upper"));

        seatTree.getRoot().addChild(sleeper);
        seatTree.getRoot().addChild(ac);
    }

    public void addBookingRequest(BookingRequest request) {
        synchronized (bookingQueue) {
            bookingQueue.offer(request);
            System.out.println("✅ Added to queue: " + request);
        }

        if (isProcessing.compareAndSet(false, true)) {
            executor.submit(this::processBookings);
        }
    }

    private void processBookings() {
        while (true) {
            BookingRequest request;

            synchronized (bookingQueue) {
                request = bookingQueue.poll();
                if (request == null) {
                    isProcessing.set(false);
                    break;
                }
            }

            System.out.println("🔄 Processing: " + request);

            SeatNode seat = seatTree.getAvailableSeat(request.getPreferredBerth());
            boolean success = seat != null;

            if (success) {
                System.out.println("🎟️ Seat assigned: " + seat.getLabel() + " to " + request.getUserId());
            } else {
                System.out.println("⚠️ No seat available → Waitlist");
                waitlistManager.addToWaitlist(request);
            }

            try {
                Thread.sleep(1000); // Simulated delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void cancelSeat(String berthLabel) {
        System.out.println("❌ Cancellation: " + berthLabel);
        freeBerth(seatTree.getRoot(), berthLabel);

        if (!waitlistManager.isEmpty()) {
            BookingRequest next = waitlistManager.promoteNext();
            System.out.println("🔁 Promoting: " + next.getUserId());
            addBookingRequest(next);
        }
    }

    private boolean freeBerth(SeatNode current, String label) {
        if (current == null) return false;

        if (current.getLabel().equalsIgnoreCase(label)) {
            current.setAvailable(true);
            return true;
        }

        for (SeatNode child : current.getChildren()) {
            if (freeBerth(child, label)) return true;
        }

        return false;
    }

    public void shutdown() {
        executor.shutdown();
    }
}
