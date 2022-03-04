package com.example.footballleague.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballleague.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;

public class StatsAdapter2 extends FirebaseRecyclerAdapter
        <Player, StatsAdapter2.statsViewholder> {

    public StatsAdapter2(@NonNull FirebaseRecyclerOptions<Player> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StatsAdapter2.statsViewholder holder,
                                    int position, @NonNull Player model)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");
        holder.name.setText(model.getName());
        holder.team.setText(model.getTeamName());
        holder.assists.setText(String.valueOf(model.getAssists()));
    }

    @Override
    public StatsAdapter2.statsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stats_item,parent,false);
        return new StatsAdapter2.statsViewholder(view);
    }

    public class statsViewholder extends
            RecyclerView.ViewHolder
    {
        TextView name,team,assists;
        public statsViewholder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.player_stats_name_tv);
            team=itemView.findViewById(R.id.player_stats_team_name_tv);
            assists=itemView.findViewById(R.id.player_stats_goals_tv);
        }
    }
}
