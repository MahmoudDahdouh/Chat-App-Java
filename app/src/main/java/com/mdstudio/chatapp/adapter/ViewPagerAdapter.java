package com.mdstudio.chatapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mdstudio.chatapp.view.fragment.LoginFragment;
import com.mdstudio.chatapp.view.fragment.SignUpFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new SignUpFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
