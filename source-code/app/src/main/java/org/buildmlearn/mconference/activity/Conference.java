package org.buildmlearn.mconference.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.util.XMLParser;

import java.io.IOException;
import java.net.URL;

public class Conference extends BaseActivity implements Constants {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getLayoutInflater().inflate(R.layout.activity_conference, frameLayout);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            super.setUpNavDrawer(toolbar);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setOffscreenPageLimit(3);

            tabLayout = (TabLayout) findViewById(R.id.tabs);

            navigationView.getMenu().getItem(0).setChecked(true);
            overridePendingTransition(0,0);

            URL url = null;
            if (SECOND_APPROACH) {
                Intent i = getIntent();
                try {
                        url = new URL(i.getStringExtra("URL"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            populateConference task = new populateConference(this);
            task.execute(url);
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


    private class populateConference extends AsyncTask<URL, Void, Void> {

        private Context mContext;
        public populateConference (Context context){
            mContext = context;
        }

        protected Void doInBackground(URL... urls) {
            if (SECOND_APPROACH) {
                try {
                    XMLParser.parse(mContext, urls[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onBackPressed() {
        if (!SECOND_APPROACH)
            System.exit(1);

        super.onBackPressed();
    }
}
