package com.example.guest.b.card_ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.b.R;

/**
 * Created by Guest on 12/21/16.
 */
public class CardBackFragment extends Fragment{
    public CardBackFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_back, container, false);
    }
}
