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
    ImageButton play;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detay);

        play=(ImageButton)findViewById(R.id.imageButton3);
        videoView=(VideoView)findViewById(R.id.videoView4);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                videoView.start();
                play.setVisibility(View.INVISIBLE);

            }
        });

        TextView videourl=(TextView)findViewById(R.id.textView5);
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
        CircularNetworkImageView text12=(CircularNetworkImageView)findViewById(R.id.oyuncuresimleri) ;
        CircularNetworkImageView text13=(CircularNetworkImageView)findViewById(R.id.oyuncuresimleri2) ;
        CircularNetworkImageView text14=(CircularNetworkImageView)findViewById(R.id.oyuncuresimleri3) ;
        CircularNetworkImageView text15=(CircularNetworkImageView)findViewById(R.id.oyuncuresimleri4) ;
        NetworkImageView text16=(NetworkImageView)findViewById(R.id.foto) ;
        NetworkImageView text17=(NetworkImageView)findViewById(R.id.foto2) ;
        NetworkImageView text18=(NetworkImageView)findViewById(R.id.foto3) ;
        NetworkImageView text19=(NetworkImageView)findViewById(R.id.foto4) ;

        text.setText(getIntent().getExtras().getString("title"));
        text2.setText("("+getIntent().getExtras().getString("year")+")");
        text3.setImageUrl(getIntent().getExtras().getString("image"),imageLoader);
        text4.setText(getIntent().getExtras().getString("rating"));
        text6.setText(getIntent().getExtras().getString("tur"));
        text5.setText(getIntent().getExtras().getString("time")+" "+"Minutes"+" "+"|"+" "+text6.getText());
        text7.setText(getIntent().getExtras().getString("yonetmen"));
        text8.setText(getIntent().getExtras().getString("oyuncular"));
        text9.setText(getIntent().getExtras().getString("roller"));
        text10.setText(getIntent().getExtras().getString("basrol"));
        text11.setText(getIntent().getExtras().getString("aciklama"));



        text12.setImageUrl(getIntent().getExtras().getString("oyuncuresim"),imageLoader);
        text13.setImageUrl(getIntent().getExtras().getString("oyuncuresim2"),imageLoader);
        text14.setImageUrl(getIntent().getExtras().getString("oyuncuresim3"),imageLoader);
        text15.setImageUrl(getIntent().getExtras().getString("oyuncuresim4"),imageLoader);
        text16.setImageUrl(getIntent().getExtras().getString("oyuncuresim"),imageLoader);
        text17.setImageUrl(getIntent().getExtras().getString("oyuncuresim2"),imageLoader);
        text18.setImageUrl(getIntent().getExtras().getString("oyuncuresim3"),imageLoader);
        text19.setImageUrl(getIntent().getExtras().getString("oyuncuresim4"),imageLoader);


        videourl.setText(getIntent().getExtras().getString("video"));
        Uri video=Uri.parse((String)videourl.getText());
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);



    }
}
