package com.seif.silentmosque.model;

import java.util.List;

public class MosqueList {
    List<Mosque>mosques;

    public MosqueList(List<Mosque> mosques) {
        this.mosques = mosques;
    }

    public List<Mosque> getMosques() {
        return mosques;
    }

    public void setMosques(List<Mosque> mosques) {
        this.mosques = mosques;
    }
}
