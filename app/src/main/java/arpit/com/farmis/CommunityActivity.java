package arpit.com.farmis;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Farmer Community");

        TextView contentText = findViewById(R.id.content_text);
        contentText.setText("👥 Farmer Community Network\n\n" +
                "• Post farming queries and get expert answers\n" +
                "• Share your farming experiences and tips\n" +
                "• Connect with farmers in your area\n" +
                "• Join discussion groups by crop type\n" +
                "• Get real-time help during farming seasons\n" +
                "• Share photos of crop diseases for diagnosis\n\n" +
                "Recent Discussions:\n" +
                "🌾 \"Best time to sow wheat this season?\"\n" +
                "🐛 \"Organic pest control for tomatoes\"\n" +
                "💧 \"Water management during drought\"");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}