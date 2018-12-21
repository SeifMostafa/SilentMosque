package com.seif.silentmosque.provider;

public class Mosque {
    private int long_, lat;
    private String name, keeperPhoneNum;
    private int boundaries[][];

    public Mosque(int long_, int lat, String name) {
        this.long_ = long_;
        this.lat = lat;
        this.name = name;
    }

    public Mosque(int long_, int lat, String name, int[][] boundaries) {
        this.long_ = long_;
        this.lat = lat;
        this.name = name;
        this.boundaries = boundaries;
    }
}
