package org.buildmlearn.mconference.activity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;

public class BaseActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public FrameLayout frameLayout;
    public Toolbar toolbar;
    private ImageView navImage;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        frameLayout = (FrameLayout) findViewById(R.id.nav_content_frame);
        navImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_image);
    }

    protected void setUpNavDrawer(Toolbar toolbar) {

        Picasso.with(this)
                .load(R.drawable.placeholder_1)
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(navImage);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        {
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_ham);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {

                if (item.getItemId() == getSelfNavDrawerItem()) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToNavDrawerItem(item.getItemId());
                    }
                }, 250);

                View mainContent = findViewById(R.id.nav_content_frame);
                mainContent.animate().alpha(0).setDuration(150);

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

        View mainContent = findViewById(R.id.nav_content_frame);
        mainContent.animate().alpha(1).setDuration(250);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void goToNavDrawerItem(int item) {
        Intent i = null;

        switch (item) {
            case R.id.nav_home:
                i = new Intent(getBaseContext(), Home.class);
                break;

            case R.id.nav_schedule:
                i = new Intent(getBaseContext(), Schedule.class);
                break;
        }

        if(i != null)
            createBackStack(i);
    }

    private void createBackStack(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(this);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        }
        else {
            startActivity(intent);
            finish();
        }
    }

    protected int getSelfNavDrawerItem(){
        return -1;
    }
}
