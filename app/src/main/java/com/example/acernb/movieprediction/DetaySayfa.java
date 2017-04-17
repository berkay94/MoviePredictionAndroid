package com.example.acernb.movieprediction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.acernb.movieprediction.app.AppController;
import com.example.acernb.movieprediction.model.Movies;

import java.util.ArrayList;


/**
 * Created by AcerNB on 23.3.2017.
 */

public class DetaySayfa extends AppCompatActivity {
    ImageLoader imageLoader= AppController.getInstance().getImageLoader();

    VideoView videoView;
    ImageButton baslat;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detay);

        baslat=(ImageButton)findViewById(R.id.play);
        videoView=(VideoView)findViewById(R.id.videoView2);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video=Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);



        baslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
               baslat.setVisibility(View.INVISIBLE);

            }
        });


        TextView text=(TextView)findViewById(R.id.filmadi);
        TextView text2=(TextView)findViewById(R.id.filmyili);
        NetworkImageView text3=(NetworkImageView)findViewById(R.id.filmiconu) ;
        TextView text4=(TextView)findViewById(R.id.basarisi);
        TextView text5=(TextView)findViewById(R.id.filmsuresi);
        TextView text6=(TextView)findViewById(R.id.filmturu);
        TextView text7=(TextView)findViewById(R.id.dyonetmen);
        TextView text8=(TextView)findViewById(R.id.textView7);
        TextView text9=(TextView)findViewById(R.id.droller);
        TextView text10=(TextView)findViewById(R.id.textView9);
        TextView text11=(TextView)findViewById(R.id.filmaciklama);



        text.setText(getIntent().getExtras().getString("title"));
        text2.setText("("+getIntent().getExtras().getString("year")+")");
        text3.setImageUrl(getIntent().getExtras().getString("image"),imageLoader);
        text4.setText(getIntent().getExtras().getString("rating"));
        text6.setText(getIntent().getExtras().getString("tur"));
        text5.setText(getIntent().getExtras().getString("time")+" "+"|"+" "+text6.getText());
        text7.setText(getIntent().getExtras().getString("yonetmen"));
        text8.setText(getIntent().getExtras().getString("oyuncular"));
        text9.setText(getIntent().getExtras().getString("roller"));
        text10.setText(getIntent().getExtras().getString("oyuncular2"));
        text11.setText(getIntent().getExtras().getString("aciklama"));



    }
}