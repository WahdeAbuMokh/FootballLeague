package com.example.footballleague.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
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

public class TableAdapter extends FirebaseRecyclerAdapter
        <Team,TableAdapter.tableViewholder> {

    int[]images={
            R.drawable.team1,
            R.drawable.team2,
            R.drawable.team3,
            R.drawable.team4,
            R.drawable.team5,
            R.drawable.team6,
            R.drawable.team7,
            R.drawable.team8,
            R.drawable.team9,
            R.drawable.team10,
            R.drawable.team11,
            R.drawable.team12,
            R.drawable.team13,
            R.drawable.team14,
            R.drawable.team15,
            R.drawable.team16,
            R.drawable.team17,
            R.drawable.team18,
            R.drawable.team19,
            R.drawable.team20



    };
    String[]names={

            "Liverpool",
            "Manchester United",
            "Manchester City",
            "Chelsea",
            "Arsenal",
            "Newcastle United",
            "Aston Villa",
            "Norwich City",
            "Leicester City",
            "Leeds United",
            "Tottenham Hotspur",
            "West Ham United",
            "Wolverhampton Wanderers",
            "Watford",
            "Burnley",
            "Brighton and Hove Albion",
            "Everton",
            "Brentford",
            "Crystal Palace",
            "Southampton"
    };

    public TableAdapter(@NonNull FirebaseRecyclerOptions<Team> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TableAdapter.tableViewholder holder,
                                    int position, @NonNull Team model)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Team");
        holder.teamName.setText(model.getTeamName());
        holder.mp.setText(model.getMatchesPlayed());
        holder.goalDifferance.setText(model.getGoalsDifference());
        holder.win.setText(model.getWins());
        holder.draw.setText(model.getDraws());
        holder.lose.setText(model.getLoses());
        holder.points.setText(String.valueOf(model.getPoints()));

    }


    @Override
    public TableAdapter.tableViewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_raw_item_rv,parent,false);
        return new TableAdapter.tableViewholder(view);
    }

    public class tableViewholder
            extends RecyclerView.ViewHolder
    {
        TextView teamName,goalDifferance,win,lose,draw,points,mp;
        TableRow row;
        public tableViewholder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            teamName=itemView.findViewById(R.id.team_name_tv);
            goalDifferance=itemView.findViewById(R.id.team_goalDifferance_tv);
            win=itemView.findViewById(R.id.team_win_tv);
            lose=itemView.findViewById(R.id.team_lose_tv);
            row=itemView.findViewById(R.id.rowkey);
            draw=itemView.findViewById(R.id.team_draw_tv);
            mp=itemView.findViewById(R.id.mp);
            points=itemView.findViewById(R.id.team_points_tv);

        }
    }
}
