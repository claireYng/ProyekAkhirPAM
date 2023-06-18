//package com.example.akhir;
//
//public class Materi {
//    String nama,img, key, bapak, pop;
//    public Materi(String nama, String img){
//        this.img = img;
//        this.nama = nama;
//    }
//    public Materi(){}
//
//    public void setPop(String pop) {
//        this.pop = pop;
//    }
//
//    public String getPop() {
//        return pop;
//    }
//
//    public String getNama() {
//        return nama;
//    }
//
//    public String getBapak() {
//        return bapak;
//    }
//
//    public void setBapak(String bapak) {
//        this.bapak = bapak;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public void setNama(String nama) {
//        this.nama = nama;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//}

package com.example.akhir;

public class Materi {
    String Nama,img, key, bapak, pop,author;
    public Materi(String nama, String img){
        this.img = img;
        this.Nama = nama;
    }
    public Materi(){}

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPop() {
        return pop;
    }

    public String getNama() {
        return Nama;
    }

    public String getBapak() {
        return bapak;
    }

    public void setBapak(String bapak) {
        this.bapak = bapak;
    }

    public String getImg() {
        return img;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

