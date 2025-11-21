package arpit.com.farmis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class GovernmentSchemeAdapter extends RecyclerView.Adapter<GovernmentSchemeAdapter.SchemeViewHolder> {

    private ArrayList<GovernmentScheme> schemesList;

    public GovernmentSchemeAdapter(ArrayList<GovernmentScheme> schemesList) {
        this.schemesList = schemesList;
    }

    @NonNull
    @Override
    public SchemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_government_scheme, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchemeViewHolder holder, int position) {
        GovernmentScheme scheme = schemesList.get(position);
        holder.titleText.setText(scheme.getTitle());
        holder.subtitleText.setText(scheme.getSubtitle());
        holder.descriptionText.setText(scheme.getDescription());
        holder.actionButton.setText(scheme.getStatus());
        
        holder.actionButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Opening " + scheme.getTitle() + " application", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return schemesList.size();
    }

    static class SchemeViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, subtitleText, descriptionText;
        Button actionButton;

        public SchemeViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.scheme_title);
            subtitleText = itemView.findViewById(R.id.scheme_subtitle);
            descriptionText = itemView.findViewById(R.id.scheme_description);
            actionButton = itemView.findViewById(R.id.action_button);
        }
    }
}