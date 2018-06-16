package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.FixtureModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FixAdapter extends RecyclerView.Adapter<FixAdapter.ViewHolder> {

    private List<FixtureModel> fixtureList;
    private Context context;
    private boolean isToday;
    private boolean isTomorrow;

    public FixAdapter(List<FixtureModel> fixtureList, Context context) {
        this.fixtureList = fixtureList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_fixture, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String day = "" + fixtureList.get(position).getCount();
        holder.match_day.setText(day);
        String between = fixtureList.get(position).getHomeTeamName() + " vs " + fixtureList.get(position).getAwayTeamName();
        holder.match_between.setText(between);
        holder.match_time.setText(fixtureList.get(position).getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            String dayOfTheWeek = (String) DateFormat.format("EEEE", format.parse(fixtureList.get(position).getDate()));
            holder.match_date.setText(dayOfTheWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (fixtureList.get(position).isToday() == 0){
            if (!isTomorrow){
                holder.today.setText(R.string.tomorrow);
                this.isTomorrow = true;
            } else {
                holder.today.setVisibility(View.GONE);
            }
        } else if (fixtureList.get(position).isToday() == 1) {
            if (!isToday){
                holder.today.setText(R.string.today);
                this.isToday = true;
            } else {
                holder.today.setVisibility(View.GONE);
            }
        } else {
            holder.today.setVisibility(View.GONE);
        }

        String status = fixtureList.get(position).getStatus();

        if (status.equals("IN_PLAY")){
            holder.live.setVisibility(View.VISIBLE);
            holder.result.setVisibility(View.GONE);
            holder.match_time.setVisibility(View.GONE);
        } else if (status.equals("FINISHED")){
            holder.live.setText(R.string.ended);
            String result = "\u2022 Full Time " + fixtureList.get(position).getHome() + " - " + fixtureList.get(position).getAway();
            holder.result.setText(result);
            holder.result.setVisibility(View.VISIBLE);
            holder.match_time.setVisibility(View.GONE);
        } else {
            holder.live.setVisibility(View.GONE);
            holder.result.setVisibility(View.GONE);
            holder.match_time.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView match_day, match_between, match_time, match_date, today, live, result;
        ViewHolder(View itemView) {
            super(itemView);
            match_day = itemView.findViewById(R.id.match_day);
            match_between = itemView.findViewById(R.id.match_between);
            match_time = itemView.findViewById(R.id.match_time);
            match_date = itemView.findViewById(R.id.match_date);
            today = itemView.findViewById(R.id.today);
            live = itemView.findViewById(R.id.live);
            result = itemView.findViewById(R.id.result);
        }
    }
}
