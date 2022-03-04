package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballleague.classes.Player;
import com.example.footballleague.classes.TableAdapter;
import com.example.footballleague.classes.Team;
import com.example.footballleague.classes.TeamAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ShowClubs extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseReference ref;
    TeamAdapter adapter;
    TextView season;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clubs);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        ref = FirebaseDatabase.getInstance().getReference("Team");
        recyclerView = findViewById(R.id.Clubs_recycle_view);
        season=findViewById(R.id.head);
        Date date;
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        season.setText(String.format("Season %s/%s",year-1,year));
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowClubs.this));
        FirebaseRecyclerOptions<Team> options
                = new FirebaseRecyclerOptions.Builder<Team>().setQuery(ref, Team.class).build();
        adapter=new TeamAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new TeamAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                 View view1=view;
                 View teamName=view1.findViewById(R.id.ClubName_Tv);
                 LinearLayout linearLayout=view1.findViewById(R.id.item);
                 Team team=(Team)teamName.getTag(R.integer.player);
                linearLayout.setPressed(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        linearLayout.setPressed(false);
                        openIntent(team,position);
                    }
                },400);


            }
        });
        adapter.startListening();



    }
  void  openIntent(Team team,int position){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent playerinfo;
                playerinfo=new Intent(ShowClubs.this, ListPlayers.class);
                playerinfo.putExtra("index",position);
                playerinfo.putExtra("team",team);
                startActivity(playerinfo);
            }
        },200);
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