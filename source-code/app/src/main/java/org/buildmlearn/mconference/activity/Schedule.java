package org.buildmlearn.mconference.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.TabAdapter;
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.schedule.DayFragment;

public class Schedule extends BaseActivity implements Constants {

    private int noOfDays;
    long startTime;
    long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_schedule, frameLayout);

        SharedPreferences sharedPref
                = getApplicationContext().getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        startTime = sharedPref.getLong(START_TAG, 0);
        Log.d("Schedule", startTime + "");
        endTime = sharedPref.getLong(END_TAG, 0);
        Log.d("Schedule", endTime + "");

        noOfDays = getNoOfDays(endTime - startTime) + 1;

        Toolbar toolbar = (Toolbar) findViewById(R.id.schedule_toolbar);
        setSupportActionBar(toolbar);

        super.setUpNavDrawer(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.schedule_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.schedule_tabs);
        tabLayout.setupWithViewPager(viewPager);

        navigationView.getMenu().getItem(1).setChecked(true);
        overridePendingTransition(0,0);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_schedule;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        for(int i=1; i<=noOfDays; i++)
            adapter.addFragment(newDayFragmentInstance(i), "Day " + i);
        viewPager.setAdapter(adapter);
    }

    private int getNoOfDays(long milli) {
        return (int)(milli / milliInOneDay);
    }

    private DayFragment newDayFragmentInstance(int day){
        DayFragment dayFragment = new DayFragment();

        Bundle args = new Bundle();
        args.putLong(DAY_KEY, startTime + (day - 1) * milliInOneDay);
        dayFragment.setArguments(args);
        return dayFragment;
    }
}
