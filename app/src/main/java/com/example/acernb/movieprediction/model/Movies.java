package com.example.acernb.movieprediction.model;



import java.util.ArrayList;
/**
 * Created by AcerNB on 8.3.2017.
 */

public class Movies  {
    private String filmadi,filmresim,aciklama,yonetmen;
    private int yil,sure;
    private double reyting;
    private ArrayList<String> tur;
    private ArrayList<String> basrol;
    private ArrayList<String> oyuncular;
    private ArrayList<String> roller;

    public Movies(){
    }
    public Movies(String filmadi,String filmresim,String aciklama,String yonetmen,int yil,int sure,double reyting,ArrayList<String> tur,ArrayList<String> basrol,ArrayList<String> oyuncular,ArrayList<String> roller  ){
        this.filmadi=filmadi;
        this.filmresim=filmresim;
        this.basrol=basrol;
        this.oyuncular=oyuncular;
        this.roller=roller;
        this.aciklama=aciklama;
        this.yonetmen=yonetmen;
        this.yil=yil;
        this.sure=sure;
        this.reyting=reyting;
        this.tur=tur;
    }


    public String getFilmadi(){
        return filmadi;
    }
    public void setFilmadi(String filmadi){
        this.filmadi=filmadi;
    }
    public String getYonetmen(){
        return yonetmen;
    }
    public void setYonetmen(String yonetmen){
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

    public int getYil(){
        return yil;
    }
    public void setYil(int yil){
        this.yil=yil;
    }

    public int getSure(){
        return sure;
    }
    public void setSure(int sure){
        this.sure=sure;
    }

    public double getReyting(){
        return reyting;
    }
    public void setReyting(double reyting){
        this.reyting=reyting;
    }

    public ArrayList<String> getTur(){
        return tur;
    }
    public void setTur(ArrayList<String> tur){
        this.tur=tur;
    }
    public ArrayList<String> getOyuncular(){
        return oyuncular;
    }
    public void setOyuncular(ArrayList<String> oyuncular){
        this.oyuncular=oyuncular;
    }
    public ArrayList<String> getRoller(){
        return roller;
    }
    public void setRoller(ArrayList<String> roller){
        this.roller=roller;
    }


}
