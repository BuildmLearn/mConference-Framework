package org.buildmlearn.mconference.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.CollapsibleActionView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.model.TalkDetails;

public class Talk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        Toolbar toolbar = (Toolbar) findViewById(R.id.talk_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TalkDetails talkDetails = (TalkDetails)getIntent().getSerializableExtra("TalkDetails");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(talkDetails.getName());

        ImageView talkImage = (ImageView) findViewById(R.id.talk_collapsible_image);
        TextView talkTime = (TextView) findViewById(R.id.talk_time_detail);
        TextView talkLocation = (TextView) findViewById(R.id.talk_venue_detail);
        TextView talkDesc = (TextView) findViewById(R.id.talk_desc_detail);

        Picasso.with(this)
                .load(Uri.parse(talkDetails.getImageURL()))
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(talkImage);
        talkTime.setText(talkDetails.getTime());
        talkLocation.setText(talkDetails.getLocation());
        talkDesc.setText(talkDetails.getDesc());



        Bitmap bitmap = ((BitmapDrawable)  talkImage.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.talk_toolbar_layout);
               //Implement Changes
            }
        });
    }
}
