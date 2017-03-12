package com.example.acernb.movieprediction.model;

import java.util.ArrayList;
/**
 * Created by AcerNB on 8.3.2017.
 */

public class Movies {
    private String filmadi,filmresim,basrol,aciklama;
    private int yil,sure;
    private double reyting;
    private ArrayList<String> tur;

    public Movies(){
    }
    public Movies(String filmadi,String filmresim,String basrol,String aciklama,int yil,int sure,double reyting,ArrayList<String> tur ){
        this.filmadi=filmadi;
        this.filmresim=filmresim;
        this.basrol=basrol;
        this.aciklama=aciklama;
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

    public String getFilmresim(){
        return filmresim;
    }
    public void setFilmresim(String filmresim){
        this.filmresim=filmresim;
    }

    public String getBasrol(){
        return basrol;
    }
    public void setBasrol(String basrol){
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
}
