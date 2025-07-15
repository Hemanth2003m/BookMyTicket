package model;

public class Train {
    private int trainId;
    private String trainName;
    private String trainNumber;

    public Train() {}

    public Train(String trainName, String trainNumber) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }

    public Train(int trainId, String trainName, String trainNumber) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public String toString() {
        return trainName + " (" + trainNumber + ")";
    }
}
