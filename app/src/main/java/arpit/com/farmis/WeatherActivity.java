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
        // Current weather data based on your screenshot
        locationText.setText("Noida");
        currentTempText.setText("20°C");
        currentConditionText.setText("Sunny Day Ahead - Perfect for farming");

        // 7-day forecast data starting from 11/22
        ArrayList<WeatherData> weatherList = new ArrayList<>();
        weatherList.add(new WeatherData("11/22 Today", "26°C", "12°C", "☀️ Sunny", "Perfect for irrigation and planting"));
        weatherList.add(new WeatherData("11/23 Tomorrow", "25°C", "12°C", "☀️ Sunny", "Great for outdoor farming activities"));
        weatherList.add(new WeatherData("11/24 Mon", "25°C", "11°C", "☀️ Sunny", "Excellent for crop maintenance"));
        weatherList.add(new WeatherData("11/25 Tue", "24°C", "10°C", "☀️ Sunny", "Good for fertilizer application"));
        weatherList.add(new WeatherData("11/26 Wed", "24°C", "10°C", "☀️ Sunny", "Suitable for pest control"));
        weatherList.add(new WeatherData("11/27 Thu", "24°C", "11°C", "☀️ Sunny", "Perfect for harvesting"));
        weatherList.add(new WeatherData("11/28 Fri", "23°C", "9°C", "☀️ Sunny", "Ideal for seed sowing"));

        WeatherAdapter adapter = new WeatherAdapter(weatherList);
        weatherRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}