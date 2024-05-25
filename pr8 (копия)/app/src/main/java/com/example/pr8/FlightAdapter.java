package com.example.pr8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private List<Flight> flightList;
    private Context context;

    public FlightAdapter(List<Flight> flightList, Context context) {
        this.flightList = flightList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flight_item, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.destination.setText(flight.getDestination());
        holder.airline.setText(flight.getAirline());
        holder.departureTime.setText(flight.getDepartureTime());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FlightDetailsActivity.class);
            intent.putExtra("destination", flight.getDestination());
            intent.putExtra("airline", flight.getAirline());
            intent.putExtra("departureTime", flight.getDepartureTime());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public void filterList(List<Flight> filteredList) {
        flightList = filteredList;
        notifyDataSetChanged();
    }

    static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView destination, airline, departureTime;

        FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            airline = itemView.findViewById(R.id.airline);
            departureTime = itemView.findViewById(R.id.departure_time);
        }
    }
}
