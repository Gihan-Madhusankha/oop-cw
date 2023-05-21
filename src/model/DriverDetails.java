package model;

public class DriverDetails {
    private String driverName;
    private String nic;
    private String licenseNumber;
    private String address;
    private String contactNumber;

    public DriverDetails() {
    }

    public DriverDetails(String driverName, String nic, String licenseNumber, String address, String contactNumber) {
        this.driverName = driverName;
        this.nic = nic;
        this.licenseNumber = licenseNumber;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "DriverDetails{" +
                "driverName='" + driverName + '\'' +
                ", nic='" + nic + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber=" + contactNumber +
                '}';
    }
}
