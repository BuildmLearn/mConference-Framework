package org.buildmlearn.mconference.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.ConferenceDetailsAdapter;
import org.buildmlearn.mconference.conference.About;
import org.buildmlearn.mconference.conference.Venue;
import org.buildmlearn.mconference.conference.Register;
import org.buildmlearn.mconference.conference.Sponsor;

public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

        private void setupViewPager(ViewPager viewPager) {
            ConferenceDetailsAdapter adapter = new ConferenceDetailsAdapter(getSupportFragmentManager());
            adapter.addFragment(new About(), "About");
            adapter.addFragment(new Venue(), "Venue");
            adapter.addFragment(new Register(), "Register");
            adapter.addFragment(new Sponsor(), "Sponsors");
            viewPager.setOffscreenPageLimit(0);
            viewPager.setAdapter(adapter);
        }
    }
