package com.example.hethongnhakinhiot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity  {
    ImageView img_weather,img_detect,img_detect_gas;
    TextView txt_weather,time_detect_gas,time_detect_human;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference weather=database.getReference();
    DatabaseReference gas=database.getReference();
    DatabaseReference detect_human=database.getReference();
    DatabaseReference control_door=database.getReference();
    DatabaseReference control=database.getReference();
    ViewPager2 viewPager;
    adapter adapter;
    adapter2 adapter2;
    adapter3 adapter3;
    adapter4 adapter4;
    ImageButton btn,btn2,btn3,btn4,clock;
    RelativeLayout r1,r2,r3,r4;
    LinearLayout l1,l2;
    BottomSheetDialog dialog,dialog_human;
    int page = 1;
    private long backPressedTime;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_weather=findViewById(R.id.weather);
        img_weather=findViewById(R.id.img_weather);
        clock=findViewById(R.id.clock);
        img_detect=findViewById(R.id.detect_human);
        img_detect_gas=findViewById(R.id.detect_gas);
        dialog=new BottomSheetDialog(this);
        dialog_human=new BottomSheetDialog(this);
        bottomsheet();
        bottomsheet1();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog_human.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        l2=findViewById(R.id.a11);
        l1=findViewById(R.id.a10);
        l1.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.INVISIBLE);
        Animation nAnimation = new AlphaAnimation(1, 0);
        nAnimation.setDuration(200);
        nAnimation.setRepeatCount(Animation.INFINITE);
        nAnimation.setRepeatMode(Animation.REVERSE);
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(200);
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_timer();
            }
        });
        detect_human.child("CONTROL/detect_human").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer detect_person=Integer.parseInt(snapshot.getValue().toString());
                if (detect_person==1){
                    l2.startAnimation(nAnimation);
                    l2.setVisibility(View.VISIBLE);
                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE , dd-MM-yyyy hh:mm:ss a");
                    String datetime=simpleDateFormat.format(calendar.getTime());
                    time_detect_human.setText(datetime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        img_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.INVISIBLE);
                nAnimation.cancel();
                dialog_human.show();
                control_door.child("CONTROL/detect_human").setValue(0);
                control.child("CONTROL/protect_door").setValue(0);
            }
        });
        img_detect_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.INVISIBLE);
                mAnimation.cancel();
                dialog.show();
            }
        });
        gas.child("CONTROL/detect_gas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer detect_gas=Integer.parseInt(snapshot.getValue().toString());
                if( detect_gas==1)
                {
                    l1.startAnimation(mAnimation);
                    l1.setVisibility(View.VISIBLE);
                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE , dd-MM-yyyy hh:mm:ss a");
                    String datetime=simpleDateFormat.format(calendar.getTime());
                    time_detect_gas.setText(datetime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        weather.child("DATA/Night").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer thoitiet=Integer.parseInt(snapshot.getValue().toString());
                if(thoitiet==0)
                {
                    img_weather.setImageResource(R.drawable.sun);
                    txt_weather.setText("Sunny");
                }
                else {
                    img_weather.setImageResource(R.drawable.moon);
                    txt_weather.setText("Night");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager = findViewById(R.id.view_pager);
        adapter = new adapter(this);
        adapter2=new adapter2(this);
        adapter3=new adapter3(this);
        adapter4=new adapter4(this);
        viewPager.setAdapter(adapter);
        r1=findViewById(R.id.r12);
        r2=findViewById(R.id.r13);
        r3=findViewById(R.id.r16);
        r4=findViewById(R.id.r18);
        btn=findViewById(R.id.btn_next);
        btn2=findViewById(R.id.btn_next2);
        btn3=findViewById(R.id.btn_next3);
        btn4=findViewById(R.id.btn_next4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(adapter);
                r1.setBackgroundResource(R.drawable.vien_img);
                r2.setBackgroundResource(R.drawable.vien_img_default);
                r3.setBackgroundResource(R.drawable.vien_img_default);
                r4.setBackgroundResource(R.drawable.vien_img_default);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(adapter2);
                r2.setBackgroundResource(R.drawable.vien_img3);
                r1.setBackgroundResource(R.drawable.vien_img_default);
                r3.setBackgroundResource(R.drawable.vien_img_default);
                r4.setBackgroundResource(R.drawable.vien_img_default);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(adapter3);
                r3.setBackgroundResource(R.drawable.vien_img2);
                r1.setBackgroundResource(R.drawable.vien_img_default);
                r2.setBackgroundResource(R.drawable.vien_img_default);
                r4.setBackgroundResource(R.drawable.vien_img_default);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(adapter4);
                r4.setBackgroundResource(R.drawable.vien_img4);
                r1.setBackgroundResource(R.drawable.vien_img_default);
                r2.setBackgroundResource(R.drawable.vien_img_default);
                r3.setBackgroundResource(R.drawable.vien_img_default);

            }
        });

    }

    private void bottomsheet() {
        View view =getLayoutInflater().inflate(R.layout.bottom_sheet,null,false);
        time_detect_gas=view.findViewById(R.id.time);
        dialog.setContentView(view);
    }
    private void bottomsheet1() {
        View view =getLayoutInflater().inflate(R.layout.bottom_sheet_human,null,false);
        time_detect_human=view.findViewById(R.id.time_human);
        dialog_human.setContentView(view);
    }

    private void openDialog_timer() {
        dialog_timer dialog=new dialog_timer();
        dialog.show(this.getSupportFragmentManager()," ");
    }
    @Override
    public void onBackPressed() {
        if (page == 1){
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed();
                return;
            }
            else {
                Toast.makeText(getBaseContext(), "Ấn hai lần để thoát",Toast.LENGTH_SHORT).show();
            }
            backPressedTime = System.currentTimeMillis();
        }
        else{
            page --;
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    public interface ViewPropertyAnimatorListener {
        /**
         * <p>Notifies the start of the animation.</p>
         *
         * @param view The view associated with the ViewPropertyAnimator
         */
        void onAnimationStart(View view);
        /**
         * <p>Notifies the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param view The view associated with the ViewPropertyAnimator
         */
        void onAnimationEnd(View view);
        /**
         * <p>Notifies the cancellation of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param view The view associated with the ViewPropertyAnimator
         */
        void onAnimationCancel(View view);
    }


}