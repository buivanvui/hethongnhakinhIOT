package com.example.hethongnhakinhiot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class light_fragment extends Fragment {
    DatabaseReference control,setup,count;
    Switch swLIGHT_1, swcontrol_1,swLIGHT_2,swcontrol_2;
    ImageView imgbtn_light_lvr,imgbtn_light_outside;
    RelativeLayout r1;
    TextView thongsocaianhang;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.light_fragment, container, false);
        swLIGHT_1 = root.findViewById(R.id.sw);
        swcontrol_1=root.findViewById(R.id.swcontrol);
        swLIGHT_2 = root.findViewById(R.id.sw2);
        swcontrol_2=root.findViewById(R.id.swcontrol2);
        thongsocaianhang=root.findViewById(R.id.d);
        r1=root.findViewById(R.id.r8);
        imgbtn_light_lvr=root.findViewById(R.id.imageView);
        imgbtn_light_outside=root.findViewById(R.id.imageView2);

        setup=FirebaseDatabase.getInstance().getReference("SETTING");
        control =FirebaseDatabase.getInstance().getReference("CONTROL");
        count =FirebaseDatabase.getInstance().getReference("COUNT_DEVICE");

        imgbtn_light_lvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });
        imgbtn_light_outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });
        swLIGHT_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swLIGHT_1.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Trạng thái đèn phòng khách: " + swLIGHT_1.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    control.child("btn_lamp1").setValue(1);
                    imgbtn_light_lvr.setImageResource(R.drawable.light_open);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Trạng thái đèn phòng khách: " + swLIGHT_1.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    control.child("btn_lamp1").setValue(0);
                    imgbtn_light_lvr.setImageResource(R.drawable.light_close);
                }
            }
        });
        swLIGHT_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swLIGHT_2.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Trạng thái đèn ngoài trời: " + swLIGHT_2.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    control.child("btn_lamp2").setValue(1);
                    imgbtn_light_outside.setImageResource(R.drawable.light_open);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Trạng thái đèn ngoài trời: " + swLIGHT_2.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    control.child("btn_lamp2").setValue(0);
                    imgbtn_light_outside.setImageResource(R.drawable.light_close);
                }
            }
        });
        control.child("btn_lamp1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer check_lamp1=Integer.parseInt(snapshot.getValue().toString());
                if(check_lamp1==1){
                    imgbtn_light_lvr.setImageResource(R.drawable.light_open);
                    swLIGHT_1.setChecked(true);
                }
                else {
                    imgbtn_light_lvr.setImageResource(R.drawable.light_close);
                    swLIGHT_1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("btn_lamp2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer check_lamp2=Integer.parseInt(snapshot.getValue().toString());
                if(check_lamp2==1){
                    imgbtn_light_outside.setImageResource(R.drawable.light_open);
                    swLIGHT_2.setChecked(true);

                }
                else {
                    imgbtn_light_outside.setImageResource(R.drawable.light_close);
                    swLIGHT_2.setChecked(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        r1.setVisibility(View.INVISIBLE);

        swcontrol_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swcontrol_1.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol_1.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    control.child("AUTO_LIGHT_LIVINGROOM").setValue(1);
                    control.child("MANUAL_LIGHT_LIVINGROOM").setValue(0);
                    swLIGHT_1.setVisibility(View.INVISIBLE);


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol_1.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    control.child("MANUAL_LIGHT_LIVINGROOM").setValue(1);
                    control.child("AUTO_LIGHT_LIVINGROOM").setValue(0);
                    swLIGHT_1.setVisibility(View.VISIBLE);
                    control.child("Auto_lamp_livingroom").setValue(0);

                }
            }
        });
        swcontrol_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swcontrol_2.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol_2.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    control.child("AUTO_LIGHT_OUTSIDE").setValue(1);
                    control.child("MANUAL_LIGHT_OUTSIDE").setValue(0);
                    swLIGHT_2.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Chế độ " + swcontrol_2.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    control.child("MANUAL_LIGHT_OUTSIDE").setValue(1);
                    control.child("AUTO_LIGHT_OUTSIDE").setValue(0);
                    swLIGHT_2.setVisibility(View.VISIBLE);
                    control.child("Auto_lamp_outside").setValue(0);
                }
            }
        });

        control.child("Auto_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer tudong=Integer.parseInt(snapshot.getValue().toString());
                if(tudong==1)
                {
                    imgbtn_light_lvr.setImageResource(R.drawable.light_open);
                    count.child("COUNT_LAMP_LIVINGROOM").setValue(1);
                }
                else
                {
                    imgbtn_light_lvr.setImageResource(R.drawable.light_close);
                    count.child("COUNT_LAMP_LIVINGROOM").setValue(0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("Auto_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer tudong=Integer.parseInt(snapshot.getValue().toString());
                if(tudong==1)
                {
                    imgbtn_light_outside.setImageResource(R.drawable.light_open);
                    count.child("COUNT_LAMP_OUTSIDE").setValue(1);
                }
                else
                {
                    imgbtn_light_outside.setImageResource(R.drawable.light_close);
                    count.child("COUNT_LAMP_OUTSIDE").setValue(0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("MANUAL_LIGHT_LIVINGROOM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer bangtay=Integer.parseInt(snapshot.getValue().toString());
                if(bangtay==0)
                {
                    imgbtn_light_lvr.setImageResource(R.drawable.light_close);
                    swLIGHT_1.setChecked(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control.child("MANUAL_LIGHT_OUTSIDE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer bangtay=Integer.parseInt(snapshot.getValue().toString());
                if(bangtay==0)
                {
                    imgbtn_light_outside.setImageResource(R.drawable.light_close);
                    swLIGHT_2.setChecked(false);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
    private void openDialog1() {
        dialog_anhsang dialog=new dialog_anhsang();
        dialog.show(getActivity().getSupportFragmentManager()," ");
    }
}
