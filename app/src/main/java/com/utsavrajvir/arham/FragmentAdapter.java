package com.utsavrajvir.arham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    String json_data2 = null;

    public FragmentAdapter(FragmentManager fm,String s1) {
        super(fm);

        json_data2 = s1;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("json_data", json_data2);

                // set Fragmentclass Arguments
                FragmentOne fragobj = new FragmentOne();
                fragobj.setArguments(bundle);
                return fragobj;
            case 1:
                return new FragmentTwo();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            //
            //Your tab titles
            //
            case 0:return "Practice";
            case 1:return "Report";

            default:return null;
        }
    }
}
