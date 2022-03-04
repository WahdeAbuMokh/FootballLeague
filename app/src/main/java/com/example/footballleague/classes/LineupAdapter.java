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
import com.google.firebase.storage.FirebaseStorage;

public class LineupAdapter extends FirebaseRecyclerAdapter<Player,LineupAdapter.LineUpViewholder> {

    public LineupAdapter(@NonNull FirebaseRecyclerOptions<Player> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LineupAdapter.LineUpViewholder holder, int position, @NonNull Player model) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");
        holder.playerName.setText(model.getName());
        holder.number.setText(model.getShirtNumber());
    }

    @NonNull

    @Override
    public LineUpViewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player2_item,parent,false);
        return new LineupAdapter.LineUpViewholder(view);
    }

    public class LineUpViewholder extends RecyclerView.ViewHolder {

        TextView playerName,number;
        public LineUpViewholder(@NonNull View itemView)
        {
            super(itemView);
            playerName=itemView.findViewById(R.id.ClubPlayerName_Tv2);
            number=itemView.findViewById(R.id.ClubPlayerNumber_Tv2);
        }
    }
}
