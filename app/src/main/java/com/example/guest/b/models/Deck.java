package com.example.guest.b.models;

import java.util.ArrayList;

public class Deck {
    public ArrayList<Card> subject = new ArrayList<>();
    public String pushId;

    public Deck() {};

    public ArrayList<Card> getSubject() {
        return subject;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
