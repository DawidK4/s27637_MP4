package dynamicattributeconstraint;

import java.util.HashSet;
import java.util.Set;

public class Order {
    private String orderNumber;
    private int numberOfPieces;
    private static int maxNumberOfPieces;

    private static Set<String> existingOrderNumbers = new HashSet<>();

    public Order(String orderNumber, int numberOfPieces) {
        setOrderNumber(orderNumber);
        setNumberOfPieces(numberOfPieces);
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(int numberOfPieces) {
        if (numberOfPieces < 0) {
            throw new IllegalArgumentException("Number of pieces must not be negative!");
        }

        if (numberOfPieces > maxNumberOfPieces) {
            throw new IllegalArgumentException("Number of pieces must not exceed maximum number of pieces!");
        }

        this.numberOfPieces = numberOfPieces;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        if (orderNumber == null || orderNumber.isEmpty()) {
            throw new IllegalArgumentException("Order number must not be null or empty!");
        }

        if (this.orderNumber != null) {
            existingOrderNumbers.remove(this.orderNumber);
        }

        if (!existingOrderNumbers.add(orderNumber)) {
            throw new IllegalArgumentException("Order with the provided number already exists!");
        }

        this.orderNumber = orderNumber;
    }

    public static void setMaxNumberOfPieces(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("Maximum number of pieces must not be negative!");
        }
        maxNumberOfPieces = max;
    }
}
