package arpit.com.farmis;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KnowledgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Knowledge Base");

        TextView contentText = findViewById(R.id.content_text);
        contentText.setText("📚 Agricultural Knowledge Center\n\n" +
                "📖 Latest Articles:\n" +
                "• Modern irrigation techniques for water conservation\n" +
                "• Organic farming: Benefits and best practices\n" +
                "• Crop rotation strategies for soil health\n" +
                "• Post-harvest management and storage\n\n" +
                "🔬 Research Publications:\n" +
                "• Climate-resilient farming practices\n" +
                "• Integrated pest management systems\n" +
                "• Soil fertility improvement methods\n\n" +
                "🎥 Video Tutorials:\n" +
                "• Drip irrigation setup guide\n" +
                "• Composting techniques for farmers\n" +
                "• Disease identification in major crops\n\n" +
                "📊 Government Reports:\n" +
                "• Agricultural statistics 2024\n" +
                "• New farming policies and subsidies");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}