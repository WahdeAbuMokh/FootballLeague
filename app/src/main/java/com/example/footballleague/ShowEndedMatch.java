package com.example.footballleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.footballleague.classes.LineupAdapter;
import com.example.footballleague.classes.MatchEvent;
import com.example.footballleague.classes.Matches;
import com.example.footballleague.classes.Player;
import com.example.footballleague.classes.Team;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class ShowEndedMatch extends AppCompatActivity {

    ImageView team1img,team2img;
    TextView team1nameTv,team2nameTv,dateTv,timeTv,team1resultTv,team2resultTv,matchStatsTv,team1LineUpName,team2LineUpName;
    Button btnExit,btnDropDown;
    Team team1,team2,team3;
    Matches match,match1;
    Intent intent;
    RecyclerView team1Rv,team2Rv;
    ScrollView lineupScrollView;
    LineupAdapter adapter1,adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ended_match);
        intent=getIntent();
        match= (Matches) intent.getSerializableExtra("match");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        team1img=findViewById(R.id.team1StartMatchimgEnd);
        team2img=findViewById(R.id.team2StartMatchimgEnd);
        team1nameTv=findViewById(R.id.team1StartMatchtvEnd);
        team2nameTv=findViewById(R.id.team2StartMatchtvEnd);
        //dateTv=findViewById(R.id.TvStartMatchDateEnd);
       // timeTv=findViewById(R.id.TvStartMatchTimeEnd);
        team1resultTv=findViewById(R.id.TvStartMatchResult1End);
        team2resultTv=findViewById(R.id.TvStartMatchResult2End);
        matchStatsTv=findViewById(R.id.StatsTVEnd);
        btnExit=findViewById(R.id.btnExitMatchEnd);
        btnDropDown=findViewById(R.id.DropDown_btn2End);
        lineupScrollView=findViewById(R.id.lineup_ScrollViewEnd);
        team1Rv=findViewById(R.id.Team1_match_lineup_recycler_viewEnd);
        team2Rv=findViewById(R.id.Team2_match_lineup_recycler_viewEnd);
        team1LineUpName=findViewById(R.id.Tv_lineup_team1NameEnd);
        team2LineUpName=findViewById(R.id.Tv_lineup_team2NameEnd);
       Picasso.get().load(match.getTeam1uri()).into(team1img);
        Picasso.get().load(match.getTeam2uri()).into(team2img);
        team1nameTv.setText(match.getTeam1name());
        team2nameTv.setText(match.getTeam2name());
     //   dateTv.setText(match.getDate());
       // timeTv.setText(match.getTime());

        btnDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(lineupScrollView.getVisibility()== View.VISIBLE)
                {
                    lineupScrollView.setVisibility(View.GONE);

                }
                else if(lineupScrollView.getVisibility()== View.GONE)
                { lineupScrollView.setVisibility(View.VISIBLE);
                    team1LineUpName.setText(match1.getTeam1name());
                    team2LineUpName.setText(match1.getTeam2name());
                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                    Query query1=ref1.child("Players").orderByChild("teamKey").equalTo(match1.getTeam1Key());
                    FirebaseRecyclerOptions<Player> options1
                            = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query1,Player.class).build();
                    adapter1=new LineupAdapter(options1);
                    team1Rv.setAdapter(adapter1);
                    adapter1.startListening();

                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
                    Query query2=ref2.child("Players").orderByChild("teamKey").equalTo(match1.getTeam2Key());
                    FirebaseRecyclerOptions<Player> options2
                            = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query2,Player.class).build();
                    adapter2=new LineupAdapter(options2);
                    team2Rv.setAdapter(adapter2);
                    adapter2.startListening();

                }
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        databaseReference.child("Ended Matches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot)
            {
                for(DataSnapshot item : snapshot.getChildren())
                {
                    if(item.child("key").getValue(String.class).equals(match.getKey()))
                    {
                        match1=item.getValue(Matches.class);
                        team1resultTv.setText(match1.getTeam1goals().toString().trim());
                        team2resultTv.setText(match1.getTeam2goals().toString().trim());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });
        databaseReference.child("Match Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {matchStatsTv.setText(" ");
                for (DataSnapshot item : snapshot.getChildren()) {
                    if (item.child("matchKey").getValue(String.class).equals(match.getKey())) {
                        MatchEvent newEvent=item.getValue(MatchEvent.class);
                        String oldText=matchStatsTv.getText().toString();
                        String time= newEvent.getTime();
                        String event= newEvent.getEvent();
                        String playerName= newEvent.getPlayerName();
                        String teamName= newEvent.getTeamName();
                        String newText=oldText+"\n"+ time+" " + playerName + " got " + event  + " for " + teamName;
                        matchStatsTv.setText(newText);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}