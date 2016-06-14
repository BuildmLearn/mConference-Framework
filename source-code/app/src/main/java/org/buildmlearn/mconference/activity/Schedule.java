package org.buildmlearn.mconference.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.TabAdapter;
import org.buildmlearn.mconference.schedule.DayFragment;

public class Schedule extends BaseActivity {

    private int noOfDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_schedule, frameLayout);

        noOfDays = 3;

        Toolbar toolbar = (Toolbar) findViewById(R.id.schedule_toolbar);
        setSupportActionBar(toolbar);

        super.setUpNavDrawer(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.schedule_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.schedule_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        for(int i=1; i<=noOfDays; i++)
            adapter.addFragment(new DayFragment(), "Day " + i);
        viewPager.setAdapter(adapter);
    }
}
