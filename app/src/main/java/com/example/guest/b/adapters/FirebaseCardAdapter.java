package com.example.guest.b.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.guest.b.Constants;
import com.example.guest.b.card_ui.DisplayCardActivity;
import com.example.guest.b.models.Card;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCardAdapter {
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Card> mCards = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS);
    ref.addListenerForSingleValueEvent(new ValueEventListener(){

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                mCards.add(snapshot.getValue(Card.class));
                Log.v("card", "snapshot: " + snapshot.getValue(Card.class));
            }


//            int itemPosition = getLayoutPosition();
//            Log.v("position", "itemPosition: " + itemPosition);
            Intent intent = new Intent(mContext, DisplayCardActivity.class);
//            intent.putExtra("position", itemPosition);
            intent.putExtra("meetups", Parcels.wrap(mCards));

            mContext.startActivity(intent);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    });
}
