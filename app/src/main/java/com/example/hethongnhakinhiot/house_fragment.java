package com.example.hethongnhakinhiot;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class house_fragment extends Fragment {
    DatabaseReference control;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference nhietdo=database.getReference();
    DatabaseReference doam=database.getReference();
    DatabaseReference count=database.getReference();
   TextView temp,humi,temp1,count_lamp_lvr,count_lamp_outside,count_fan;
   ImageView door;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.house_fragment, container, false);


        temp=root.findViewById(R.id.nhietdo);
        temp1=root.findViewById(R.id.nhietdo1);
        humi=root.findViewById(R.id.doam);
        door=root.findViewById(R.id.door);
        count_lamp_lvr=root.findViewById(R.id.count_light);
        count_lamp_outside=root.findViewById(R.id.count_light1);
        count_fan=root.findViewById(R.id.count_fan);
        control =FirebaseDatabase.getInstance().getReference("CONTROL");
        control.child("Door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer door1=Integer.parseInt(snapshot.getValue().toString());
                if(door1==0){
                    door.setImageResource(R.drawable.exit_close);
                }
                else {
                    door.setImageResource(R.drawable.exit_open);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        count.child("COUNT_DEVICE/COUNT_LAMP_LIVINGROOM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count_lamp_lvr.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        count.child("COUNT_DEVICE/COUNT_LAMP_OUTSIDE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count_lamp_outside.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        count.child("COUNT_DEVICE/COUNT_FAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count_fan.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("btn_lamp1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count_lamp_lvr.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("btn_lamp2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count_lamp_outside.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("fan_speed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer fan=Integer.parseInt(snapshot.getValue().toString());
                if(fan==0)
                {
                    count_fan.setText(snapshot.getValue().toString());
                }
                else {
                    count_fan.setText("1");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nhietdo.child("DATA/Nhiệt độ").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Temp) {
                temp1.setText(Temp.getValue().toString() + " ℃");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        doam.child("DATA/Độ ẩm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Humi) {
                humi.setText(Humi.getValue().toString() + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}
