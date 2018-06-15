package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.model.GroupModel;
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
        int size = position / 4;

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

            Picasso.with(context).load(Uri.parse(modelList.get(size).getItemModels().get(item).getIcon())).into(holder.icon);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return Config.TOTAL_TEAM;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, rank, team;
        private ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            rank = itemView.findViewById(R.id.rank);
            team = itemView.findViewById(R.id.team);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
