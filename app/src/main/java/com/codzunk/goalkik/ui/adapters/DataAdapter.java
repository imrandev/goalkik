package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.model.GroupModel;
import com.codzunk.goalkik.ui.SquadActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    private List<GroupModel> modelList;
    private int item = -1;

    public DataAdapter(Context context, List<GroupModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_adapter, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int size = position / 4;

        if (position > 0 && position % 4 == 0){
            item = 0;
        } else {
            if (item == 3){
                item = 0;
            } else {
                item++;
            }
        }

        try {
            holder.name.setText(modelList.get(size).getName());
            String rank = Config.RANK + " " + modelList.get(size).getItemModels().get(item).getRank();
            holder.rank.setText(rank);
            holder.team.setText(modelList.get(size).getItemModels().get(item).getTeam());
            String coach = "COACH : " + modelList.get(size).getItemModels().get(item).getCoach();
            holder.coach.setText(coach);
            holder.cardView.setTag(modelList.get(size).getItemModels().get(item).getCode());

            Picasso.with(context).load(Uri.parse(modelList.get(size).getItemModels().get(item).getIcon())).into(holder.icon);
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v, size);
            }
        });
    }

    private OnRecyclerViewItemClickListener onClickListener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent(context, SquadActivity.class);
            intent.putExtra("code", view.getTag().toString());
            context.startActivity(intent);
        }
    };

    @Override
    public int getItemCount() {
        return Config.TOTAL_TEAM;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, rank, team, coach;
        private ImageView icon;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            rank = itemView.findViewById(R.id.rank);
            team = itemView.findViewById(R.id.team);
            icon = itemView.findViewById(R.id.icon);
            coach = itemView.findViewById(R.id.coach);
            cardView = itemView.findViewById(R.id.cardView);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v, getAdapterPosition());
        }
    }

    interface OnRecyclerViewItemClickListener {
        void onClick(View view, int position);
    }
}
