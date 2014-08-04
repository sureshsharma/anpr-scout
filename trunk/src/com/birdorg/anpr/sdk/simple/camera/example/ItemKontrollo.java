package com.birdorg.anpr.sdk.simple.camera.example;

/**
 * Created by john on 8/2/2014.
 */
public class ItemKontrollo {

    private String targa;
    private String data;
    private String user_id;
    private String imgpath;

    private String gjoba;
    private String shuma;

    public ItemKontrollo(){}

    public void setTarga(String t){

        this.targa=t;
    }
    public void setData(String d)
    {
        this.data=d;
    }
    public void setUser(String u){
        this.user_id = u;
    }
    public void setImgpath(String p){

        this.imgpath = p;
    }
    public void setGjoba(String p){

        this.gjoba = p;
    }
    public void setShuma(String p){

        this.shuma = p;
    }
    public String getTarga()
    {
        return this.targa;
    }
    public String getData()
    {
        return this.data;


    }
    public String getUser()
    {
        return this.user_id;


    }
    public String getImgpath()
    {
        return this.imgpath;

    }
    public String getGjoba()
    {
        return this.gjoba;

    }
    public String getShuma()
    {
        return this.shuma;

    }
}
