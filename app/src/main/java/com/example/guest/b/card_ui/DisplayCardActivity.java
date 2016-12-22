package com.example.guest.b.card_ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.models.Card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class DisplayCardActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    private Handler mHandler = new Handler();
    private boolean mShowingBack = false;
    private ArrayList<Card> mCards = new ArrayList<>();
    private int position = 0;

    private Card sentCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        } else {
            mShowingBack = (getSupportFragmentManager().getBackStackEntryCount() > 0);
        }

        getCardsFromFirebase();




        getSupportFragmentManager().addOnBackStackChangedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.previous, menu);
        inflater.inflate(R.menu.next, menu);
        ButterKnife.bind(this);

        super.onCreateOptionsMenu(menu);

        MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                mShowingBack
                        ? R.string.action_photo
                        : R.string.action_info);
        item.setIcon(mShowingBack
                ? R.drawable.ic_action_photo
                : R.drawable.ic_action_info);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                NavUtils.navigateUpTo(this, new Intent(this, CreateCardActivity.class));
                return true;

            case R.id.action_flip:
                flipCard();
                return true;

            //adding actions to icons in the menu bar.

            case R.id.action_previous:
                //previousCard();
                return true;

            case R.id.action_next:
                //nextCard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;

        getSupportFragmentManager()
                .beginTransaction()

                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                .replace(R.id.container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

//    private void nextCard(){
//        mPager.setCurrentItem(getItem(+1), true);
//
//    }
//
//    private void previousCard(){
//        mPager.setCurrentItem(getItem(-1), true);
//
//    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getSupportFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }

    public void getCardsFromFirebase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS);
        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mCards.add(snapshot.getValue(Card.class));
                    Log.v("card", "snapshot: " + snapshot.getValue(Card.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public Card getMyData() {
        sentCard = mCards.get(0);
        return sentCard;
    }

}
