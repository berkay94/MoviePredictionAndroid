package com.example.acernb.movieprediction;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.acernb.movieprediction.adapter.ListAdapter;
import com.example.acernb.movieprediction.app.AppController;
import com.example.acernb.movieprediction.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AnaSayfaActivity extends AppCompatActivity {
    private static final String TAG = AnaSayfaActivity.class.getSimpleName();

    private static final String url = "";//verilerin json formatında bulundukları url
    private ProgressDialog progressDialog;
    private List<Movies> moviesList=new ArrayList<Movies>();
    private ListView listView;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        listView=(ListView)findViewById(R.id.list);
        listAdapter=new ListAdapter(this,moviesList);
        listView.setAdapter(listAdapter);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Filmler Yükleniyor");
        progressDialog.show();

        JsonArrayRequest moviesReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG,response.toString());
                hidePDialog();

                for (int i=0;i<response.length();i++){
                    try{
                        JSONObject jsonObject=response.getJSONObject(i);
                        Movies movie=new Movies();
                        movie.setFilmadi(jsonObject.getString(""));//parantez içine json nesnelerinin başlıkları gelecek
                        movie.setFilmresim(jsonObject.getString(""));
                        movie.setBasrol(jsonObject.getString(""));
                        movie.setAciklama(jsonObject.getString(""));
                        movie.setReyting(((Number)jsonObject.get("")).doubleValue());
                        movie.setYil(jsonObject.getInt(""));
                        movie.setSure(jsonObject.getInt(""));

                        JSONArray turArry=jsonObject.getJSONArray("");//
                        ArrayList<String> tur= new ArrayList<String>();
                        for (int j=0;j<turArry.length();j++){
                            tur.add((String)turArry.get(j));
                        }
                         movie.setTur(tur);

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
}


