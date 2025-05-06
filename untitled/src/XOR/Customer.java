package XOR;

import utils.ObjectPlus4;

import java.util.HashSet;
import java.util.Set;

public class Customer extends ObjectPlus4 {
    private int id;
    private String name;
    private String surname;

    private Set<Integer> ids;

    public Customer(int id, String name, String surname) {
        setId(id);
        setName(name);
        setSurname(surname);
        this.ids = new HashSet<>();

        addXorRole("deliveredToCustomer");
        addXorRole("deliveredToPickupPoint");
    }

    // Only one XOR delivery is allowed
    public void deliverTo(PickupPoint pickupPoint) throws Exception {
        addLinkXor("deliveredToPickupPoint", "isDeliveredBy", pickupPoint);
    }

    public void deliverTo(Customer customer) throws Exception {
        addLinkXor("deliveredToCustomer", "isDeliveredBy", customer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must not be negative!");
        }

        if (ids.contains(id)) {
            throw new IllegalArgumentException("Id has to be unique!");
        }

        ids.add(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty!");
        }

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Surname must not be null or empty!");
        }

        this.surname = surname;
    }
}
