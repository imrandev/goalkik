package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.PlayerModel;

import java.util.List;

public class SquadAdapter extends RecyclerView.Adapter<SquadAdapter.ViewHolder> {
    private List<PlayerModel> playerList;
    private Context context;

    public SquadAdapter(List<PlayerModel> playerList, Context context) {
        this.playerList = playerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_squad, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(playerList.get(position).getName());
        holder.position.setText(playerList.get(position).getPosition());
        holder.jersey.setText(playerList.get(position).getJerseyNumber());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, position, jersey;
        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.player_name);
            position = itemView.findViewById(R.id.position);
            jersey = itemView.findViewById(R.id.jersey);
        }
    }
}
