import java.util.function.Predicate;

public class SeatTree {
    private CompartmentNode root;
    
    public SeatTree(String trainName) {
        this.root = new CompartmentNode(trainName);
    }

    public CompartmentNode getRoot() {
        return root;
    }

    public CompartmentNode addClass(String className) {
        CompartmentNode classNode = new CompartmentNode(className);
        root.addChild(classNode);
        return classNode;
    }

    public CompartmentNode addCompartment(CompartmentNode classNode, String name) {
        CompartmentNode compartment = new CompartmentNode(name);
        classNode.addChild(compartment);
        return compartment;
    }

    public Seat searchSeat(Predicate<Seat> condition) {
        return searchRecursive(root, condition);
    }

    private Seat searchRecursive(CompartmentNode node, Predicate<Seat> condition) {
        for (Seat s : node.getSeats()) {
            if (s.isAvailable() && condition.test(s)) return s;
        }
        for (CompartmentNode child : node.getChildren()) {
            Seat found = searchRecursive(child, condition);
            if (found != null) return found;
        }
        return null;
    }
}
