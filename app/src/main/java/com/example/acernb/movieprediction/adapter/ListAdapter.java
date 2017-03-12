package com.example.acernb.movieprediction.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.acernb.movieprediction.R;
import com.example.acernb.movieprediction.app.AppController;
import com.example.acernb.movieprediction.model.Movies;

import java.util.List;

/**
 * Created by AcerNB on 10.3.2017.
 */

public class ListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movies> moviesItems;
    ImageLoader imageLoader= AppController.getInstance().getImageLoader();

    public ListAdapter(Activity activity,List<Movies> moviesItems){
        this.activity=activity;
        this.moviesItems=moviesItems;
    }
    @Override
    public int getCount(){
        return moviesItems.size();
    }
    @Override
    public Object getItem(int location){
        return moviesItems.get(location);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView==null)
            convertView=inflater.inflate(R.layout.list_row,null);

        if(imageLoader==null)
            imageLoader=AppController.getInstance().getImageLoader();
        NetworkImageView filmIcon=(NetworkImageView)convertView.findViewById(R.id.filmicon);
        TextView filmAd=(TextView)convertView.findViewById(R.id.filmad);
        TextView filmTarih=(TextView)convertView.findViewById(R.id.filmtarih);
        TextView filmSure=(TextView)convertView.findViewById(R.id.filmsure);
        TextView filmTur=(TextView)convertView.findViewById(R.id.filmtur);
        TextView filmBasari=(TextView)convertView.findViewById(R.id.basari);
        TextView filmBasrol=(TextView)convertView.findViewById(R.id.basrol);
        TextView filmAciklama=(TextView)convertView.findViewById(R.id.aciklama);

        Movies movies=moviesItems.get(position);

        filmIcon.setImageUrl(movies.getFilmresim(),imageLoader);

        filmAd.setText(movies.getFilmadi());
        filmBasrol.setText(movies.getBasrol());
        filmAciklama.setText(movies.getAciklama());
        filmTarih.setText(String.valueOf(movies.getYil()));
        filmSure.setText(String.valueOf(movies.getSure()));
        filmBasari.setText(String.valueOf(movies.getReyting()));

        String genreStr = "";
        for (String str:movies.getTur()){
            genreStr += str + ", ";
        }
        genreStr = genreStr.length()>0 ? genreStr.substring(0,genreStr.length()-2):genreStr;
        filmTur.setText(genreStr);

        return convertView;

    }

}
