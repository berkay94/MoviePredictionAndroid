package com.example.acernb.movieprediction;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class AnaSayfaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = AnaSayfaActivity.class.getSimpleName();
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog progressDialog;
    private List<Movies> moviesList=new ArrayList<Movies>();
    private ListView listView;
    private ListAdapter listAdapter;
    private ImageButton ımageButton;
    private SearchView searchView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        ımageButton=(ImageButton)findViewById(R.id.filmara);

        listView=(ListView)findViewById(R.id.list);
        listAdapter=new ListAdapter(this,moviesList);
        listView.setAdapter(listAdapter);

        searchView=(SearchView)findViewById(R.id.search);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Filmler Yükleniyor");
        progressDialog.show();

        listView.setTextFilterEnabled(true);
        setupSearchView();

        ımageButton.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {

          searchView.setVisibility(View.VISIBLE);


    }
});



        JsonArrayRequest moviesReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {//volley nesnesi oluşturuma
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




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(AnaSayfaActivity.this,DetaySayfa.class);
                    String ıtem=moviesList.get(position).getFilmadi();
                    String ıtem2= String.valueOf(moviesList.get(position).getYil());
                    String ıtem3=moviesList.get(position).getFilmresim();
                    String ıtem4=String.valueOf(moviesList.get(position).getReyting());
                    String ıtem5= String.valueOf(moviesList.get(position).getSure());
                    String ıtem6=moviesList.get(position).getYonetmen();
                    String ıtem7=moviesList.get(position).getAciklama();


                    String genreStr = "";
                for (String str:moviesList.get(position).getTur()){
                    genreStr += str + ", ";
                }
                genreStr = genreStr.length()>0 ? genreStr.substring(0,genreStr.length()-2):genreStr;

                String oyuncularStr = "";
                for (String ostr:moviesList.get(position).getOyuncular()){
                    oyuncularStr += ostr+ "\n\n ";
                }
                oyuncularStr =  oyuncularStr.length()>0 ?  oyuncularStr.substring(0, oyuncularStr.length()-2): oyuncularStr;

                String rollerStr = "";
                for (String rstr:moviesList.get(position).getRoller()){
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

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText.toString());
        }
        return true;
    }
}


