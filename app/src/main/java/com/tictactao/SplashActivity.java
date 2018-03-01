package com.tictactao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * Created by Shivali on 07-07-2017.
 */

public class SplashActivity extends Activity {
    Context context;
    SplashCountdown countdown;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        context = this;
        countdown = new SplashCountdown((Long.valueOf("3000")), (Long.valueOf("1000")));
        countdown.start();
    }


    public class SplashCountdown extends CountDownTimer {
        public SplashCountdown(final long startTime, final long interval) {

            super(startTime, interval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name", "");
            intent.putExtra("other", "");
            startActivity(intent);
            finish();

        }
    }
}



