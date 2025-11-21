package arpit.com.farmis;

public class WeatherData {
    private String day;
    private String highTemp;
    private String lowTemp;
    private String condition;
    private String farmingAdvice;

    public WeatherData(String day, String highTemp, String lowTemp, String condition, String farmingAdvice) {
        this.day = day;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.condition = condition;
        this.farmingAdvice = farmingAdvice;
    }

    // Getters
    public String getDay() { return day; }
    public String getHighTemp() { return highTemp; }
    public String getLowTemp() { return lowTemp; }
    public String getCondition() { return condition; }
    public String getFarmingAdvice() { return farmingAdvice; }
}