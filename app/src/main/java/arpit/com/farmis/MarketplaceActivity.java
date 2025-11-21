package arpit.com.farmis;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Marketplace");

        TextView contentText = findViewById(R.id.content_text);
        contentText.setText("🛒 E-commerce Platform\n\n" +
                "• Buy seeds, fertilizers, and tools online\n" +
                "• Sell your produce directly to buyers\n" +
                "• Compare prices from multiple vendors\n" +
                "• Home delivery available\n" +
                "• Secure payment options\n\n" +
                "Coming Soon: Full marketplace integration!");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}