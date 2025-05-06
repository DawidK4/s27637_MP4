package subset;

import utils.ObjectPlusPlus;

public class Department extends ObjectPlusPlus {
    private String name;

    public Department(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        this.name = name;
    }

    public void addLecturer(Lecturer lecturer) {
        addLink("lecturers", "department", lecturer);
    }

    public void addHead(Lecturer lecturer) throws Exception {
        addLink_subset("heads", "headDepartment", "lecturers", lecturer);
    }

    public void removeLecturer(Lecturer lecturer) throws Exception {
        if (isLink("heads", lecturer)) {
            throw new Exception("Cannot remove lecturer who is still a head. Remove from 'heads' first.");
        }
    }

    public void showAllLecturers() throws Exception {
        showLinks("lecturers", System.out);
    }

    public void showAllHeads() throws Exception {
        showLinks("heads", System.out);
    }

    @Override
    public String toString() {
        return "Department: " + name;
    }
}
