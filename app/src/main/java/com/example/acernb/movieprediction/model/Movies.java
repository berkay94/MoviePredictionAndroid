package com.example.acernb.movieprediction.model;



import java.util.ArrayList;
/**
 * Created by AcerNB on 8.3.2017.
 */

public class Movies  {
   private String filmadi,filmresim,aciklama,yil,tur,videourl;
    private int sure;
    private double predicate;


    private ArrayList<String> basrol;
    private ArrayList<String> oyuncuresim;
    private ArrayList<String> oyuncuresim2;
    private ArrayList<String> oyuncuresim3;
    private ArrayList<String> oyuncuresim4;
    private ArrayList<String> roller;
    private ArrayList<String> yonetmen;



    public Movies(){
    }
    public Movies(String filmadi,String filmresim,String aciklama,String yil,String tur,String videourl,int sure,ArrayList<String> basrol,ArrayList<String> oyuncuresim,ArrayList<String> roller,ArrayList<String> yonetmen,ArrayList<String> oyuncuresim2,ArrayList<String> oyuncuresim3,ArrayList<String> oyuncuresim4,double predicate){
        this.filmadi=filmadi;
        this.filmresim=filmresim;
        this.basrol=basrol;
        this.oyuncuresim=oyuncuresim;
        this.oyuncuresim2=oyuncuresim2;
        this.oyuncuresim3=oyuncuresim3;
        this.oyuncuresim4=oyuncuresim4;
        this.roller=roller;
        this.aciklama=aciklama;
        this.yonetmen=yonetmen;
        this.yil=yil;
        this.sure=sure;
        this.tur=tur;
        this.videourl=videourl;
        this.predicate=predicate;


    }






    public String getVideourl(){
        return videourl;
    }
    public void setVideourl(String videourl){
        this.videourl=videourl;
    }
    public String getFilmadi(){
        return filmadi;
    }
    public void setFilmadi(String filmadi){
        this.filmadi=filmadi;
    }
    public ArrayList <String> getYonetmen(){
        return yonetmen;
    }
    public void setYonetmen(ArrayList<String> yonetmen){
        this.yonetmen=yonetmen;
    }

    public String getFilmresim(){
        return filmresim;
    }
    public void setFilmresim(String filmresim){
        this.filmresim=filmresim;
    }

    public ArrayList<String>getBasrol(){
        return basrol;
    }
    public void setBasrol(ArrayList<String> basrol){
        this.basrol=basrol;
    }

    public String getAciklama(){
        return aciklama;
    }
    public void setAciklama(String aciklama){
        this.aciklama=aciklama;
    }

    public String getYil(){
        return yil;
    }
    public void setYil(String yil){
        this.yil=yil;
    }

    public int getSure(){
        return sure;
    }
    public void setSure(int sure){
        this.sure=sure;
    }
    
    public String getTur(){
        return tur;
    }
    public void setTur(String tur){
        this.tur=tur;
    }
    public ArrayList<String> getOyuncuresim(){
        return oyuncuresim;
    }
    public void setOyuncuresim(ArrayList<String> oyuncuresim){
        this.oyuncuresim=oyuncuresim;
    }

    public ArrayList<String> getOyuncuresim2(){
        return oyuncuresim2;
    }
    public void setOyuncuresim2(ArrayList<String> oyuncuresim2){
        this.oyuncuresim2=oyuncuresim2;
    }

    public ArrayList<String> getOyuncuresim3(){
        return oyuncuresim3;
    }
    public void setOyuncuresim3(ArrayList<String> oyuncuresim3){
        this.oyuncuresim3=oyuncuresim3;
    }

    public ArrayList<String> getOyuncuresim4(){
        return oyuncuresim4;
    }
    public void setOyuncuresim4(ArrayList<String> oyuncuresim4){
        this.oyuncuresim4=oyuncuresim4;
    }


    public ArrayList<String> getRoller(){
        return roller;
    }
    public void setRoller(ArrayList<String> roller){
        this.roller=roller;
    }

    public double getPredicate(){
        return predicate;
    }
    public void setPredicate(double predicate){
        this.predicate=predicate;
    }


}
