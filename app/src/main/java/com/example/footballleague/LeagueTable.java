package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.footballleague.classes.TableAdapter;
import com.example.footballleague.classes.Team;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;
import java.util.Date;

public class LeagueTable extends AppCompatActivity {
    private RecyclerView recyclerView;
    TableAdapter adapter;
    DatabaseReference ref;
    LinearLayoutManager linearLayoutManager;
    TextView season;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        season=findViewById(R.id.head);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        Date date;
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        season.setText(String.format("Season %s/%s",year-1,year));
        ref = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycle_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(LeagueTable.this));
        Query query=ref.child("Team").orderByChild("points").limitToFirst(20);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        FirebaseRecyclerOptions<Team> options
                = new FirebaseRecyclerOptions.Builder<Team>().setQuery(query, Team.class).build();
        adapter=new TableAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }
}
