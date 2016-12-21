package com.example.guest.b.card_ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.b.R;
import com.example.guest.b.models.Card;

/**
 * Created by Guest on 12/21/16.
 */
public class CardFrontFragment extends Fragment {
    public CardFrontFragment() {
    }

    public static CardFrontFragment newInstance(Card card) {
        CardFrontFragment meetupDetailFragment = new CardFrontFragment();
        Bundle args = new Bundle();
//        args.putParcelable("card", Parcels.wrap(card));
        meetupDetailFragment.setArguments(args);
        return meetupDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_front, container, false);
    }
}
