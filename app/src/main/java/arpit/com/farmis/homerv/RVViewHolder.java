package arpit.com.farmis.homerv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import arpit.com.farmis.ChartData;
import arpit.com.farmis.R;

public class RVViewHolder extends RecyclerView.ViewHolder implements DataLoaded {
    // ImageView imageView;
    Context context;
    LineChart lineChart;
    TextView stats, max, curr, min, emptyTV;
    ImageView imageView, emptyIW;


    public RVViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        lineChart = itemView.findViewById(R.id.line_chart);
        stats = itemView.findViewById(R.id.stats_title);
        max = itemView.findViewById(R.id.max);
        curr = itemView.findViewById(R.id.cur);
        min = itemView.findViewById(R.id.min);
        imageView = itemView.findViewById(R.id.info_image);
        this.context = context;
    }


    @Override
    public void onDataLoaded(ArrayList<Float> values, String name) {
        //check card type and load data accor.

        if (values.isEmpty()) {

            if (name.equalsIgnoreCase("humidity")) {
                imageView.setImageResource(R.drawable.ic_humidity);
            } else if (name.equalsIgnoreCase("temperature")) {
                imageView.setImageResource(R.drawable.ic_thermometer);
            } else if (name.equalsIgnoreCase("level")) {
                imageView.setImageResource(R.drawable.ic_arrow_up_water_level);
            } else if (name.equalsIgnoreCase("silo level")) {
//            imageView.setImageResource(R.drawable.ic_humidity);
            }

        } else {
            List<Entry> entries = new ArrayList<Entry>();
            ArrayList<ChartData> chartDatas = new ArrayList<>();

            Date date = new Date();   // given date
            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date
            int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
            int position = 24 - hour;

            // Use the full values list instead of creating problematic sublists
            List<Float> valueList = values;
            System.out.print("pos=> " + position + " hour=> " + hour);
            stats.setText(name);

            // Safety check to ensure we have data
            if (valueList.isEmpty()) {
                return;
            }

            if (name.equalsIgnoreCase("humidity")) {
                imageView.setImageResource(R.drawable.ic_humidity);
                max.setText(context.getString(R.string.max) + " " + Collections.max(valueList) + "g/m3");
                curr.setText(context.getString(R.string.current) + " " + values.get(values.size() - 1) + "g/m3");
                min.setText(context.getString(R.string.min) + " " + Collections.min(valueList) + "g/m3");

            } else if (name.equalsIgnoreCase("temperature")) {
                imageView.setImageResource(R.drawable.ic_thermometer);
                max.setText(context.getString(R.string.max) + " " + Collections.max(valueList) + context.getString(R.string.celsius));
                curr.setText(context.getString(R.string.current) + " " + values.get(values.size() - 1) + context.getString(R.string.celsius));
                min.setText(context.getString(R.string.min) + " " + Collections.min(valueList) + context.getString(R.string.celsius));

            } else if (name.equalsIgnoreCase("level")) {
                imageView.setImageResource(R.drawable.ic_arrow_up_water_level);
                max.setText(context.getString(R.string.max) + " " + Collections.max(valueList));
                curr.setText(context.getString(R.string.current) + " " + values.get(values.size() - 1));
                min.setText(context.getString(R.string.min) + " " + Collections.min(valueList));

            } else if (name.equalsIgnoreCase("silo level")) {
                imageView.setImageResource(R.drawable.ic_silo);
                max.setText(context.getString(R.string.max) + " " + Collections.max(valueList));
                curr.setText(context.getString(R.string.current) + " " + values.get(values.size() - 1));
                min.setText(context.getString(R.string.min) + " " + Collections.min(valueList));

            }

            // Create chart data from all available values
            for (int i = 0; i < Math.min(valueList.size(), 24); i++) {
                chartDatas.add(new ChartData(i, valueList.get(i)));
            }

            // Convert chart data to entries for the line chart
            for (ChartData chartData : chartDatas) {
                entries.add(new Entry(chartData.getX(), chartData.getY()));
            }

            // Create and configure the line chart
            if (!entries.isEmpty()) {
                LineDataSet dataSet = new LineDataSet(entries, "Values");
                dataSet.setColor(Color.parseColor("#3f51b5"));
                dataSet.setValueTextColor(Color.RED);
                dataSet.setValueTextSize(0f); // Hide values on points
                dataSet.setDrawFilled(true);
                dataSet.setDrawCircles(false);
                dataSet.setLineWidth(2f);

                if (Utils.getSDKInt() >= 18) {
                    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.chart_gradient);
                    dataSet.setFillDrawable(drawable);
                } else {
                    dataSet.setFillColor(Color.BLUE);
                }

                LineData lineData = new LineData(dataSet);
                lineData.setDrawValues(false);
                lineChart.setData(lineData);

                // Configure chart appearance
                lineChart.getXAxis().setEnabled(true);
                lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                lineChart.getXAxis().setDrawGridLines(false);
                lineChart.getXAxis().setTextColor(Color.parseColor("#3F51B5"));
                lineChart.getXAxis().setTextSize(12f);
                lineChart.getXAxis().setYOffset(10f);

                lineChart.getAxisLeft().setEnabled(true);
                lineChart.getAxisLeft().setDrawAxisLine(true);
                lineChart.getAxisLeft().setLabelCount(5, true);
                lineChart.getAxisLeft().setDrawGridLines(false);

                lineChart.getAxisRight().setEnabled(false);
                lineChart.getAxisRight().setDrawGridLines(false);

                lineChart.getLegend().setEnabled(false);
                lineChart.getDescription().setEnabled(false);
                lineChart.setDrawGridBackground(false);
                lineChart.setDrawBorders(false);
                lineChart.setAutoScaleMinMaxEnabled(true);
                lineChart.setBackgroundColor(Color.WHITE);
                lineChart.setExtraBottomOffset(15f);

                // Disable interactions
                lineChart.setTouchEnabled(false);
                lineChart.setDragEnabled(false);
                lineChart.setScaleEnabled(false);
                lineChart.setPinchZoom(false);
                lineChart.setClickable(false);

                // Refresh the chart
                lineChart.notifyDataSetChanged();
                lineChart.invalidate();
            }
        }

    }
}
