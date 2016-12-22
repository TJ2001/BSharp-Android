package com.example.guest.b.card_ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.b.R;
import com.example.guest.b.models.Card;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardFrontFragment extends Fragment {
@Bind(R.id.frontCardText) TextView mFrontCardText;
    private Card mCard;

    public static CardFrontFragment newInstance(Card card) {
        CardFrontFragment cardFrontFragment = new CardFrontFragment();
        Bundle args = new Bundle();
        args.putParcelable("card", Parcels.wrap(card));
        cardFrontFragment.setArguments(args);
        return cardFrontFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCard = Parcels.unwrap(getArguments().getParcelable("card"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_front, container, false);
        ButterKnife.bind(this, view);
        mFrontCardText.setText(mCard.getFront());

        return view;
    }
}
