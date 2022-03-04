package com.example.footballleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    Button btntable, btnMatch,btnStatistics,btnClubs, btnLocation;
    TextView Tv_lat, Tv_long, Tv_country, Tv_locality, Tv_Address;
    FusedLocationProviderClient fusedLocationProviderClient;
    BottomSheetDialog bottomSheetDialog;
    LayoutInflater layoutInflater;
    ImageView btnBottomsheet;


    public void setBottom(){
        layoutInflater=LayoutInflater.from(getApplicationContext());
        bottomSheetDialog=new BottomSheetDialog(MainActivity.this);//,R.style.Theme_Design_Light);
        View view1=layoutInflater.inflate(R.layout.bottom_sheet,null);
        RelativeLayout relativeLayout=view1.findViewById(R.id.view1);
        //Toast.makeText(getApplicationContext(),String.valueOf(relativeLayout.getId()),Toast.LENGTH_SHORT).show();

        bottomSheetDialog.setContentView(view1);

        btntable = bottomSheetDialog.findViewById(R.id.btngo);
        btnMatch = bottomSheetDialog.findViewById(R.id.btnMatch);
        btnStatistics=bottomSheetDialog.findViewById(R.id.btnStatistics);
        btnClubs=bottomSheetDialog.findViewById(R.id.btnClubs);
        //Toast.makeText(getApplicationContext(),String.valueOf(btntable.getText()),Toast.LENGTH_SHORT).show();
//        btntable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"dd",Toast.LENGTH_SHORT).show();
//            }
//        });
        btntable.setOnClickListener(this);
        btnMatch.setOnClickListener(this);
        btnStatistics.setOnClickListener(this);
        btnClubs.setOnClickListener(this);

    }
    float y=0;//event.getY();
    float ycurrent=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction()==MotionEvent.ACTION_DOWN){
            y=event.getY();

        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            ycurrent=event.getY();
            y-=ycurrent;
            if(y>=400){
                bottomSheetDialog.show();
            }
           // Toast.makeText(getApplicationContext(),String.format("%s-%s",y,ycurrent),Toast.LENGTH_SHORT).show();

        }
        return super.onTouchEvent(event);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        setBottom();

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Tv_lat.setText(String.valueOf(addresses.get(0).getLatitude()));
                        Tv_long.setText(String.valueOf(addresses.get(0).getLongitude()));
                        Tv_country.setText(String.valueOf(addresses.get(0).getCountryName()));
                        Tv_locality.setText(String.valueOf(addresses.get(0).getLocality()));
                        Tv_Address.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        Intent intent=null;

        switch (view.getId()){
            case  R.id.btngo:
                intent = new Intent(MainActivity.this, LeagueTable.class);
                break;
            case  R.id.btnMatch:
                intent = new Intent(MainActivity.this, LeagueMatches.class);
                break;
            case  R.id.btnStatistics:
                intent = new Intent(MainActivity.this, LeagueStatistics.class);
                break;
            case  R.id.btnClubs:
                intent=new Intent(MainActivity.this, ShowClubs.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}