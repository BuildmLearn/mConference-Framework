package org.buildmlearn.mconference.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.model.TalkDetails;

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
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.talk_toolbar_layout);
        collapsingToolbarLayout.setTitle(talkDetails.getName());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));

        final ImageView talkImage = (ImageView) findViewById(R.id.talk_collapsible_image);
        TextView talkTime = (TextView) findViewById(R.id.talk_time_detail);
        TextView talkLocation = (TextView) findViewById(R.id.talk_venue_detail);
        TextView talkDesc = (TextView) findViewById(R.id.talk_desc_detail);

        talkTime.setText(talkDetails.getTime());
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
