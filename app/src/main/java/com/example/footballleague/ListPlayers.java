package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballleague.classes.Player;
import com.example.footballleague.classes.PlayerAdapter;
import com.example.footballleague.classes.Team;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ListPlayers extends AppCompatActivity {

    DatabaseReference ref;
    Team team;
    int index;
    Bundle bundle;
    ImageView teamLogo;
    PlayerAdapter playerAdapter;
    RecyclerView recyclerView;
    TextView namTeam;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_players);
        recyclerView=(RecyclerView)findViewById(R.id.Club_Players_recycle_view);
        teamLogo=(ImageView)findViewById(R.id.team);
        namTeam=(TextView)findViewById(R.id.nameteam);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bundle=getIntent().getExtras();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        team=(Team)bundle.get("team");
        index=(int)bundle.get("index");
        Picasso.get().load(team.getUri()).into(teamLogo);
        namTeam.setText(team.getTeamName());
        ref= FirebaseDatabase.getInstance().getReference();
        Query query=ref.child("Players").orderByChild("teamKey").equalTo(team.getKey());
        FirebaseRecyclerOptions<Player> options
                = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query,Player.class).build();
        playerAdapter=new PlayerAdapter(options);
        recyclerView.setAdapter(playerAdapter);
        playerAdapter.startListening();
        //Toast.makeText(getApplicationContext(),String.valueOf(""),Toast.LENGTH_SHORT).show();
    }
}