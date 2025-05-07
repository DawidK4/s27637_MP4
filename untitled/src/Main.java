import XORandcustom.Customer;
import XORandcustom.PickupPoint;
import XORandcustom.Shipment;
import bag.User;
import bag.Visit;
import bag.Website;
import custom.CarReservation;
import dynamicattributeconstraint.Order;
import subset.Department;
import subset.Lecturer;
import unique.Client;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // bag
        User user = new User(1, "Dawid", "Kucharski");
        Website website = new Website("http://website.com");
        Visit visit = new Visit(user, website, LocalDate.of(1999, 1, 1));
        Visit visit1 = new Visit(user, website, LocalDate.of(1922, 1, 1));

        // custom
//        CarReservation carReservation = new CarReservation("1111111",
//                LocalDate.of(2000, 1, 1),
//                LocalDate.of(2000, 2, 18));

        // dynamic
//        Order.setMaxNumberOfPieces(1);
//        Order order = new Order("111", 10);

        // ordered ...

        // static ...

        // subset
        try {
            // Create departments and lecturers
            Department csDepartment = new Department("Computer Science");
            Department mathDepartment = new Department("Mathematics");

            Lecturer lecturer1 = new Lecturer("John", "Doe");
            Lecturer lecturer2 = new Lecturer("Jane", "Smith");
            Lecturer lecturer3 = new Lecturer("Alice", "Johnson");

            // Add lecturers to departments
            csDepartment.addLecturer(lecturer1);
            csDepartment.addLecturer(lecturer2);
            mathDepartment.addLecturer(lecturer3);

            // Add head to the department
            csDepartment.addHead(lecturer1);  // John Doe is the head of CS department

            // Display lecturers and heads for each department
            System.out.println("CS Department:");
            csDepartment.showAllLecturers();
            csDepartment.showAllHeads();

            System.out.println("\nMath Department:");
            mathDepartment.showAllLecturers();

            // Remove lecturer
            csDepartment.removeLecturer(lecturer2);

            System.out.println("\nCS Department after removing Jane Smith:");
            csDepartment.showAllLecturers();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");

        // unique
        try {
            // Creating two clients with unique client numbers and emails
            Client client1 = new Client();
            client1.setClientNumber("C123");
            client1.setEmail("client1@example.com");

            Client client2 = new Client();
            client2.setClientNumber("C124");
            client2.setEmail("client2@example.com");

            System.out.println("Clients created successfully.");

            // Trying to create a third client with a duplicate client number (C123)
            Client client3 = new Client();
            client3.setClientNumber("C123"); // This should throw an exception
            client3.setEmail("client3@example.com");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Trying to create a fourth client with a duplicate email (client1@example.com)
            Client client4 = new Client();
            client4.setClientNumber("C125");
            client4.setEmail("client1@example.com"); // This should throw an exception

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n");

        // XOR
        try {
            // Create active and inactive customers
            Customer activeCustomer = new Customer(1, "Alice", "Smith", true);
            Customer inactiveCustomer = new Customer(2, "Bob", "Jones", false);

            // Create a pickup point
            PickupPoint pickup = new PickupPoint(1, "Locker #42");

            // Create shipment
            Shipment shipment1 = new Shipment(1);

            // Deliver to active customer (should succeed)
            shipment1.deliverToCustomer(activeCustomer);
            System.out.println("Shipment 1 delivered to active customer.");

            // Try to deliver the same shipment to a pickup point (should fail due to XOR constraint)
            try {
                shipment1.deliverToPickupPoint(pickup);
            } catch (Exception e) {
                System.out.println("Expected error (XOR): " + e.getMessage());
            }

            // New shipment
            Shipment shipment2 = new Shipment(2);

            // Try to deliver to inactive customer (should fail due to custom constraint)
            try {
                shipment2.deliverToCustomer(inactiveCustomer);
            } catch (Exception e) {
                System.out.println("Expected error (inactive customer): " + e.getMessage());
            }

            // Deliver shipment to a pickup point (should succeed)
            shipment2.deliverToPickupPoint(pickup);
            System.out.println("Shipment 2 delivered to pickup point.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
