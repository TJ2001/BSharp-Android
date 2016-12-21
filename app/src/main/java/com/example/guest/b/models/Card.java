package com.example.guest.b.models;

/**
 * Created by Guest on 12/20/16.
 */
public class Card {
    public String deck, front, back, pushId;

    public Card() {};

    public Card(String deck, String front, String back) {
        this.deck = deck;
        this.front = front;
        this.back = back;
    }

    public String getDeck() { return deck; }

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
