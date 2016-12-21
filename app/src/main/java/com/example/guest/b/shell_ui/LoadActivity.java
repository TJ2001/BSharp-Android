package com.example.guest.b.shell_ui;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.guest.b.R;
import com.example.guest.b.card_ui.CardFlipActivity;

public class LoadActivity extends AppCompatActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.alpha);
        fromBottom.reset();
        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.rLayout);
        rLayout.clearAnimation();
        rLayout.startAnimation(fromBottom);

        fromBottom = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);
        fromBottom.reset();

        RelativeLayout bLayout = (RelativeLayout) findViewById(R.id.bLayout);
        bLayout.clearAnimation();
        bLayout.startAnimation(fromBottom);
        RelativeLayout sLayout = (RelativeLayout) findViewById(R.id.sLayout);
        sLayout.clearAnimation();
        sLayout.startAnimation(fromBottom);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2700) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(LoadActivity.this,
                            CardFlipActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    LoadActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    LoadActivity.this.finish();
                }
            }
        };
        splashTread.start();

    }
}
