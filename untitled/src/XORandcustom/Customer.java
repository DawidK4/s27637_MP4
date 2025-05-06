package XORandcustom;

import utils.ObjectPlusPlus;

import java.util.HashSet;
import java.util.Set;

public class Customer extends ObjectPlusPlus {
    private int id;
    private String name;
    private String surname;
    private boolean isActive;

    private static Set<Integer> ids = new HashSet<>();

    public Customer(int id, String name, String surname, boolean isActive) {
        setId(id);
        setName(name);
        setSurname(surname);
        setActive(isActive);
    }

    private void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("Id must be non-negative!");
        if (ids.contains(id)) throw new IllegalArgumentException("Id must be unique!");
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
