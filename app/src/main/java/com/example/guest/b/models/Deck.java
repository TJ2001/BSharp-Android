package com.example.guest.b.models;

import java.util.ArrayList;

public class Deck {
//    public ArrayList<Card> subject = new ArrayList<>();
    public String deckType;
    public String pushId;

    public Deck() {};

    public Deck(String deckType) {
        this.deckType = deckType;
    }

    //    public ArrayList<Card> getSubject() {
//        return subject;
//    }


    public String getDeckType() {
        return deckType;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
