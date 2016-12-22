package com.example.guest.b.shell_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.adapters.CardPagerAdapter;
import com.example.guest.b.card_ui.CreateCardActivity;
import com.example.guest.b.card_ui.DisplayCardActivity;
import com.example.guest.b.models.Card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.createCardButton) Button mCreateCardButton;
    @Bind(R.id.displayCardsButton) Button mDisplayCardsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCreateCardButton.setOnClickListener(this);
        mDisplayCardsButton.setOnClickListener(this);

        getDecksFromFirebase();
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateCardButton) {
            startActivity(new Intent(MainActivity.this, CreateCardActivity.class));
        } else if (v == mDisplayCardsButton) {
            startActivity(new Intent(MainActivity.this, DisplayCardActivity.class));
        }
    }

    public void getDecksFromFirebase(){
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
