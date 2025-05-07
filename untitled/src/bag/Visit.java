package bag;

import java.time.LocalDate;

public class Visit {
    private User user;
    private Website website;
    private LocalDate visitDate;

    public Visit(User user, Website website, LocalDate visitDate) {
        setUser(user);
        setWebsite(website);
        setVisitDate(visitDate);
    }

    void clearReference() {
        this.user = null;
        this.website = null;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        if (user == null) throw new IllegalArgumentException("User must not be null!");

        user.addVisit(this);
        this.user = user;
    }

    public Website getWebsite() {
        return website;
    }

    private void setWebsite(Website website) {
        if (website == null) throw new IllegalArgumentException("Website must not be null!");

        website.addVisit(this);
        this.website = website;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        if (visitDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Visit date must not be in the future!");
        }

        this.visitDate = visitDate;
    }
}
