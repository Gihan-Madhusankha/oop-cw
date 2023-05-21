package main;

public class DVehicle {
    private String vehicleNumber;
    private String vehicleType;
    private int maximumWeight;
    private int noOfPassengers;
    private String driverNIC;

    public DVehicle() {
    }

    public DVehicle(String vehicleNumber, String vehicleType, int maximumWeight, int noOfPassengers, String driverNIC) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maximumWeight = maximumWeight;
        this.noOfPassengers = noOfPassengers;
        this.driverNIC = driverNIC;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getDriverNIC() {
        return driverNIC;
    }

    public void setDriverNIC(String driverNIC) {
        this.driverNIC = driverNIC;
    }

    @Override
    public String toString() {
        return "DVehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", maximumWeight=" + maximumWeight +
                ", noOfPassengers=" + noOfPassengers +
                ", driverNIC='" + driverNIC + '\'' +
                '}';
    }
}
