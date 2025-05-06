package XOR;

import utils.ObjectPlusPlus;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PickupPoint extends ObjectPlusPlus {
    private int id;
    private String location;

    private static Set<Integer> ids = new HashSet<>();

    public PickupPoint(int id, String location) {
        setId(id);
        setLocation(location);
        ids = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than 0!");
        }

        if (ids.contains(id)) {
            throw new IllegalArgumentException("Pickup point id must be unique!");
        }

        ids.add(id);
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location must not be null or empty!");
        }

        this.location = location;
    }
}
