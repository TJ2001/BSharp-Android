package com.example.guest.b.models;

import org.parceler.Parcel;

@Parcel
public class Card {
    public String deckType, front, back, pushId;

    public Card() {};

    public Card(String deckType, String front, String back) {
        this.front = front;
        this.back = back;
        this.deckType = deckType;
    }

    public String getDeckType() {return deckType; }

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
