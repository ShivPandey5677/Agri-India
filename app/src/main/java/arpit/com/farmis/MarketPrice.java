package arpit.com.farmis;

public class MarketPrice {
    private String cropName;
    private String currentPrice;
    private String previousPrice;
    private String change;
    private String trend;
    private String trendColor;

    public MarketPrice(String cropName, String currentPrice, String previousPrice, 
                      String change, String trend, String trendColor) {
        this.cropName = cropName;
        this.currentPrice = currentPrice;
        this.previousPrice = previousPrice;
        this.change = change;
        this.trend = trend;
        this.trendColor = trendColor;
    }

    // Getters
    public String getCropName() { return cropName; }
    public String getCurrentPrice() { return currentPrice; }
    public String getPreviousPrice() { return previousPrice; }
    public String getChange() { return change; }
    public String getTrend() { return trend; }
    public String getTrendColor() { return trendColor; }
}