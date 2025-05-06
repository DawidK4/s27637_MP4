package subset;

import utils.ObjectPlusPlus;

public class Lecturer extends ObjectPlusPlus {
    private String name;
    private String surname;

    public Lecturer(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        if (surname.length() < 2) {
            throw new IllegalArgumentException("Surname must be at least 2 characters long.");
        }
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
