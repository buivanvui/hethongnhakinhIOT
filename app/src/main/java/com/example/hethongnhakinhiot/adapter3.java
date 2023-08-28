package com.example.hethongnhakinhiot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class adapter3 extends FragmentStateAdapter {


    public adapter3(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            default:
                return new fan_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
