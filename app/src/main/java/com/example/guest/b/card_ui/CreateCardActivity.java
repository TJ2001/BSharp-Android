package com.example.guest.b.card_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.models.Card;
import com.example.guest.b.models.Deck;
import com.example.guest.b.shell_ui.MainActivity;
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
    @Bind(R.id.deckEditText) EditText mDeckType;

//    private ArrayList<String> cards = new ArrayList<>();

    public static final String TAG = CreateCardActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);

        mCreateCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardFront = mFront.getText().toString();
                String cardBack = mBack.getText().toString();
                String deckType = mDeckType.getText().toString().toUpperCase();

                saveToFirebase(deckType, cardFront, cardBack);
                saveToFirebase(deckType, cardFront, cardBack);

                mFront.setText("");
                Intent intent = new Intent(CreateCardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveToFirebase(String deckType, String front, String back) {
        Card mCard = new Card(deckType, front, back);
        DatabaseReference cardRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CARDS).child(deckType);

        DatabaseReference pushRef = cardRef.push();
        String pushId = pushRef.getKey();
        mCard.setPushId(pushId);
        pushRef.setValue(mCard);
    }

}
