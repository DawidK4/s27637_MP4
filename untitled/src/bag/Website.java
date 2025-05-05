package bag;

import java.util.HashSet;
import java.util.Set;

public class Website {
    private String url;
    private Set<Visit> isVisitedBy;

    public Website(String url) {
        setUrl(url);
        this.isVisitedBy = new HashSet<>();
    }

    public void addVisit(Visit visit) {
        isVisitedBy.add(visit);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty.");
        }
        if (!url.matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$")) {
            throw new IllegalArgumentException("Invalid URL format.");
        }

        this.url = url;
    }
}
