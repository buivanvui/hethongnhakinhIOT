package com.example.hethongnhakinhiot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fan_fragment extends Fragment {

    DatabaseReference control,setup,count;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference manu=database.getReference();
    DatabaseReference auto=database.getReference();
    DatabaseReference nhietdo=database.getReference();
    SwitchCompat swcontrol;
    Slider speed_fan;
    ImageButton imgbtn_fan;

    RelativeLayout r1,r2;
    TextView thongsocainhietdo,txt_speed_fan;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fan_fragment, container, false);
        speed_fan = root.findViewById(R.id.speed);
        txt_speed_fan=root.findViewById(R.id.txt_speed);
        swcontrol=root.findViewById(R.id.swcontrol);
        thongsocainhietdo=root.findViewById(R.id.a);
        r1=root.findViewById(R.id.r5);
        r2=root.findViewById(R.id.r1);
        imgbtn_fan=root.findViewById(R.id.imageView);
        
        setup=FirebaseDatabase.getInstance().getReference("SETTING");
        control =FirebaseDatabase.getInstance().getReference("CONTROL");
        count=FirebaseDatabase.getInstance().getReference("COUNT_DEVICE");
        imgbtn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });
        control.child("fan_speed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer check_speed=Integer.parseInt(snapshot.getValue().toString());
                if(check_speed==0){
                    speed_fan.setValue(0);
                    count.child("COUNT_FAN").setValue(0);
                }
                else if(check_speed==1){
                    speed_fan.setValue(1);
                    count.child("COUNT_FAN").setValue(1);
                }
                else if(check_speed==2){
                    speed_fan.setValue(2);
                    count.child("COUNT_FAN").setValue(1);
                }
                else if(check_speed==3){
                    speed_fan.setValue(3);
                    count.child("COUNT_FAN").setValue(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        speed_fan.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int value_speed= (int) value;
                txt_speed_fan.setText(Integer.toString(value_speed));
                if(value_speed==0){
                    control.child("fan_speed").setValue(0);
                    imgbtn_fan.setImageResource(R.drawable.fan_close);
                    count.child("COUNT_FAN").setValue(0);
                }
                else if(value_speed==1){
                    control.child("fan_speed").setValue(1);
                    count.child("COUNT_FAN").setValue(1);
                    imgbtn_fan.setImageResource(R.drawable.fan_open);
                }
                else if(value_speed==2){
                    control.child("fan_speed").setValue(2);
                    count.child("COUNT_FAN").setValue(1);
                    imgbtn_fan.setImageResource(R.drawable.fan_open);
                }
                else if(value_speed==3){
                    control.child("fan_speed").setValue(3);
                    count.child("COUNT_FAN").setValue(1);
                    imgbtn_fan.setImageResource(R.drawable.fan_open);
                }
            }
        });
        r1.setVisibility(View.INVISIBLE);
        swcontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swcontrol.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    control.child("AUTO_FAN").setValue(1);
                    control.child("MANUAL_FAN").setValue(0);
                    r2.setVisibility(View.INVISIBLE);
                    r1.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    control.child("MANUAL_FAN").setValue(1);
                    control.child("AUTO_FAN").setValue(0);
                    r1.setVisibility(View.INVISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    control.child("Auto_Fan").setValue(0);

                }
            }
        });

        auto.child("CONTROL/Auto_Fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer tudong=Integer.parseInt(snapshot.getValue().toString());
                if(tudong==1)
                {
                    imgbtn_fan.setImageResource(R.drawable.fan_open);
                    count.child("COUNT_FAN").setValue(1);
                }
                else
                {
                    imgbtn_fan.setImageResource(R.drawable.fan_close);
                    count.child("COUNT_FAN").setValue(0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        manu.child("CONTROL/AUTO_FAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer td=Integer.parseInt(snapshot.getValue().toString());
                if(td==1)
                {
                    r2.setVisibility(View.INVISIBLE);
                    r1.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        manu.child("CONTROL/MANUAL_FAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer bangtay=Integer.parseInt(snapshot.getValue().toString());
                if(bangtay==0)
                {
                    imgbtn_fan.setImageResource(R.drawable.fan_close);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nhietdo.child("SETTING/SETTING_TEMPERATURE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Temp) {
                thongsocainhietdo.setText(Temp.getValue().toString()+" ℃");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
    private void openDialog1() {
        dialog_nhietdo dialog=new dialog_nhietdo();
        dialog.show(getActivity().getSupportFragmentManager()," ");
    }
}
