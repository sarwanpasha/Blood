package com.example.root.cm.example.root.FragmentMainActivity_For_All;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.root.giveblood.R;
import com.example.root.giveblood.RecyclerViewFragment;
import com.example.root.giveblood.RecyclerViewFragment_for_AB_negative;
import com.example.root.giveblood.RecyclerViewFragment_for_AB_positive;
import com.example.root.giveblood.RecyclerViewFragment_for_A_negative;
import com.example.root.giveblood.RecyclerViewFragment_for_B_Positive;
import com.example.root.giveblood.RecyclerViewFragment_for_B_negative;
import com.example.root.giveblood.RecyclerViewFragment_for_O_negative;
import com.example.root.giveblood.RecyclerViewFragment_for_O_positive;
import com.example.root.giveblood.Select_Donor_Or_Receptor_3;

import java.util.Locale;

public class FragmentMainActivity_For_A_Negative extends ActionBarActivity implements ActionBar.TabListener{

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main_activity__for__a__negative);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
          //  position=1;
            switch (position) {
                case 0:
                    return RecyclerViewFragment.newInstance();
                case 1:
                    return RecyclerViewFragment_for_A_negative.newInstance();
                case 2:
                    return RecyclerViewFragment_for_AB_positive.newInstance();
                case 3:
                    return RecyclerViewFragment_for_AB_negative.newInstance();
                case 4:
                    return RecyclerViewFragment_for_B_Positive.newInstance();
                case 5:
                    return RecyclerViewFragment_for_B_negative.newInstance();
                case 6:
                    return RecyclerViewFragment_for_O_positive.newInstance();
                case 7:
                    return RecyclerViewFragment_for_O_negative.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "A+ve";
                case 1:
                    return "A-ve";
                case 2:
                    return "AB+ve";
                case 3:
                    return "AB-ve";
                case 4:
                    return "B+ve";
                case 5:
                    return "B-ve";
                case 6:
                    return "O+ve";
                case 7:
                    return "O-ve";
            }
            return null;
        }
    }
    public void onBackPressed() {
        Intent i = new Intent(FragmentMainActivity_For_A_Negative.this,Select_Donor_Or_Receptor_3.class);
        startActivity(i);
        finish();
    }
}

