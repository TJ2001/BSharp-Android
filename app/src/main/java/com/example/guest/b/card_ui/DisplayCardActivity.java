package com.example.guest.b.card_ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.adapters.CardPagerAdapter;
import com.example.guest.b.models.Card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DisplayCardActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private boolean mShowingBack = false;
    private ArrayList<Card> mCards = new ArrayList<>();
    private int startingPosition = 0;
    private CardPagerAdapter adapterViewPager;

    @Bind(R.id.viewPager) ViewPager mViewPager;

    private Card sentCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);
        ButterKnife.bind(this);

        getCardsFromFirebase();

//        mViewPager.setPageTransformer(true, new RotateUpTransformer());




    }

    public void getCardsFromFirebase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS);
        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mCards.add(snapshot.getValue(Card.class));
                    Log.v("card", "snapshot: " + snapshot.getValue(Card.class));

                    adapterViewPager = new CardPagerAdapter(getSupportFragmentManager(), mCards);
                    mViewPager.setAdapter(adapterViewPager);
                    mViewPager.setCurrentItem(startingPosition);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
