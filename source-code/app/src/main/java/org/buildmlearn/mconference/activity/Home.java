package org.buildmlearn.mconference.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.TabAdapter;
import org.buildmlearn.mconference.conference.About;
import org.buildmlearn.mconference.conference.Venue;
import org.buildmlearn.mconference.conference.Register;
import org.buildmlearn.mconference.conference.Sponsor;

public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            super.setUpNavDrawer(toolbar);

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setOffscreenPageLimit(3);
            setupViewPager(viewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            navigationView.getMenu().getItem(0).setChecked(true);
            overridePendingTransition(0,0);
        }

    @Override
    protected int getSelfNavDrawerItem() {
            return R.id.nav_home;
    }

    private void setupViewPager(ViewPager viewPager) {
            TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
            adapter.addFragment(new About(), "About");
            adapter.addFragment(new Venue(), "Venue");
            adapter.addFragment(new Register(), "Register");
            adapter.addFragment(new Sponsor(), "Sponsors");
            viewPager.setAdapter(adapter);
        }
}
