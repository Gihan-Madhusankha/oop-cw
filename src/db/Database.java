package db;

import model.DriverDetails;
import model.VehicleDetails;

import java.util.ArrayList;

public class Database {

    public static ArrayList<VehicleDetails> vehicleDetails = new ArrayList<>();
    public static ArrayList<DriverDetails> driverDetails = new ArrayList<>();

    static {
        vehicleDetails.add(new VehicleDetails("NA-3434", "Bus", 3500, 60));
        vehicleDetails.add(new VehicleDetails("KA-4563", "Van", 1000, 7));
        vehicleDetails.add(new VehicleDetails("58-3567", "Van", 1500, 4));
        vehicleDetails.add(new VehicleDetails("GF-4358", "Van", 800, 4));
        vehicleDetails.add(new VehicleDetails("CCB-3568", "Van", 1800, 8));
        vehicleDetails.add(new VehicleDetails("LM-6679", "Van", 1500, 4));
        vehicleDetails.add(new VehicleDetails("QA-3369", "Van", 1800, 6));
        vehicleDetails.add(new VehicleDetails("KB - 3668", "Cargo Lorry", 2500, 2));
        vehicleDetails.add(new VehicleDetails("JJ - 9878", "Cargo Lorry", 3000, 2));
        vehicleDetails.add(new VehicleDetails("GH - 5772", "Cargo Lorry", 4000, 3));
        vehicleDetails.add(new VehicleDetails("XY - 4456", "Cargo Lorry", 3500, 2));
        vehicleDetails.add(new VehicleDetails("YQ - 3536", "Cargo Lorry", 2000, 2));
        vehicleDetails.add(new VehicleDetails("CBB - 3566", "Cargo Lorry", 2500, 2));
        vehicleDetails.add(new VehicleDetails("QH - 3444", "Cargo Lorry", 5000, 4));

        driverDetails.add(new DriverDetails("Sumith Kumara", "7835348345V", "B6474845", "Panadura", "0725637456"));
        driverDetails.add(new DriverDetails("Amila Pathirana", "8826253734V", "B3354674", "Galle", "0717573583"));
        driverDetails.add(new DriverDetails("Jithmal Perera", "9283289272V", "B3674589", "Horana", "0772452457"));
        driverDetails.add(new DriverDetails("Sumith Dissanayaka", "9425245373V", "B8366399", "Kaluthara", "0782686390"));
        driverDetails.add(new DriverDetails("Sumanasiri Herath", "8976544373V", "B3537538", "Beruwala", "0772534436"));
        driverDetails.add(new DriverDetails("Awantha Fernando", "9173537839V", "B3554789", "Colombo 5", "0714534356"));
        driverDetails.add(new DriverDetails("Charith Sudara", "9573536833V", "B6835836", "Baththaramulla", "0771536662"));
        driverDetails.add(new DriverDetails("Prashan Dineth", "9362426738V", "B2683536", "Wadduwa", "0715353434"));
        driverDetails.add(new DriverDetails("Chethiya Dilan", "9162353436V", "B6836836", "Panadura", "0772436737"));
        driverDetails.add(new DriverDetails("Dushantha Perera", "9255556343V", "B3334435", "Matara", "0777245343"));
        driverDetails.add(new DriverDetails("Sumith Udayanga", "8735354355V", "B3573783", "Galle", "0703635442"));
        driverDetails.add(new DriverDetails("Dinesh Udara", "9026344373V", "B5343783", "Hettimulla", "0713456878"));
        driverDetails.add(new DriverDetails("Udana Chathuranga", "9692653338V", "B7888632", "Kottawa", "0772442444"));
        driverDetails.add(new DriverDetails("Mohommad Riaz", "9124537733V", "B3638537", "Kaluthara", "0777544222"));
        driverDetails.add(new DriverDetails("Sandun Kumara", "9563524267V", "B2263333", "Panadura", "0772325544"));
        driverDetails.add(new DriverDetails("Priyanga Perera", "9135343537V", "B3853753", "Matara", "0723344562"));
    }
}
