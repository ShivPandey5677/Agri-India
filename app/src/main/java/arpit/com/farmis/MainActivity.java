package arpit.com.farmis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import arpit.com.farmis.homerv.Item;
import arpit.com.farmis.homerv.Presenter;
import arpit.com.farmis.homerv.RVAdapter;
import arpit.com.farmis.network.DownloadJSON;
import arpit.com.farmis.network.JsonLoaded;
import arpit.com.farmis.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JsonLoaded {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String URL = "http://darsh.southindia.cloudapp.azure.com:8080/get/all";
    private ActivityMainBinding binding;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Skip network call and load dummy data directly
        loadDefaultData();

        binding.navView.setNavigationItemSelectedListener(this);

        View bottomSheet = findViewById(R.id.home_logs_container);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                binding.appBarMain.secToolbar.setAlpha(slideOffset);
                int min = 0, max = 360;
                //homeToolbarCross.animate().rotationBy(360);
                binding.appBarMain.secToolbarClose.setRotation(slideOffset * 180f);
                binding.appBarMain.secToolbarClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NestedScrollView nestedScrollView = findViewById(R.id.home_logs_container);
                        nestedScrollView.smoothScrollTo(0, 0);
                        nestedScrollView.smoothScrollTo(0, 0);
                        mBottomSheetBehavior.setState(mBottomSheetBehavior.STATE_COLLAPSED);
                    }
                });
                bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        });


    }

    public void fabClick(View view) {
         new MaterialStyledDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setDescription(R.string.dialog_desc)
                .setIcon(R.drawable.ic_supervisor_account_black_24dp)
                .withIconAnimation(true)
                .setPositiveText(R.string.call_now)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:1376936363"));
                        startActivity(callIntent);
                    }})
                .show();

    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, Alert.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onJsonLoaded(JSONObject json) {
        if (json != null) {
            Log.d(TAG, "Json => " + json.toString());
            try {
                ParseJSON parseJSON = new ParseJSON(json);
                ArrayList<Float> hums = parseJSON.getJSONArray("humidity");
                ArrayList<Float> temps = parseJSON.getJSONArray("temperature");
                ArrayList<Float> level = parseJSON.getJSONArray("level");
                ArrayList<Float> silo = parseJSON.getJSONArray("silo_level");

                final Item item = new Item(temps, hums, level, silo, 4);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // Stuff that updates the UI
                        loadRVCard(item);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
                loadDefaultData();
            }
        } else {
            // Network failed, load default data
            Log.d(TAG, "Network request failed, loading default data");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Unable to connect to server. Showing sample data.", Toast.LENGTH_LONG).show();
                }
            });
            loadDefaultData();
        }
    }

    private void loadDefaultData() {
        // Create realistic sample farm data
        ArrayList<Float> defaultHums = new ArrayList<>();
        ArrayList<Float> defaultTemps = new ArrayList<>();
        ArrayList<Float> defaultLevel = new ArrayList<>();
        ArrayList<Float> defaultSilo = new ArrayList<>();
        
        // Add realistic humidity values (50-80%)
        defaultHums.add(65.5f);
        defaultHums.add(72.3f);
        defaultHums.add(68.1f);
        defaultHums.add(70.8f);
        defaultHums.add(66.2f);
        
        // Add realistic temperature values (20-35°C)
        defaultTemps.add(25.4f);
        defaultTemps.add(28.7f);
        defaultTemps.add(26.1f);
        defaultTemps.add(29.3f);
        defaultTemps.add(24.8f);
        
        // Add water level values (60-95%)
        defaultLevel.add(82.5f);
        defaultLevel.add(78.2f);
        defaultLevel.add(85.7f);
        defaultLevel.add(80.1f);
        defaultLevel.add(83.9f);
        
        // Add silo level values (70-95%)
        defaultSilo.add(92.3f);
        defaultSilo.add(87.6f);
        defaultSilo.add(89.4f);
        defaultSilo.add(91.1f);
        defaultSilo.add(88.8f);

        final Item item = new Item(defaultTemps, defaultHums, defaultLevel, defaultSilo, 5);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadRVCard(item);
                Toast.makeText(MainActivity.this, "Showing sample farm data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadRVCard(Item item) {


        final Presenter[] myListPresenter = {new Presenter(item, this)};
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.appBarMain.fragmentHomeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.appBarMain.fragmentHomeRecycler.setLayoutManager(layoutManager);
        binding.appBarMain.fragmentHomeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.appBarMain.fragmentHomeRecycler.setAdapter(new RVAdapter(myListPresenter[0], MainActivity.this));
    }
}
