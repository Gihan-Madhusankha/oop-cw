package main;

import controller.OverViewFormController;
import model.InParkingDetails;
import model.OnDeliveryDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class VSlot {

    public static HashMap<Integer, DVehicle> hm = new HashMap<>();
    public static DVehicle vehicle = new DVehicle();

    public static ArrayList<Integer> indexVan = new ArrayList<>();
    public static ArrayList<Integer> indexBus = new ArrayList<>();
    public static ArrayList<Integer> indexCargoLorry = new ArrayList<>();

    public static ArrayList<InParkingDetails> parkingDetails = new ArrayList<>();
    public static ArrayList<OnDeliveryDetails> leaveParkingDetails = new ArrayList<>();

    public static int slotNo = 0, y = 0, y1 = 0, y2 = 0;

    static {
        indexVan.add(1);
        indexVan.add(2);
        indexVan.add(3);
        indexVan.add(4);
        indexVan.add(12);
        indexVan.add(13);
        indexBus.add(14);
        indexCargoLorry.add(5);
        indexCargoLorry.add(6);
        indexCargoLorry.add(7);
        indexCargoLorry.add(8);
        indexCargoLorry.add(9);
        indexCargoLorry.add(10);
        indexCargoLorry.add(11);
    }

    public VSlot() {

        if (OverViewFormController.vType.equals("Van")) {
            slotNo = indexVan.get(y);
        }
        if (OverViewFormController.vType.equals("Bus")) {
            slotNo = indexBus.get(y1);
        }
        if (OverViewFormController.vType.equals("Cargo Lorry")) {
            slotNo = indexCargoLorry.get(y2);
        }
    }
}
