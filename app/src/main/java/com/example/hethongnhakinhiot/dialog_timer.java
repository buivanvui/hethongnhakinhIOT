package com.example.hethongnhakinhiot;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dialog_timer extends AppCompatDialogFragment {
    DatabaseReference setup;
    EditText setup_hour,setup_minute,setup_day,setup_month,setup_year,setup_hour_2,setup_minute_2,setup_day_2,setup_month_2,setup_year_2;
    Switch sw_start,sw_start_2;
    ImageButton imb_timer,imb_calendar,imb_timer_2,imb_calendar_2;
    Calendar calendar;
    TimePickerDialog timePickerDialog,timePickerDialog_2;
    int currentHour,currentHour_2;
    int currentMinute,currentMinute_2;
    @SuppressLint("MissingInflatedId")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_time, null);
        builder.setView(view);

        setup_hour=view.findViewById(R.id.edt_hour);
        setup_minute=view.findViewById(R.id.edt_minute);
        setup_day=view.findViewById(R.id.edt_day);
        setup_month=view.findViewById(R.id.edt_month);
        setup_year=view.findViewById(R.id.edt_year);
        imb_timer=view.findViewById(R.id.img_timer);
        imb_calendar=view.findViewById(R.id.img_calendar);
        sw_start=view.findViewById(R.id.sw_timer);
        setup_hour_2=view.findViewById(R.id.edt_hour_outside);
        setup_minute_2=view.findViewById(R.id.edt_minute_outside);
        setup_day_2=view.findViewById(R.id.edt_day_outside);
        setup_month_2=view.findViewById(R.id.edt_month_outside);
        setup_year_2=view.findViewById(R.id.edt_year_outside);
        imb_timer_2=view.findViewById(R.id.img_timer_outside);
        imb_calendar_2=view.findViewById(R.id.img_calendar_outside);
        sw_start_2=view.findViewById(R.id.sw_timer_outside);
        setup= FirebaseDatabase.getInstance().getReference("SETTING");
        sw_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_start.isChecked()) {
                    setup.child("setting_time_lamp_livingroom").setValue(1);
                } else {
                    setup.child("setting_time_lamp_livingroom").setValue(0);
                }
            }
        });
        sw_start_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_start_2.isChecked()) {
                    setup.child("setting_time_lamp_outside").setValue(1);
                } else {
                    setup.child("setting_time_lamp_outside").setValue(0);
                }
            }
        });
        setup.child("setting_time_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer check_time_lamp_livingroom=Integer.parseInt(snapshot.getValue().toString());
                if(check_time_lamp_livingroom==1){
                    sw_start.setChecked(true);
                }
                else {
                    sw_start.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_time_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer check_time_lamp_outside=Integer.parseInt(snapshot.getValue().toString());
                if(check_time_lamp_outside==1){
                    sw_start_2.setChecked(true);
                }
                else {
                    sw_start_2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_year_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_year.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_year_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_year_2.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_month_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_month.setText(snapshot.getValue().toString()+"/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_month_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_month_2.setText(snapshot.getValue().toString()+"/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_day_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_day.setText(snapshot.getValue().toString()+"/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_day_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_day_2.setText(snapshot.getValue().toString()+"/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_hour_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_hour.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_hour_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_hour_2.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_minute_lamp_livingroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_minute.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setup.child("setting_minute_lamp_outside").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setup_minute_2.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imb_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    setup_day.setText(String.format("%d",dayOfMonth)+"/");
                    setup_month.setText(String.format("%d",month+1)+"/");
                    setup_year.setText(String.format("%d",year));
                    setup.child("setting_year_lamp_livingroom").setValue(year);
                    setup.child("setting_month_lamp_livingroom").setValue(month+1);
                    setup.child("setting_day_lamp_livingroom").setValue(dayOfMonth);
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        imb_calendar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    setup_day_2.setText(String.format("%d",dayOfMonth)+"/");
                    setup_month_2.setText(String.format("%d",month+1)+"/");
                    setup_year_2.setText(String.format("%d",year));
                    setup.child("setting_year_lamp_outside").setValue(year);
                    setup.child("setting_month_lamp_outside").setValue(month+1);
                    setup.child("setting_day_lamp_outside").setValue(dayOfMonth);
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        imb_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                currentHour=calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute=calendar.get(Calendar.MINUTE);
                timePickerDialog=new TimePickerDialog(getActivity(),(timerPicker,hourofDay,minutes) ->{
                    setup_hour.setText(String.format("%d",hourofDay));
                    setup_minute.setText(String.format("%d",minutes));
                    setup.child("setting_hour_lamp_livingroom").setValue(hourofDay);
                    setup.child("setting_minute_lamp_livingroom").setValue(minutes);
                },currentHour,currentMinute,false );
                timePickerDialog.show();
            }
        });
        imb_timer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                currentHour_2=calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute_2=calendar.get(Calendar.MINUTE);
                timePickerDialog_2=new TimePickerDialog(getActivity(),(timerPicker,hourofDay,minutes) ->{
                    setup_hour_2.setText(String.format("%d",hourofDay));
                    setup_minute_2.setText(String.format("%d",minutes));
                    setup.child("setting_hour_lamp_outside").setValue(hourofDay);
                    setup.child("setting_minute_lamp_outside").setValue(minutes);
                },currentHour_2,currentMinute_2,false );
                timePickerDialog_2.show();
            }
        });
        return builder.create();

    }
}
