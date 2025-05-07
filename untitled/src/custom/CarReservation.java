package custom;

import utils.ObjectPlus4;
import java.time.LocalDate;

public class CarReservation extends ObjectPlus4 {
    private String licencePlate;
    private LocalDate startDate;
    private LocalDate endDate;

    public CarReservation(String licencePlate, LocalDate startDate, LocalDate endDate) {
        setLicencePlate(licencePlate);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        if (startDate != null && endDate != null && endDate.isAfter(startDate.plusDays(30))) {
            throw new IllegalArgumentException("Reservation cannot exceed 30 days.");
        }
        this.endDate = endDate;
    }
}
