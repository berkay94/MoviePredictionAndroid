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
    private static final String url = "http://159.203.131.130:3000/";
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
        rl.getBackground().setAlpha(20);

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
                String ıtem5= String.valueOf(movies.getSure());
                String ıtem7=movies.getAciklama();
                String ıtem9=movies.getTur();
                String ıtem11= String.format("%.1f",movies.getPredicate());

                if(position==0){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.wonderwoman;
                    i.putExtra("video",url);


                }
                else if(position==1){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.amityvilletheawakening;
                    i.putExtra("video",url);


                }
                else if(position==3){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.despicableme3;
                    i.putExtra("video",url);
                    movies.setVideourl(url);

                }
                else if(position==4){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.fourtysevenmeters;
                    i.putExtra("video",url);


                }
                else if(position==5){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.thebadbatch;
                    i.putExtra("video",url);

                }
                else if(position==6){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.thebeguiled;
                    i.putExtra("video",url);


                }
                else if(position==7){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.thebigsick;
                    i.putExtra("video",url);


                }
                else if(position==8){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.maudie;
                    i.putExtra("video",url);


                }
                else if(position==11){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.alleyezonme;
                    i.putExtra("video",url);


                }
                else if(position==12){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.cars3;
                    i.putExtra("video",url);


                }
                else if(position==13){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.mycousinrachel;
                    i.putExtra("video",url);


                }
                else if(position==14){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.beatrizatdinner;
                    i.putExtra("video",url);


                }
                else if(position==15){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.thehero;
                    i.putExtra("video",url);


                }
                else if(position==16){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.itcomesatnight;
                    i.putExtra("video",url);

                }
                else if(position==17){
                    String url="android.resource://" + getPackageName() + "/raw/"+R.raw.meganleavey;
                    i.putExtra("video",url);
                }


                String basrolStr = "";
                for (String bstr:movies.getBasrol()){
                    basrolStr += bstr + ", ";
                }
                basrolStr = basrolStr.length()>0 ? basrolStr.substring(0,basrolStr.length()-2):basrolStr;

                String oyuncularStr = "";
                for (String ostr:movies.getBasrol()){
                    oyuncularStr += ostr+ "\n\n\n\n\n ";
                }
                oyuncularStr =  oyuncularStr.length()>0 ?  oyuncularStr.substring(0, oyuncularStr.length()-2): oyuncularStr;

                String rollerStr = "";
                for (String rstr:movies.getRoller()){
                    rollerStr += rstr + "\n\n\n\n\n ";
                }
                rollerStr = rollerStr.length()>0 ? rollerStr.substring(0,rollerStr.length()-2):rollerStr;

                String yntmnStr = "";
                for (String ystr:movies.getYonetmen()){
                    yntmnStr += ystr + ", ";
                }
                yntmnStr = yntmnStr.length()>0 ? yntmnStr.substring(0,yntmnStr.length()-2):yntmnStr;


                String oyncuresmStr = "";
                for (String oyncurstr:movies.getOyuncuresim()){
                    oyncuresmStr += oyncurstr + ", ";
                }
                oyncuresmStr = oyncuresmStr.length()>0 ? oyncuresmStr.substring(0,oyncuresmStr.length()-2):oyncuresmStr;

                String oyncuresmStr2 = "";
                for (String oyncurstr:movies.getOyuncuresim2()){
                    oyncuresmStr2 += oyncurstr + ", ";
                }
                oyncuresmStr2 = oyncuresmStr2.length()>0 ? oyncuresmStr2.substring(0,oyncuresmStr2.length()-2):oyncuresmStr2;

                String oyncuresmStr3 = "";
                for (String oyncurstr:movies.getOyuncuresim3()){
                    oyncuresmStr3 += oyncurstr + ", ";
                }
                oyncuresmStr3 = oyncuresmStr3.length()>0 ? oyncuresmStr3.substring(0,oyncuresmStr3.length()-2):oyncuresmStr3;

                String oyncuresmStr4 = "";
                for (String oyncurstr:movies.getOyuncuresim4()){
                    oyncuresmStr4 += oyncurstr + ", ";
                }
                oyncuresmStr4 = oyncuresmStr4.length()>0 ? oyncuresmStr4.substring(0,oyncuresmStr4.length()-2):oyncuresmStr4;




                i.putExtra("title",ıtem);
                i.putExtra("year",ıtem2);
                i.putExtra("image",ıtem3);
                i.putExtra("rating",ıtem11);
                i.putExtra("time",ıtem5);
                i.putExtra("yonetmen",yntmnStr);
                i.putExtra("oyuncular",oyuncularStr);
                i.putExtra("roller",rollerStr);
                i.putExtra("aciklama",ıtem7);
                i.putExtra("basrol",basrolStr);
                i.putExtra("tur",ıtem9);
                i.putExtra("oyuncuresim",oyncuresmStr);
                i.putExtra("oyuncuresim2",oyncuresmStr2);
                i.putExtra("oyuncuresim3",oyncuresmStr3);
                i.putExtra("oyuncuresim4",oyncuresmStr4);



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
                        JSONObject other = jsonObject.getJSONObject("other");
                        Movies movie=new Movies();
                        movie.setFilmadi(other.getString("title"));
                        movie.setFilmresim(jsonObject.getString("image"));
                        movie.setAciklama(other.getString("description"));
                        movie.setTur(other.getString("genreString"));
                        movie.setYil(other.getString("releaseyear"));
                        movie.setVideourl(other.getString("imdbURL"));
                        movie.setPredicate(jsonObject.getDouble("rating"));

                        String runtime = other.getString("runtime");
                        if(runtime == "N/A")
                            movie.setSure(0);
                        else
                            movie.setSure(other.getInt("runtime"));


                       JSONArray basrolArry=other.getJSONArray("castArray");
                        ArrayList<String> basrol= new ArrayList<String>();
                       for (int j=0;j<basrolArry.length();j++){
                       JSONObject object=basrolArry.getJSONObject(j);
                           String name=object.getString("name");
                           basrol.add(name);
                     }
                        movie.setBasrol(basrol);


                        JSONArray rollerArry=other.getJSONArray("castArray");
                       ArrayList<String> roller= new ArrayList<String>();
                        for (int j=0;j<rollerArry.length();j++){
                            JSONObject object2=rollerArry.getJSONObject(j);
                            String character= object2.getString("character");
                            roller.add(character);
                        }
                        movie.setRoller(roller);

                        JSONArray yonetmenArry=other.getJSONArray("directorArray");
                        ArrayList<String> yonetmen= new ArrayList<String>();
                        for (int j=0;j<yonetmenArry.length();j++){
                            JSONObject object3=yonetmenArry.getJSONObject(j);
                            String name2= object3.getString("name");
                            yonetmen.add(name2);
                        }
                        movie.setYonetmen(yonetmen);


                        JSONArray oyuncuresimArry=other.getJSONArray("castArray");
                        ArrayList<String> oyncuresim= new ArrayList<String>();
                        for (int j=0;j<oyuncuresimArry.length();j++){
                            JSONObject object4=oyuncuresimArry.getJSONObject(j);
                            String image= object4.getString("image");
                            oyncuresim.add(image);

                        }
                        movie.setOyuncuresim(oyncuresim);

                        JSONArray oyuncuresimArry2=other.getJSONArray("castArray");
                        ArrayList<String> oyncuresim2= new ArrayList<String>();
                        for (int j=1;j<oyuncuresimArry2.length();j++){
                            JSONObject object5=oyuncuresimArry2.getJSONObject(j);
                            String image2= object5.getString("image");
                            oyncuresim2.add(image2);


                        }
                        movie.setOyuncuresim2(oyncuresim2);

                        JSONArray oyuncuresimArry3=other.getJSONArray("castArray");
                        ArrayList<String> oyncuresim3= new ArrayList<String>();
                        for (int j=2;j<oyuncuresimArry3.length();j++){
                            JSONObject object6=oyuncuresimArry3.getJSONObject(j);
                            String image3= object6.getString("image");
                            oyncuresim3.add(image3);


                        }
                        movie.setOyuncuresim3(oyncuresim3);



                        JSONArray oyuncuresimArry4=other.getJSONArray("castArray");
                        ArrayList<String> oyncuresim4= new ArrayList<String>();
                        for (int j=3;j<oyuncuresimArry4.length();j++){
                            JSONObject object7=oyuncuresimArry4.getJSONObject(j);
                            String image4= object7.getString("image");
                            oyncuresim4.add(image4);

                        }
                        movie.setOyuncuresim4(oyncuresim4);


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



