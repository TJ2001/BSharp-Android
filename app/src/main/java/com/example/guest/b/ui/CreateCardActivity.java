package com.example.guest.b.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.models.Card;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class  CreateCardActivity extends AppCompatActivity {
    private DatabaseReference mCardReference;
//    private ValueEventListener mCardReferenceReferenceListener;

    @Bind(R.id.createCardButton) Button mCreateCardButton;
    @Bind(R.id.frontEditText) EditText mFront;
    @Bind(R.id.backEditText) EditText mBack;
//    private ArrayList<String> cards = new ArrayList<>();

    public static final String TAG = CreateCardActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCardReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CARDS);

//        mCardReferenceReferenceListener = mCardReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
//                    String card = cardSnapshot.getValue().toString();
//                    Log.d("Interest updated", "card: " + card);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);

        mCreateCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardFront = mFront.getText().toString();
                String cardBack = mBack.getText().toString();

                saveToFirebase(cardFront, cardBack);

                mFront.setText("");
                Intent intent = new Intent(CreateCardActivity.this, DisplayCardActivity.class);
//                intent.putExtra("cardFront", cardFront);
//                intent.putExtra("cardBack", cardBack);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mCardReference.removeEventListener(mCardReferenceReferenceListener);
//    }

    private void saveToFirebase(String front, String back) {
        Card mCard = new Card(front, back);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
        DatabaseReference meetupRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CARDS);
//                .child(uid);

        DatabaseReference pushRef = meetupRef.push();
        String pushId = pushRef.getKey();
        mCard.setPushId(pushId);
        pushRef.setValue(mCard);

        mCardReference.push().setValue(mCard);
    }

//    TODO take input from three text fields, front, back, deck, save object to firebase

}
