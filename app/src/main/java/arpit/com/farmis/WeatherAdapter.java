package arpit.com.farmis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ArrayList<WeatherData> weatherList;

    public WeatherAdapter(ArrayList<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherData weather = weatherList.get(position);
        holder.dayText.setText(weather.getDay());
        holder.highTempText.setText(weather.getHighTemp());
        holder.lowTempText.setText(weather.getLowTemp());
        holder.conditionText.setText(weather.getCondition());
        holder.adviceText.setText(weather.getFarmingAdvice());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView dayText, highTempText, lowTempText, conditionText, adviceText;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.day_text);
            highTempText = itemView.findViewById(R.id.high_temp);
            lowTempText = itemView.findViewById(R.id.low_temp);
            conditionText = itemView.findViewById(R.id.condition_text);
            adviceText = itemView.findViewById(R.id.advice_text);
        }
    }
}