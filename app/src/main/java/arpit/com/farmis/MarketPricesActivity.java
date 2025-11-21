package arpit.com.farmis;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MarketPricesActivity extends AppCompatActivity {

    private RecyclerView pricesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_prices);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Market Prices");

        initViews();
        loadMarketData();
    }

    private void initViews() {
        pricesRecyclerView = findViewById(R.id.prices_recycler);
        pricesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadMarketData() {
        ArrayList<MarketPrice> pricesList = new ArrayList<>();
        
        // Sample APMC prices
        pricesList.add(new MarketPrice("Wheat", "₹2,150", "₹2,050", "+₹100", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Rice", "₹3,200", "₹3,150", "+₹50", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Cotton", "₹5,800", "₹6,000", "-₹200", "↓", "#F44336"));
        pricesList.add(new MarketPrice("Sugarcane", "₹350", "₹340", "+₹10", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Onion", "₹25", "₹30", "-₹5", "↓", "#F44336"));
        pricesList.add(new MarketPrice("Potato", "₹18", "₹16", "+₹2", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Tomato", "₹35", "₹40", "-₹5", "↓", "#F44336"));
        pricesList.add(new MarketPrice("Soybean", "₹4,200", "₹4,100", "+₹100", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Maize", "₹1,850", "₹1,800", "+₹50", "↑", "#4CAF50"));
        pricesList.add(new MarketPrice("Groundnut", "₹5,500", "₹5,600", "-₹100", "↓", "#F44336"));

        MarketPriceAdapter adapter = new MarketPriceAdapter(pricesList);
        pricesRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}