package com.example.administrator.myapplication.charts.time;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class TimeChartCursor {

    public float avgPrice;
    public float avePriceY;
    public float percent;
    public float percentCursor;
    public float price;
    public float priceY;
    public String time;
    public float volume;
    public float volumeForTouch;
    public float volumeY;
    public float x;

    public TimeChartCursor(float x, float priceY, float avePriceY, float volumeY, String time, float price, float avgPrice, float percent, float percentCursor, float volume, float volumeForTouch) {
        this.avgPrice = avgPrice;
        this.avePriceY = avePriceY;
        this.percent = percent;
        this.percentCursor = percentCursor;
        this.price = price;
        this.priceY = priceY;
        this.time = time;
        this.volume = volume;
        this.volumeForTouch = volumeForTouch;
        this.volumeY = volumeY;
        this.x = x;
    }
}
