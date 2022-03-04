package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DirectAction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.footballleague.classes.Player;
import com.example.footballleague.classes.StatsAdapter;
import com.example.footballleague.classes.StatsAdapter2;
import com.example.footballleague.classes.TableAdapter;
import com.example.footballleague.classes.Team;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;
import java.util.Date;

public class LeagueStatistics extends AppCompatActivity {

    private RecyclerView recyclerView;
    StatsAdapter adapter;
    StatsAdapter2 adapter2;
    DatabaseReference ref,ref2;
    Button btngoals,btnasissts;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;
    TextView stats;
    TextView season;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_statistics);
        btngoals=findViewById(R.id.btGoalsShow);
        btnasissts=findViewById(R.id.btnAssistShow);
        season=findViewById(R.id.head);
        Date date;
        Calendar calendar=Calendar.getInstance();



        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        int year=calendar.get(Calendar.YEAR);
        season.setText(String.format("Season %s/%s",year-1,year));
        stats=findViewById(R.id.GoalOrAssist);
        ref = FirebaseDatabase.getInstance().getReference();
        ref2 = FirebaseDatabase.getInstance().getReference();
        Query query=ref.child("Players").orderByChild("goals").limitToLast(10);
        Query query2=ref2.child("Players").orderByChild("assists").limitToLast(10);
        recyclerView = findViewById(R.id.statistic_recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(LeagueStatistics.this));

        FirebaseRecyclerOptions<Player> options
                = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query, Player.class).build();
        FirebaseRecyclerOptions<Player> options2
                = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query2, Player.class).build();
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        adapter=new StatsAdapter(options);
        adapter2=new StatsAdapter2(options2);
        recyclerView.setAdapter(adapter);
        adapter.startListening();



        btngoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stats.setText("Goals");
                recyclerView.setAdapter(adapter);
                adapter2.stopListening();
                adapter.startListening();

            }
        });
        btnasissts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stats.setText("Assists");
                recyclerView.setAdapter(adapter2);
                adapter.stopListening();
                adapter2.startListening();
            }
        });



    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();

    }
}
