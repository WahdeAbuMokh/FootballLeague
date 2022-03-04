package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.footballleague.classes.MatchAdapter;
import com.example.footballleague.classes.MatchEndedAdapter;
import com.example.footballleague.classes.Matches;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeagueMatches extends AppCompatActivity {
    private RecyclerView recyclerView;
    MatchAdapter adapter1;
    MatchEndedAdapter adapter2;
    DatabaseReference ref,ref2;
    Button btnEndedMatches,btnCurrentMatches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_matches);
        ref = FirebaseDatabase.getInstance().getReference("Matches");
        ref2 = FirebaseDatabase.getInstance().getReference("Ended Matches");
        btnEndedMatches=findViewById(R.id.btnEndedMatches);
        btnCurrentMatches=findViewById(R.id.btnCurrentMatches);
        recyclerView =findViewById(R.id.Matches_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeagueMatches.this));
        FirebaseRecyclerOptions<Matches> options
                = new FirebaseRecyclerOptions.Builder<Matches>().setQuery(ref, Matches.class).build();
        adapter1=new MatchAdapter(options);

        FirebaseRecyclerOptions<Matches> options2
                = new FirebaseRecyclerOptions.Builder<Matches>().setQuery(ref2, Matches.class).build();
        adapter2=new MatchEndedAdapter(options2);
        recyclerView.setAdapter(adapter1);
        adapter1.startListening();
        btnCurrentMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setAdapter(adapter1);
                adapter2.stopListening();
                adapter1.startListening();
            }
        });
        btnEndedMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setAdapter(adapter2);
                adapter1.stopListening();
                adapter2.startListening();
            }
        });


    }
    @Override
    public void onStop() {
        super.onStop();
        adapter1.stopListening();
        adapter2.stopListening();

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter2.startListening();

    }
}