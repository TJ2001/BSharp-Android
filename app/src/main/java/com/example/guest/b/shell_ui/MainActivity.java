package com.example.guest.b.shell_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guest.b.R;
import com.example.guest.b.card_ui.CreateCardActivity;
import com.example.guest.b.card_ui.DisplayCardActivity;

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
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateCardButton) {
            startActivity(new Intent(MainActivity.this, CreateCardActivity.class));
        } else if (v == mDisplayCardsButton) {
            startActivity(new Intent(MainActivity.this, DisplayCardActivity.class));
        }
    }
}
