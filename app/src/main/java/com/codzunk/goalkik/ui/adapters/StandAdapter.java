package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.LeagueModel;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
import com.codzunk.goalkik.ui.TableActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StandAdapter extends RecyclerView.Adapter<StandAdapter.ViewHolder>{

    private Standings standings;
    private Context context;

    private List<String> groupList = new ArrayList<>();

    public StandAdapter(Standings standings, Context context) {
        this.standings = standings;
        this.context = context;

        groupList.add("A");
        groupList.add("B");
        groupList.add("C");
        groupList.add("D");
        groupList.add("E");
        groupList.add("F");
        groupList.add("G");
        groupList.add("H");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_stand, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.group.setText("Group " + groupList.get(position));
        holder.group.setTag(groupList.get(position));
        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v, holder.getAdapterPosition());
            }
        });
    }

    private OnRecyclerViewItemClickListener onClickListener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            if (view.getTag() == "A"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getA().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getA().get(i).getCrestURI());
                    model.setGoalDifference(standings.getA().get(i).getGoalDifference());
                    model.setCrestURI(standings.getA().get(i).getCrestURI());
                    model.setGoals(standings.getA().get(i).getGoals());
                    model.setGoalsAgainst(standings.getA().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getA().get(i).getPlayedGames());
                    model.setGroup(standings.getA().get(i).getGroup());
                    model.setPoints(standings.getA().get(i).getPoints());
                    model.setRank(standings.getA().get(i).getRank());
                    model.setTeam(standings.getA().get(i).getTeam());
                    model.setTeamId(standings.getA().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getA().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "B"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getB().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getB().get(i).getCrestURI());
                    model.setGoalDifference(standings.getB().get(i).getGoalDifference());
                    model.setCrestURI(standings.getB().get(i).getCrestURI());
                    model.setGoals(standings.getB().get(i).getGoals());
                    model.setGoalsAgainst(standings.getB().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getB().get(i).getPlayedGames());
                    model.setGroup(standings.getB().get(i).getGroup());
                    model.setPoints(standings.getB().get(i).getPoints());
                    model.setRank(standings.getB().get(i).getRank());
                    model.setTeam(standings.getB().get(i).getTeam());
                    model.setTeamId(standings.getB().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getB().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "C"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getC().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getC().get(i).getCrestURI());
                    model.setGoalDifference(standings.getC().get(i).getGoalDifference());
                    model.setCrestURI(standings.getC().get(i).getCrestURI());
                    model.setGoals(standings.getC().get(i).getGoals());
                    model.setGoalsAgainst(standings.getC().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getC().get(i).getPlayedGames());
                    model.setGroup(standings.getC().get(i).getGroup());
                    model.setPoints(standings.getC().get(i).getPoints());
                    model.setRank(standings.getC().get(i).getRank());
                    model.setTeam(standings.getC().get(i).getTeam());
                    model.setTeamId(standings.getC().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getC().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "D"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getD().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getD().get(i).getCrestURI());
                    model.setGoalDifference(standings.getD().get(i).getGoalDifference());
                    model.setCrestURI(standings.getD().get(i).getCrestURI());
                    model.setGoals(standings.getD().get(i).getGoals());
                    model.setGoalsAgainst(standings.getD().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getD().get(i).getPlayedGames());
                    model.setGroup(standings.getD().get(i).getGroup());
                    model.setPoints(standings.getD().get(i).getPoints());
                    model.setRank(standings.getD().get(i).getRank());
                    model.setTeam(standings.getD().get(i).getTeam());
                    model.setTeamId(standings.getD().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getD().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "E"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getE().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getE().get(i).getCrestURI());
                    model.setGoalDifference(standings.getE().get(i).getGoalDifference());
                    model.setCrestURI(standings.getE().get(i).getCrestURI());
                    model.setGoals(standings.getE().get(i).getGoals());
                    model.setGoalsAgainst(standings.getE().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getE().get(i).getPlayedGames());
                    model.setGroup(standings.getE().get(i).getGroup());
                    model.setPoints(standings.getE().get(i).getPoints());
                    model.setRank(standings.getE().get(i).getRank());
                    model.setTeam(standings.getE().get(i).getTeam());
                    model.setTeamId(standings.getE().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getE().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "F"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getF().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getF().get(i).getCrestURI());
                    model.setGoalDifference(standings.getF().get(i).getGoalDifference());
                    model.setCrestURI(standings.getF().get(i).getCrestURI());
                    model.setGoals(standings.getF().get(i).getGoals());
                    model.setGoalsAgainst(standings.getF().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getF().get(i).getPlayedGames());
                    model.setGroup(standings.getF().get(i).getGroup());
                    model.setPoints(standings.getF().get(i).getPoints());
                    model.setRank(standings.getF().get(i).getRank());
                    model.setTeam(standings.getF().get(i).getTeam());
                    model.setTeamId(standings.getF().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getF().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "G"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getG().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getG().get(i).getCrestURI());
                    model.setGoalDifference(standings.getG().get(i).getGoalDifference());
                    model.setCrestURI(standings.getG().get(i).getCrestURI());
                    model.setGoals(standings.getG().get(i).getGoals());
                    model.setGoalsAgainst(standings.getG().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getG().get(i).getPlayedGames());
                    model.setGroup(standings.getG().get(i).getGroup());
                    model.setPoints(standings.getG().get(i).getPoints());
                    model.setRank(standings.getG().get(i).getRank());
                    model.setTeam(standings.getG().get(i).getTeam());
                    model.setTeamId(standings.getG().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getG().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            } else if (view.getTag() == "H"){
                List<LeagueModel> leagueModels = new ArrayList<>(2);
                for (int i=0; i<standings.getH().size(); i++){
                    LeagueModel model = new LeagueModel();
                    model.setCrestURI(standings.getH().get(i).getCrestURI());
                    model.setGoalDifference(standings.getH().get(i).getGoalDifference());
                    model.setCrestURI(standings.getH().get(i).getCrestURI());
                    model.setGoals(standings.getH().get(i).getGoals());
                    model.setGoalsAgainst(standings.getH().get(i).getGoalsAgainst());
                    model.setPlayedGames(standings.getH().get(i).getPlayedGames());
                    model.setGroup(standings.getH().get(i).getGroup());
                    model.setPoints(standings.getH().get(i).getPoints());
                    model.setRank(standings.getH().get(i).getRank());
                    model.setTeam(standings.getH().get(i).getTeam());
                    model.setTeamId(standings.getH().get(i).getTeamId());
                    leagueModels.add(model);

                    if (i == standings.getA().size()-1){
                        Intent intent = new Intent(context, TableActivity.class);
                        intent.putExtra("arrayList", (Serializable) leagueModels);
                        context.startActivity(intent);
                    }
                }
            }
        }
    };

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView group;
        ViewHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group);
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
