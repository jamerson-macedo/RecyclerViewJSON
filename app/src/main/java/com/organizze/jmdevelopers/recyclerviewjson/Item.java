package com.organizze.jmdevelopers.recyclerviewjson;
// gets e sets

public class Item {
    private String mImagemURL;
    private String mCriador;
    private int mLikes;

    public String getmImagemURL() {
        return mImagemURL;
    }

    public void setmImagemURL(String mImagemURL) {
        this.mImagemURL = mImagemURL;
    }

    public String getmCriador() {
        return mCriador;
    }

    public void setmCriador(String mCriador) {
        this.mCriador = mCriador;
    }

    public int getmLikes() {
        return mLikes;
    }

    public void setmLikes(int mLikes) {
        this.mLikes = mLikes;
    }

    public Item(String mImagemURL, String mCriador, int mLikes) {
        this.mImagemURL = mImagemURL;
        this.mCriador = mCriador;
        this.mLikes = mLikes;
    }

}
