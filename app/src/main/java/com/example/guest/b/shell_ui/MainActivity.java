package com.example.guest.b.shell_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.b.Constants;
import com.example.guest.b.R;
import com.example.guest.b.card_ui.CreateCardActivity;
import com.example.guest.b.card_ui.DisplayCardActivity;
import com.example.guest.b.models.Deck;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Deck> mDecks = new ArrayList<>();
    private String mDeckType;

    @Bind(R.id.createCardButton) Button mCreateCardButton;
    @Bind(R.id.displayCardsButton) Button mDisplayCardsButton;
    @Bind(R.id.gridview) GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCreateCardButton.setOnClickListener(this);
        mDisplayCardsButton.setOnClickListener(this);

        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new GridViewAdapter(this));

//        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<Deck>
//                (this,android.R.layout.simple_list_item_1, mDecks);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DECKS);

        ListAdapter adapter = new FirebaseListAdapter<Deck>(this, Deck.class, R.layout.grid_view_item, ref)
        {
            @Override
            protected void populateView(View v, Deck model, int position) {
                ImageView image = (ImageView)v.findViewById(R.id.imageView);
                TextView name = (TextView)v.findViewById(R.id.Name);
//
                mDeckType = model.getDeckType();
                name.setText(mDeckType);
            }

        };
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {
                    Toast.makeText(MainActivity.this, "it's clickable" + position,
                        Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this, DisplayCardActivity.class);

               intent.putExtra("deck", mDecks.get(position).deckType);
               startActivity(intent);
            }
        });

        getDeckFromFirebase();
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateCardButton) {
            startActivity(new Intent(MainActivity.this, CreateCardActivity.class));
        } else if (v == mDisplayCardsButton) {
            startActivity(new Intent(MainActivity.this, DisplayCardActivity.class));
        }
    }

    public void getDeckFromFirebase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS);
        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mDecks.add(snapshot.getValue(Deck.class));
                    Log.v("card", "snapshot: " + snapshot.getValue(Deck.class));



//                    gridViewAdapter = new GridViewAdapter(getSupportFragmentManager(), mDecks);
//                    mGridView.setAdapter(gridViewAdapter);
//                    mGridView.setCurrentItem(startingPosition);



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}
