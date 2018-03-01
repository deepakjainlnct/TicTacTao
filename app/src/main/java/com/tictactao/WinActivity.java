package com.tictactao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WinActivity extends Activity {
    TextView winn;
    Button btnPlayAgain, quit;
    final Context context = this;
    public static transient Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_layout);
        Intent intent = getIntent();
        String win = intent.getStringExtra("winner");
        final String you = intent.getStringExtra("you");
        final String other = intent.getStringExtra("other");
        winn = (TextView) findViewById(R.id.wintxt);
        btnPlayAgain = (Button) findViewById(R.id.back);
        quit = (Button) findViewById(R.id.quit);
        winn.setText(win + " you won the game!!!");
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name", you);
                i.putExtra("other", other);
                startActivity(i);
                finish();
            }

        });
        quit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
        appExitDialog();
    }

    /**
     * Method for App Exit
     */
    public void appExitDialog() {
        try {
            if (exit) {
                exitHandler();
            } else {
                Toast.makeText(this, "Tap again to exit.", Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 2 * 1000);
            }
        } catch (Exception e) {
        }
    }

    public void exitHandler() {
        try {
            finish();
        } catch (Exception e) {
        }
    }

}
