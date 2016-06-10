package org.buildmlearn.mconference.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.TabAdapter;
import org.buildmlearn.mconference.conference.About;
import org.buildmlearn.mconference.conference.Venue;
import org.buildmlearn.mconference.conference.Register;
import org.buildmlearn.mconference.conference.Sponsor;

public class Home extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            super.setUpNavDrawer(toolbar);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
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
