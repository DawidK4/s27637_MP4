package bag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private final int userId;
    private String name;
    private String surname;
    private Set<Visit> visits;

    private static final Set<Integer> userIds = new HashSet<>();

    public User(int userId, String name, String surname) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        if (userIds.contains(userId)) {
            throw new IllegalArgumentException("User ID already exists.");
        }
        userIds.add(userId);
        this.userId = userId;
        this.visits = new HashSet<>();

        setName(name);
        setSurname(surname);
    }

    public void addVisit (Visit visit) {
        visits.add(visit);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        this.surname = surname.trim();
    }
}
