package com.example.footballleague.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballleague.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;

public class StatsAdapter extends FirebaseRecyclerAdapter
        <Player,StatsAdapter.statsViewholder> {

    public StatsAdapter(@NonNull FirebaseRecyclerOptions<Player> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StatsAdapter.statsViewholder holder,
                                    int position, @NonNull Player model) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");
        holder.name.setText(model.getName());
        holder.team.setText(model.getTeamName());
        holder.goals.setText(String.valueOf(model.getGoals()));
    }

    @Override
    public StatsAdapter.statsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stats_item,parent,false);
        return new StatsAdapter.statsViewholder(view);
    }

    public class statsViewholder extends
            RecyclerView.ViewHolder
    {
        TextView name,team,goals;
        public statsViewholder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.player_stats_name_tv);
            team=itemView.findViewById(R.id.player_stats_team_name_tv);
            goals=itemView.findViewById(R.id.player_stats_goals_tv);
        }
    }
}
