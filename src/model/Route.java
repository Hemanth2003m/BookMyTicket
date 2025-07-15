package model;

public class Route {
    private int routeId;
    private String source;
    private String destination;
    private String stops;

    public Route() {}

    public Route(String source, String destination, String stops) {
        this.source = source;
        this.destination = destination;
        this.stops = stops;
    }

    public Route(int routeId, String source, String destination, String stops) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return source + " → " + destination;
    }
}
