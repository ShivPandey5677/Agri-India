package arpit.com.farmis;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MarketPriceAdapter extends RecyclerView.Adapter<MarketPriceAdapter.PriceViewHolder> {

    private ArrayList<MarketPrice> pricesList;

    public MarketPriceAdapter(ArrayList<MarketPrice> pricesList) {
        this.pricesList = pricesList;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_price, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        MarketPrice price = pricesList.get(position);
        holder.cropNameText.setText(price.getCropName());
        holder.currentPriceText.setText(price.getCurrentPrice() + "/quintal");
        holder.previousPriceText.setText("Yesterday: " + price.getPreviousPrice());
        holder.changeText.setText(price.getChange());
        holder.trendText.setText(price.getTrend());
        
        // Set trend color
        int color = Color.parseColor(price.getTrendColor());
        holder.changeText.setTextColor(color);
        holder.trendText.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return pricesList.size();
    }

    static class PriceViewHolder extends RecyclerView.ViewHolder {
        TextView cropNameText, currentPriceText, previousPriceText, changeText, trendText;

        public PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            cropNameText = itemView.findViewById(R.id.crop_name);
            currentPriceText = itemView.findViewById(R.id.current_price);
            previousPriceText = itemView.findViewById(R.id.previous_price);
            changeText = itemView.findViewById(R.id.price_change);
            trendText = itemView.findViewById(R.id.trend_arrow);
        }
    }
}