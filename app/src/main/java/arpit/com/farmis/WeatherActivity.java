package arpit.com.farmis;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

public class WeatherActivity extends AppCompatActivity {

    private RecyclerView weatherRecyclerView;
    private TextView locationText, currentTempText, currentConditionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Weather Forecast");

        initViews();
        loadWeatherData();
    }

    private void initViews() {
        locationText = findViewById(R.id.location_text);
        currentTempText = findViewById(R.id.current_temp);
        currentConditionText = findViewById(R.id.current_condition);
        weatherRecyclerView = findViewById(R.id.weather_recycler);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadWeatherData() {
        // Sample weather data
        locationText.setText("Your Farm Location");
        currentTempText.setText("28°C");
        currentConditionText.setText("Partly Cloudy - Good for farming");

        // 7-day forecast data
        ArrayList<WeatherData> weatherList = new ArrayList<>();
        weatherList.add(new WeatherData("Today", "28°C", "22°C", "Partly Cloudy", "Good for irrigation"));
        weatherList.add(new WeatherData("Tomorrow", "30°C", "24°C", "Sunny", "Perfect for harvesting"));
        weatherList.add(new WeatherData("Day 3", "26°C", "20°C", "Light Rain", "Avoid spraying pesticides"));
        weatherList.add(new WeatherData("Day 4", "25°C", "19°C", "Heavy Rain", "Indoor work recommended"));
        weatherList.add(new WeatherData("Day 5", "27°C", "21°C", "Cloudy", "Good for planting"));
        weatherList.add(new WeatherData("Day 6", "29°C", "23°C", "Sunny", "Ideal farming conditions"));
        weatherList.add(new WeatherData("Day 7", "31°C", "25°C", "Hot", "Early morning work advised"));

        WeatherAdapter adapter = new WeatherAdapter(weatherList);
        weatherRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}