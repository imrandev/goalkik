package com.codzunk.goalkik.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.ScoreModel;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private List<ScoreModel> modelList;

    public ScoreAdapter(List<ScoreModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        boolean isPlaying = modelList.get(position).isPlaying();

        if (isPlaying) {
            holder.home.setText(modelList.get(position).getHomeTeam());
            holder.homeScore.setText(modelList.get(position).getHomeGoal());
            holder.away.setText(modelList.get(position).getAwayTeam());
            holder.awayScore.setText(modelList.get(position).getAwayGoal());

            holder.scoreBoard.setVisibility(View.VISIBLE);
            holder.timeState.setVisibility(View.GONE);
        } else {
            holder.scoreBoard.setVisibility(View.GONE);
            holder.timeState.setVisibility(View.VISIBLE);
            holder.timeState.setText(R.string.no_match);
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout scoreBoard;
        TextView home, homeScore, away, awayScore, timeState;
        ViewHolder(View itemView) {
            super(itemView);

            home = itemView.findViewById(R.id.home);
            homeScore = itemView.findViewById(R.id.home_score);
            away = itemView.findViewById(R.id.away);
            awayScore = itemView.findViewById(R.id.away_score);
            timeState = itemView.findViewById(R.id.timing_state);
            scoreBoard = itemView.findViewById(R.id.score_board);
        }
    }
}
