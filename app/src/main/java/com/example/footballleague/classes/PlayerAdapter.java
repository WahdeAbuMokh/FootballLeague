package com.example.footballleague.classes;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballleague.PlayerInfo;
import com.example.footballleague.R;
import com.example.footballleague.ShowMatch;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class PlayerAdapter  extends FirebaseRecyclerAdapter<Player,PlayerAdapter.playersViewholder> {


    public PlayerAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Player> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull PlayerAdapter.playersViewholder holder
            , int position, @NonNull @NotNull Player model)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");
        holder.playerName.setText(model.getName());
        holder.number.setText(model.getShirtNumber());
        //Toast.makeText(holder.number.getContext(),String.valueOf(model.getName()),Toast.LENGTH_SHORT).show();
        Picasso.get().load(model.getUri()).into(holder.playerImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemClick.setPressed(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.itemClick.setPressed(false);
                        openIntent(view,model,position);
                    }
                },400);
            }
        });
    }
    void  openIntent(View view,Player player,int index){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(view.getContext(), PlayerInfo.class);
                intent.putExtra("Players",player);
                intent.putExtra("index",index);
                view.getContext().startActivity(intent);
            }
        },200);
    }
    @NonNull
    @NotNull
    @Override
    public playersViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item,parent,false);
        return new PlayerAdapter.playersViewholder(view);
    }

    class playersViewholder extends RecyclerView.ViewHolder
    {//variables
        ImageView playerImg;
        TextView  playerName,number;
        LinearLayout itemClick;
        public playersViewholder(@NonNull @NotNull View itemView)
        {
            super(itemView);

            //find variables by id
            playerName=itemView.findViewById(R.id.ClubPlayerName_Tv);
            number=itemView.findViewById(R.id.ClubPlayerNumber_Tv);
            playerImg=itemView.findViewById(R.id.ClubPlayerImg);
            itemClick=itemView.findViewById(R.id.item);

        }
    }
}