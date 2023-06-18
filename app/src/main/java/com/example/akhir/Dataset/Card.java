package com.example.akhir.Dataset;

import android.net.Uri;

public class Card {
    public String id;
    public int imageId;
    public String title;
    public String description;
    public CardType type;
    public Uri imageUri;

    public Card(String title, String description, CardType type, Uri imageUri) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.imageUri = imageUri;
    }

    public Card(String id, String title, String description, CardType type, Uri imageUri) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.imageUri = imageUri;
    }

    public Card(int imageId, String title, String description, CardType type) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}

