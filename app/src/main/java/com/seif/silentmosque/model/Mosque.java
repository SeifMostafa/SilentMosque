package com.seif.silentmosque.model;

public class Mosque {


    private LocationModel center_loc;
    private String name, keeperPhoneNum;
    private int r;

    public Mosque(double long_, double lat, String name) {
        center_loc = new LocationModel(lat, long_);
        this.name = name;
    }

    public Mosque(double lat_, double long_, String name, int radius) {
        center_loc = new LocationModel(lat_, long_);
        this.name = name;
        this.r   = radius;
    }

    public String getKeeperPhoneNum() {
        return keeperPhoneNum;
    }

    public void setKeeperPhoneNum(String keeperPhoneNum) {
        this.keeperPhoneNum = keeperPhoneNum;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public String getCenter_loc() {
        return center_loc.toString();
    }

    public void setCenter_loc(LocationModel center_loc) {
        this.center_loc = center_loc;
    }
    class LocationModel {
        private double

                Latitude, Longitude;

        LocationModel() {
        }

        public LocationModel(double lat, double lng) {
            this.Latitude = lat;
            this.Longitude = lng;
        }

        @Override
        public String toString() {
            return ""+getLat()+", "+getLng();
        }

        public double getLat() {
            return Latitude;
        }

        public double getLng() {
            return Longitude;
        }
    }
}
