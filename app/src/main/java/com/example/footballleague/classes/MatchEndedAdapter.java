package com.example.footballleague.classes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballleague.R;
import com.example.footballleague.ShowEndedMatch;
import com.example.footballleague.ShowMatch;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class MatchEndedAdapter extends FirebaseRecyclerAdapter<Matches, MatchEndedAdapter.matchesViewholder>
{
    public MatchEndedAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Matches> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MatchEndedAdapter.matchesViewholder holder, int position, @NonNull @NotNull Matches model)
    { FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Matches");
        holder.team1Tv.setText(model.getTeam1name());
        holder.team2Tv.setText(model.getTeam2name());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.result1.setText(model.getTeam1goals());
        holder.result2.setText(model.getTeam2goals());
        holder.ballloding.setVisibility(View.GONE);
        Picasso.get().load(model.getTeam1uri()).into(holder.team1img);
        Picasso.get().load(model.getTeam2uri()).into(holder.team2img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), ShowEndedMatch.class);
                intent.putExtra("match",model);
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public MatchEndedAdapter.matchesViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item,parent,false);
        return new matchesViewholder(view);
    }

    public class matchesViewholder extends RecyclerView.ViewHolder {

        ImageView team1img,team2img;
        TextView team1Tv,team2Tv,date,time,result1,result2;
        ImageView ballloding;
        public matchesViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            ballloding=itemView.findViewById(R.id.ballloding);
            team1img=itemView.findViewById(R.id.team1Rvimg);
            team2img=itemView.findViewById(R.id.team2Rvimg);
            team1Tv=itemView.findViewById(R.id.team1Rvtv);
            team2Tv=itemView.findViewById(R.id.team2Rvtv);
            date=itemView.findViewById(R.id.TvRvMatchDate);
            time=itemView.findViewById(R.id.TvRvMatchTime);
            result1=itemView.findViewById(R.id.team1RvResult);
            result2=itemView.findViewById(R.id.team2RvResult);

        }
    }
}
