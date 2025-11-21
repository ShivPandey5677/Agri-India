package arpit.com.farmis;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class GovernmentSchemesActivity extends AppCompatActivity {

    private RecyclerView schemesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_schemes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Government Schemes");

        initViews();
        loadSchemesData();
    }

    private void initViews() {
        schemesRecyclerView = findViewById(R.id.schemes_recycler);
        schemesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadSchemesData() {
        ArrayList<GovernmentScheme> schemesList = new ArrayList<>();
        
        schemesList.add(new GovernmentScheme(
            "PM-KISAN", 
            "₹6,000 per year direct benefit transfer",
            "Financial support to small and marginal farmers. Get ₹2,000 in three installments.",
            "Active"
        ));
        
        schemesList.add(new GovernmentScheme(
            "Soil Health Card", 
            "Free soil testing and recommendations",
            "Get your soil tested for free and receive customized fertilizer recommendations.",
            "Apply Now"
        ));
        
        schemesList.add(new GovernmentScheme(
            "Pradhan Mantri Fasal Bima Yojana", 
            "Crop insurance at subsidized rates",
            "Protect your crops against natural calamities with affordable insurance.",
            "Enroll"
        ));
        
        schemesList.add(new GovernmentScheme(
            "Kisan Credit Card", 
            "Easy credit for farming needs",
            "Get instant credit up to ₹3 lakh for seeds, fertilizers, and equipment.",
            "Apply"
        ));
        
        schemesList.add(new GovernmentScheme(
            "PM Kisan Maan Dhan Yojana", 
            "Pension scheme for farmers",
            "Monthly pension of ₹3,000 after 60 years with minimal contribution.",
            "Join Now"
        ));
        
        schemesList.add(new GovernmentScheme(
            "National Agriculture Market", 
            "Online trading platform",
            "Sell your produce directly on e-NAM platform for better prices.",
            "Register"
        ));

        GovernmentSchemeAdapter adapter = new GovernmentSchemeAdapter(schemesList);
        schemesRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}