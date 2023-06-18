package com.example.akhir;

public class Courses {
    private String Judul,key;

    public void setJudul(String judul) {
        Judul = judul;
    }
    public Courses(String judul){
        this.Judul = judul;
    }
    public Courses(){}

    public String getJudul() {
        return Judul;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
