package com.oldd6.gui;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {
    @Id
    private String date;
    private int temp;
    private String stateSky;
    private int pressure;
    private int humid;
    private int wind;
    private String precip;

    Weather(String d, int t, String stt, int pr, int h, int w, String p){
        date = d; temp = t; stateSky = stt; pressure = pr; humid = h; wind = w; precip = p;
    }

    public String getDate() {
        return date;
    }

    public int getTemp() {
        return temp;
    }

    public String getStateSky() {
        return stateSky;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumid() {
        return humid;
    }

    public int getWind() {
        return wind;
    }

    public String getPrecip() {
        return precip;
    }

    Weather(){}
}
