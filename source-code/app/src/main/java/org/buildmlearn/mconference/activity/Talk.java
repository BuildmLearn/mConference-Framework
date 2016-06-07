package org.buildmlearn.mconference.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.model.TalkDetails;

import java.text.SimpleDateFormat;

public class Talk extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        Toolbar toolbar = (Toolbar) findViewById(R.id.talk_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TalkDetails talkDetails = (TalkDetails)getIntent().getSerializableExtra("TalkDetails");
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.talk_toolbar_layout);
        collapsingToolbarLayout.setTitle(talkDetails.getName());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));

        final ImageView talkImage = (ImageView) findViewById(R.id.talk_collapsible_image);
        TextView talkTime = (TextView) findViewById(R.id.talk_time_detail);
        TextView talkLocation = (TextView) findViewById(R.id.talk_venue_detail);
        TextView talkDesc = (TextView) findViewById(R.id.talk_desc_detail);

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String talkTimeText = dateFormat.format(talkDetails.getStartTime())
                + " - " + dateFormat.format(talkDetails.getEndTime());
        talkTime.setText(talkTimeText);

        talkLocation.setText(talkDetails.getLocation());
        talkDesc.setText(talkDetails.getDesc());

        Picasso.with(this)
                .load(Uri.parse(talkDetails.getImageURL()))
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(talkImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                applyPalette(talkImage);
            }

            @Override
            public void onError() {
                Log.d("Talk.java", "Error caused by Picasso");
                applyPalette(talkImage);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.talk_reminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE,
                        talkDetails.getName());
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        talkDetails.getStartTime().getTime());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                        talkDetails.getLocation());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        talkDetails.getEndTime().getTime());
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                intent.putExtra(CalendarContract.Events.DESCRIPTION,
                        talkDetails.getShortDesc());
                v.getContext().startActivity(intent);
            }
        });
    }

    private void applyPalette(ImageView img) {

        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int primary = ContextCompat.getColor(getBaseContext(), R.color.colorPrimary);
                int primaryDark = ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark);

                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(primaryDark));
            }
        });
    }
}
