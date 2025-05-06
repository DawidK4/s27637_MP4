package XOR;

import utils.ObjectPlusPlus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Shipment extends ObjectPlusPlus {
    private int id;
    private static Set<Integer> ids = new HashSet<>();

    public Shipment(int id) {
       setId(id);
       ids = new HashSet<>();
    }

    public void deliverTo(PickupPoint pickupPoint) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must not be negative!");
        }

        if (ids.contains(id)) {
            throw new IllegalArgumentException("Id must be unique!");
        }

        ids.add(id);
        this.id = id;
    }
}
