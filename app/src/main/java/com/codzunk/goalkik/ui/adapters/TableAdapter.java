package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.LeagueModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private ArrayList<LeagueModel> leagueModels;
    private Context context;
    private boolean isHeader;

    public TableAdapter(ArrayList<LeagueModel> leagueModels, Context context) {
        this.leagueModels = leagueModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!isHeader){
            holder.id.setText(leagueModels.get(position).getGroup());
            isHeader = true;
        } else {
            holder.id.setVisibility(View.GONE);
            holder.tableHeader.setVisibility(View.GONE);
        }

        String goals = "" + leagueModels.get(position).getGoals();
        holder.goals.setText(goals);
        holder.team.setText(leagueModels.get(position).getTeam());
        String points = "" + leagueModels.get(position).getPoints();
        holder.points.setText(points);
        String rank = "" + leagueModels.get(position).getRank();
        holder.rank.setText(rank);

        Picasso.with(context).load(Uri.parse(leagueModels.get(position).getCrestURI())).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return leagueModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, rank, team, points, goals;
        private LinearLayout tableHeader;
        private ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.group_id);
            rank = itemView.findViewById(R.id.rank);
            team = itemView.findViewById(R.id.team);
            points = itemView.findViewById(R.id.points);
            goals = itemView.findViewById(R.id.goals);
            tableHeader = itemView.findViewById(R.id.table_header);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
