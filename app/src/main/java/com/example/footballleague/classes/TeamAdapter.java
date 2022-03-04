package com.example.footballleague.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballleague.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class TeamAdapter extends FirebaseRecyclerAdapter
        <Team,TeamAdapter.teamsViewholder> {

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
    ItemClickListener itemClickListener;
    public TeamAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Team> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull TeamAdapter.teamsViewholder holder,
                                    int position, @NonNull @NotNull Team model)
    {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Team");
       Picasso.get().load(model.getUri()).into(holder.teamImg);

        //if(position<names.length){
          //  holder.teamName.setText(names[position]);
           // holder.teamImg.setImageResource(images[position]);
        //}
        //else
            holder.teamName.setText(model.getTeamName());

        holder.teamName.setTag(R.integer.player,model);//model.getKey());
        holder.teamName.setText(model.getTeamName());
        holder.teamManger.setText(model.getManger());
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.scrollView.getVisibility()== View.VISIBLE)
                {
                    holder.scrollView.setVisibility(View.GONE);

                }
                else if(holder.scrollView.getVisibility()== View.GONE)
                {   DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
                    holder.scrollView.setVisibility(View.VISIBLE);
                    Query query=ref2.child("Players").orderByChild("teamKey").equalTo(model.getKey());
                    FirebaseRecyclerOptions<Player> options
                            = new FirebaseRecyclerOptions.Builder<Player>().setQuery(query,Player.class).build();
                    holder.adapter=new PlayerAdapter(options);
                    holder.recyclerView.setAdapter(holder.adapter);
                    holder.adapter.startListening();
                }

            }
        });


    }


    @NonNull
    @NotNull
    @Override
    public teamsViewholder
    onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                       int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clubs_item,parent,false);
        return new TeamAdapter.teamsViewholder(view);
    }



    class teamsViewholder
            extends RecyclerView.ViewHolder implements View.OnClickListener
    {   TextView teamName,teamManger;
        Button show;
        ScrollView scrollView;
        ImageView teamImg;
        RecyclerView recyclerView;
        PlayerAdapter adapter;
        LinearLayout item;

        public teamsViewholder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            teamName=itemView.findViewById(R.id.ClubName_Tv);
            item=itemView.findViewById(R.id.item);
            teamManger=itemView.findViewById(R.id.ClubManger_Tv);
            show=itemView.findViewById(R.id.DropDown_btn);
            scrollView=itemView.findViewById(R.id.Club_Players_ScrollView);
            teamImg=itemView.findViewById(R.id.ClubImg);
            recyclerView=itemView.findViewById(R.id.Club_Players_recycle_view);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public   void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}