package com.example.hethongnhakinhiot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class adapter4 extends FragmentStateAdapter {


    public adapter4(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            default:
                return new door_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
