package com.example.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        switch(tabPosition){
            case 0:
                Profile_Tab profiletab = new Profile_Tab();
                return profiletab;
            case 1:

                return new Users_Tab();
            case 2:
                return new SharePictureTab();
            default:
                return null;

        }
     }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Picture";
            default:
                return null;
        }
     }
}
