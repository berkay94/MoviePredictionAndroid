package com.example.acernb.movieprediction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.acernb.movieprediction.adapter.ListAdapter;
import com.example.acernb.movieprediction.app.AppController;
import com.example.acernb.movieprediction.model.Movies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class AnaSayfaActivity extends AppCompatActivity{

    private static final String TAG = AnaSayfaActivity.class.getSimpleName();
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog progressDialog;
    private ArrayList<Movies> moviesList=new ArrayList<Movies>();
    private ListView listView;
    private ListAdapter listAdapter;
    private ImageButton ımageButton;
    private EditText editText;
    RelativeLayout rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);


        editText=(EditText)findViewById(R.id.editText);
        rl=(RelativeLayout)findViewById(R.id.activity_ana_sayfa);
        rl.getBackground().setAlpha(50);

        ımageButton=(ImageButton)findViewById(R.id.filmara);

        listView=(ListView)findViewById(R.id.list);
        listAdapter=new ListAdapter(AnaSayfaActivity.this,moviesList);
        listView.setAdapter(listAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             listAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Filmler Yükleniyor");
        progressDialog.show();


        ımageButton.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
          editText.setVisibility(View.VISIBLE);
    }
});

        jsonRequest();
        sendData();



    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        hidePDialog();
    }
    private void hidePDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }
    private void sendData(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies movies=(Movies)parent.getItemAtPosition(position);
                Intent i = new Intent(AnaSayfaActivity.this,DetaySayfa.class);
                String ıtem=movies.getFilmadi();
                String ıtem2= String.valueOf(movies.getYil());
                String ıtem3=movies.getFilmresim();
                String ıtem4=String.valueOf(movies.getReyting());
                String ıtem5= String.valueOf(movies.getSure());
                String ıtem6=movies.getYonetmen();
                String ıtem7=movies.getAciklama();


                String genreStr = "";
                for (String str:movies.getTur()){
                    genreStr += str + ", ";
                }
                genreStr = genreStr.length()>0 ? genreStr.substring(0,genreStr.length()-2):genreStr;

                String oyuncularStr = "";
                for (String ostr:movies.getOyuncular()){
                    oyuncularStr += ostr+ "\n\n ";
                }
                oyuncularStr =  oyuncularStr.length()>0 ?  oyuncularStr.substring(0, oyuncularStr.length()-2): oyuncularStr;

                String rollerStr = "";
                for (String rstr:movies.getRoller()){
                    rollerStr += rstr + "\n\n ";
                }
                rollerStr = rollerStr.length()>0 ? rollerStr.substring(0,rollerStr.length()-2):rollerStr;


                i.putExtra("title",ıtem);
                i.putExtra("year",ıtem2);
                i.putExtra("image",ıtem3);
                i.putExtra("rating",ıtem4);
                i.putExtra("time",ıtem5);
                i.putExtra("tur",genreStr);
                i.putExtra("yonetmen",ıtem6);
                i.putExtra("oyuncular",oyuncularStr);
                i.putExtra("roller",rollerStr);
                i.putExtra("oyuncular2",genreStr);
                i.putExtra("aciklama",ıtem7);
                startActivity(i);

            }
        });
    }
    private void jsonRequest(){
        JsonArrayRequest moviesReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG,response.toString());
                hidePDialog();

                for (int i=0;i<response.length();i++){
                    try{
                        JSONObject jsonObject=response.getJSONObject(i);
                        Movies movie=new Movies();
                        movie.setFilmadi(jsonObject.getString("title"));
                        movie.setFilmresim(jsonObject.getString("image"));
                        movie.setYonetmen(jsonObject.getString("title"));
                        movie.setAciklama(jsonObject.getString("title"));
                        movie.setReyting(((Number)jsonObject.get("rating")).doubleValue());
                        movie.setYil(jsonObject.getInt("releaseYear"));
                        // movie.setSure(jsonObject.getInt(""));

                        JSONArray turArry=jsonObject.getJSONArray("genre");
                        ArrayList<String> tur= new ArrayList<String>();
                        for (int j=0;j<turArry.length();j++){
                            tur.add((String)turArry.get(j));
                        }
                        movie.setTur(tur);

                        JSONArray basrolArry=jsonObject.getJSONArray("genre");
                        ArrayList<String> basrol= new ArrayList<String>();
                        for (int j=0;j<basrolArry.length();j++){
                            basrol.add((String)basrolArry.get(j));
                        }
                        movie.setBasrol(basrol);

                        JSONArray oyuncularArry=jsonObject.getJSONArray("genre");
                        ArrayList<String> oyuncular= new ArrayList<String>();
                        for (int j=0;j<oyuncularArry.length();j++){
                            oyuncular.add((String)oyuncularArry.get(j));
                        }
                        movie.setOyuncular(oyuncular);

                        JSONArray rollerArry=jsonObject.getJSONArray("genre");
                        ArrayList<String> roller= new ArrayList<String>();
                        for (int j=0;j<rollerArry.length();j++){
                            roller.add((String)rollerArry.get(j));
                        }
                        movie.setRoller(roller);

                        moviesList.add(movie);
                    }

                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                listAdapter.notifyDataSetChanged();
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG,"Hata: " + error.getMessage());
                hidePDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(moviesReq);

    }

}


