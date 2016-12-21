package com.example.guest.b.adapters;

import android.content.Context;

import com.example.guest.b.models.Card;
import com.google.firebase.database.ChildEventListener;

import java.util.ArrayList;

public class FirebaseCardAdapter {
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Card> mCards = new ArrayList<>();

//    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS).child(userId);
//    ref.addListenerForSingleValueEvent(new ValueEventListener(){
//
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                meetups.add(snapshot.getValue(Meetup.class));
//                Log.v("meetup", "snapshot: " + snapshot.getValue(Meetup.class));
//            }
//
//
//            int itemPosition = getLayoutPosition();
//            Log.v("position", "itemPosition: " + itemPosition);
//            Intent intent = new Intent(mContext, DisplayCardActivity.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("meetups", Parcels.wrap(mCards));
//
//            mContext.startActivity(intent);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//        }
//    });
}
