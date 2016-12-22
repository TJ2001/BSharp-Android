package com.example.guest.b.models;

import org.parceler.Parcel;

@Parcel
public class Card {
    public String deckType, front, back, pushId;

    public Card() {};

    public Card(String deck, String front, String back) {
        this.deckType = deck;
        this.front = front;
        this.back = back;
    }

    public String getDeckType() { return deckType; }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
