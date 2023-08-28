package com.example.hethongnhakinhiot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class door_fragment extends Fragment {
    DatabaseReference control_door;
    ImageView status_door,txt_door,protect_door;
    ImageButton imb_key;
    Switch protect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_door_fragment, container, false);

        imb_key=root.findViewById(R.id.img_key);
        status_door=root.findViewById(R.id.img_door);
        txt_door=root.findViewById(R.id.img_statusDoor);
        protect_door=root.findViewById(R.id.img_security);
        protect=root.findViewById(R.id.sw_protect_door);
        control_door =FirebaseDatabase.getInstance().getReference("CONTROL");
        imb_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog1();
            }
        });
        control_door.child("Door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              Integer door=Integer.parseInt(snapshot.getValue().toString());
              if(door==0){
                  status_door.setImageResource(R.drawable.exit_close);
                  txt_door.setImageResource(R.drawable.closed);
              }
              else {
                  status_door.setImageResource(R.drawable.exit_open);
                  txt_door.setImageResource(R.drawable.open);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        control_door.child("protect_door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer prt_door=Integer.parseInt(snapshot.getValue().toString());
                if(prt_door==1) {
                    protect_door.setImageResource(R.drawable.protection_open);
                    protect.setChecked(true);
                }
                if(prt_door==0) {
                    protect_door.setImageResource(R.drawable.protection);
                    protect.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(protect.isChecked()){
                    protect_door.setImageResource(R.drawable.protection_open);
                    control_door.child("protect_door").setValue(1);
                }
                else {
                    protect_door.setImageResource(R.drawable.protection);
                    control_door.child("protect_door").setValue(0);
                }
            }
        });
        return root;

    }

    private void openDialog1() {
        dialog_passkey dialog_passkey=new dialog_passkey();
        dialog_passkey.show(getActivity().getSupportFragmentManager(),"");
    }
}