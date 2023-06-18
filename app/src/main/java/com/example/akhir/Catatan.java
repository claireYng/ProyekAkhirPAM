//package com.example.akhir;
//
//public class Catatan {
//    private String texs;
//    private String judul;
//    private int nomor;
//    private String imgUrl, key, linkpdf;
//
//    public Catatan (String texs, String title) {
//        this.texs = texs;
//        this.judul = title;
//    }
//
//    public Catatan(){}
//
//    public void setLinkpdf(String linkpdf) {
//        this.linkpdf = linkpdf;
//    }
//
//    public String getLinkpdf() {
//        return linkpdf;
//    }
//
//    public void setNomor(int nomor) {
//        this.nomor = nomor;
//    }
//
//    public int getNomor() {
//        return nomor;
//    }
//
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public String getJudul() {
//        return judul;
//    }
//
//    public String getTexs() {
//        return texs;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    public void setTexs(String texs) {
//        this.texs = texs;
//    }
//
//    public void setJudul(String judul) {
//        this.judul = judul;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getKey() {
//        return key;
//    }
//}

package com.example.akhir;

public class Catatan {
    private String texs;
    private String judul;
    private int nomor;
    private String imgUrl, key, linkpdf, author;

    public Catatan (String texs, String title) {
        this.texs = texs;
        this.judul = title;
    }

    public Catatan(){}

//    public String getAuthor() {
//        return author;
//    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public void setLinkpdf(String linkpdf) {
        this.linkpdf = linkpdf;
    }

    public String getLinkpdf() {
        return linkpdf;
    }

//    public void setNomor(int nomor) {
//        this.nomor = nomor;
//    }

//    public int getNomor() {
//        return nomor;
//    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getTexs() {
        return texs;
    }
    public void setTexs(String texs) {
        this.texs = texs;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}

