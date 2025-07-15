import java.util.ArrayList;
import java.util.List;

public class CompartmentNode {
    private String name;
    private List<Seat> seats;
    private List<CompartmentNode> children;

    public CompartmentNode(String name) {
        this.name = name;
        this.seats = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<CompartmentNode> getChildren() {
        return children;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void addChild(CompartmentNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return name;
    }
}
