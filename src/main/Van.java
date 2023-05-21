package main;

import controller.OverViewFormController;
import db.Database;
import model.DriverDetails;
import model.InParkingDetails;
import model.OnDeliveryDetails;
import model.VehicleDetails;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Van extends DVehicle implements Vehicle {

    @Override
    public void park() throws IOException {
        int count = 0, count1 = 0, c = 0, c1 = 0;
        for (VehicleDetails vm : Database.vehicleDetails) {
            if (OverViewFormController.vNo.equals(vm.getVehicleNumber())) {
                c = count;
            }
            count++;
        }
        for (DriverDetails dm : Database.driverDetails) {
            if (OverViewFormController.dL.equals(dm.getDriverName())) {
                c1 = count1;
            }
            count1++;
        }

        VSlot.vehicle.setVehicleNumber(OverViewFormController.vNo);
        VSlot.vehicle.setVehicleType(OverViewFormController.vType);
        VSlot.vehicle.setMaximumWeight(Database.vehicleDetails.get(c).getMaximumWeight());
        VSlot.vehicle.setNoOfPassengers(Database.vehicleDetails.get(c).getNoOfPassengers());
        VSlot.vehicle.setDriverNIC(Database.driverDetails.get(c1).getNic());

        VSlot.hm.put(VSlot.slotNo, VSlot.vehicle);

        VSlot.vehicle = new DVehicle();

        InParkingDetails pd = new InParkingDetails();
        pd.setVehicleNumber(OverViewFormController.vNo);
        pd.setVehicleType(OverViewFormController.vType);
        pd.setSlotNumber(VSlot.slotNo);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm aa");
        Date date = new Date();
        pd.setFormat(formatter.format(date));
        VSlot.parkingDetails.add(pd);

        VSlot.leaveParkingDetails.removeIf(od -> OverViewFormController.vNo.equals(od.getVehicleNumber()));

        chooseSlotNumber();
    }

    private void chooseSlotNumber() {
        L1:
        for (int i = 0; i < VSlot.indexVan.size(); i++) {
            for (InParkingDetails in : VSlot.parkingDetails) {
                if (VSlot.indexVan.get(i).equals(in.getSlotNumber())) {
                    continue L1;
                }
            }
            VSlot.y = i;
            break;
        }
    }

    @Override
    public void leavePark() throws IOException {

        OnDeliveryDetails od = new OnDeliveryDetails();
        od.setVehicleNumber(OverViewFormController.vNo);
        od.setVehicleType(OverViewFormController.vType);
        od.setDriverName(OverViewFormController.dL);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm aa");
        Date date = new Date();
        od.setFormat(formatter.format(date));
        VSlot.leaveParkingDetails.add(od);

        for (int i = 0; i < VSlot.parkingDetails.size(); i++) {
            if (VSlot.parkingDetails.get(i).getVehicleNumber().equals(OverViewFormController.vNo)) {
                VSlot.parkingDetails.remove(i);
                break;
            }
        }

        AtomicInteger k = new AtomicInteger();
        for (DVehicle dv : VSlot.hm.values()) {
            if (OverViewFormController.vNo.equals(dv.getVehicleNumber())) {
                VSlot.hm.forEach((key, value) -> {
                    if (value.getVehicleNumber().equals(dv.getVehicleNumber())) {
                        k.set(key);
                    }
                });
            }
        }

        VSlot.hm.remove(k.get());

        chooseSlotNumber();
    }
}
