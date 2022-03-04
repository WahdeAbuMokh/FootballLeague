package com.example.footballleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballleague.classes.Matches;
import com.example.footballleague.classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class PlayerInfo extends AppCompatActivity {

    ImageView playerImg;
    Button exit;
    TextView name,age,shirt,redCard,yellowCard,goals,assists,teamName,appearances;
    Player player,player1;
    ImageView iconLogo;
    Intent intent;
     int index;
    Bundle bundle;
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
        setContentView(R.layout.activity_player_info);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        intent=getIntent();
        player= (Player) intent.getSerializableExtra("Players");
        bundle=getIntent().getExtras();
        index=(int)bundle.get("index");
      //  iconLogo=findViewById(R.id.iconLogo);
      //  iconLogo.setImageResource(images[index]);
        name=findViewById(R.id.PlayerInfoName);
        appearances=findViewById(R.id.PlayerInfoAppearances);
        teamName=findViewById(R.id.PlayerInfoTeamName);
        age=findViewById(R.id.PlayerInfoAge);
        shirt=findViewById(R.id.PlayerInfoShirtNumber);
        redCard=findViewById(R.id.PlayerInfoRedCard);
        yellowCard=findViewById(R.id.PlayerInfoYellowCards);
        goals=findViewById(R.id.PlayerInfoGoals);
        assists=findViewById(R.id.PlayerInfoAssists);
        exit=findViewById(R.id.btnExitInfo);
        playerImg=findViewById(R.id.PlayerInfoImg);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot)
            {
                for(DataSnapshot item : snapshot.getChildren())
                {
                    if(item.child("key").getValue(String.class).equals(player.getKey()))
                    {
                        player1=item.getValue(Player.class);
                        Picasso.get().load(player.getUri()).into(playerImg);
                        name.setText(player1.getName());
                        teamName.setText(player1.getTeamName());
                        age.setText(player1.getAge());
                        appearances.setText(player1.getAppearances());
                        shirt.setText(player1.getShirtNumber());
                        redCard.setText(player1.getRedCards());
                        yellowCard.setText(player1.getYellowCards());
                        goals.setText(String.valueOf(player1.getGoals()));
                        assists.setText(String.valueOf(player1.getAssists()));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(PlayerInfo.this, "Error Uploading data Please wait", Toast.LENGTH_SHORT).show();
            }

        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}