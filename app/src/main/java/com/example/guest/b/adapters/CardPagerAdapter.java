package com.example.guest.b.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.b.card_ui.CardBackFragment;
import com.example.guest.b.card_ui.CardFrontFragment;
import com.example.guest.b.models.Card;

import java.util.ArrayList;

public class CardPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Card> mCards;

    public CardPagerAdapter(FragmentManager fm, ArrayList<Card> cards) {
        super(fm);
        mCards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        if ( position % 2 == 0 ) {
            return CardFrontFragment.newInstance(mCards.get(position));
        } else {
            return CardBackFragment.newInstance(mCards.get(position));
        }
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if ( position % 2 == 0 ){
            return mCards.get(position).getFront();
        } else {
            return mCards.get(position).getBack();
        }
    }
}
