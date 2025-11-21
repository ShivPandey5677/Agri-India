package arpit.com.farmis;

public class GovernmentScheme {
    private String title;
    private String subtitle;
    private String description;
    private String status;

    public GovernmentScheme(String title, String subtitle, String description, String status) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.status = status;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
}