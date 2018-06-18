package com.example.acernb.movieprediction.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.acernb.movieprediction.R;
import com.example.acernb.movieprediction.app.AppController;
import com.example.acernb.movieprediction.model.Movies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AcerNB on 10.3.2017.
 */

public class ListAdapter extends BaseAdapter implements Filterable {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Movies> moviesItems;
    private ArrayList<Movies> mStringFilterList;
    private ValueFilter valueFilter;
    ImageLoader imageLoader= AppController.getInstance().getImageLoader();



    public ListAdapter(Activity activity,ArrayList<Movies> moviesItems){
        super();
        this.activity=activity;
        this.moviesItems=moviesItems;
        mStringFilterList=moviesItems;
        getFilter();

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


        String basrolStr = "";
        for (String bstr:movies.getBasrol()){
           basrolStr += bstr + ", ";
   }
        basrolStr = basrolStr.length()>0 ? basrolStr.substring(0,basrolStr.length()-2):basrolStr;
        filmBasrol.setText(basrolStr);





        filmIcon.setImageUrl(movies.getFilmresim(),imageLoader);
        filmAd.setText(movies.getFilmadi());
        filmAciklama.setText(movies.getAciklama());
        filmTarih.setText("("+String.valueOf(movies.getYil())+")");
        filmSure.setText(String.valueOf(movies.getSure())+ " "+ "Minutes"+" "+"|"+" "+movies.getTur());
        filmBasari.setText(String.format("%.1f",movies.getPredicate()));


        return convertView;

    }


    @Override
    public Filter getFilter() {
        if (valueFilter==null){
            valueFilter=new ValueFilter();
        }
        return valueFilter;
    }
    private class ValueFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results=new FilterResults();
            if (constraint!=null && constraint.length()>0){
                ArrayList<Movies> filterList=new ArrayList<Movies>();
                for (int i=0;i<mStringFilterList.size();i++){
                    if ((mStringFilterList.get(i).getFilmadi().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        Movies movies=new Movies();
                        movies.setFilmadi(mStringFilterList.get(i).getFilmadi());
                        movies.setRoller(mStringFilterList.get(i).getRoller());
                        movies.setAciklama(mStringFilterList.get(i).getAciklama());
                        movies.setOyuncuresim(mStringFilterList.get(i).getOyuncuresim());
                        movies.setOyuncuresim2(mStringFilterList.get(i).getOyuncuresim2());
                        movies.setOyuncuresim3(mStringFilterList.get(i).getOyuncuresim3());
                        movies.setOyuncuresim4(mStringFilterList.get(i).getOyuncuresim4());
                        movies.setYil(mStringFilterList.get(i).getYil());
                        movies.setBasrol(mStringFilterList.get(i).getBasrol());
                        movies.setFilmresim(mStringFilterList.get(i).getFilmresim());
                        movies.setSure(mStringFilterList.get(i).getSure());
                        movies.setTur(mStringFilterList.get(i).getTur());
                        movies.setYonetmen(mStringFilterList.get(i).getYonetmen());
                        movies.setPredicate(mStringFilterList.get(i).getPredicate());

                        filterList.add(movies);
                    }
                }
                results.count=filterList.size();
                results.values=filterList;
            }else{
                results.count=mStringFilterList.size();
                results.values=mStringFilterList;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint,FilterResults results){
            moviesItems=(ArrayList<Movies>) results.values;
            notifyDataSetChanged();
        }

    }
}
