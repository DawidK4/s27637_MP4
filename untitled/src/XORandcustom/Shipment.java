package XORandcustom;

import utils.ObjectPlus4;

import java.util.HashSet;
import java.util.Set;

public class Shipment extends ObjectPlus4 {
    private int id;
    private static Set<Integer> ids = new HashSet<>();

    public Shipment(int id) {
        setId(id);

        addXorRole("deliveredToCustomer");
        addXorRole("deliveredToPickupPoint");
    }

    public void deliverToCustomer(Customer customer) throws Exception {
        if (!customer.isActive()) {
            throw new Exception("Shipment cannot be delivered to an inactive customer.");
        }

        addLinkXor("deliveredToCustomer", "receivesShipment", customer);
    }

    public void deliverToPickupPoint(PickupPoint pickupPoint) throws Exception {
        addLinkXor("deliveredToPickupPoint", "hasShipment", pickupPoint);
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
