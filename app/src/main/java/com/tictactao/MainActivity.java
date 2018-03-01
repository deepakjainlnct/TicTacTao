package com.tictactao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button btnPlay;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radioButton;
    String player1;
    String player2;
    Button btnClick;
    Button btnOne;
    Button btnTwo;
    final Context context = this;
    LinearLayout linearLayout;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    EditText etPlayer1;
    EditText etPlayer2;
    String firstPlayer = "";
    String secondPlayer = "";
    TextView tvPlayer1;
    TextView tvPlayer2;
    boolean twoPlayer;
    String you, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoPlayer = false;
        Intent intent = getIntent();
        you = intent.getStringExtra("name");
        other = intent.getStringExtra("other");
        btnClick = (Button) findViewById(R.id.clickHere);
        etPlayer1 = (EditText) findViewById(R.id.et_player1);
        etPlayer1.setText(you);
        etPlayer2 = (EditText) findViewById(R.id.et_player2);
        etPlayer2.setText(other);
        tvPlayer1 = (TextView) findViewById(R.id.tv_player1);
        tvPlayer2 = (TextView) findViewById(R.id.tv_player2);
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout1 = (LinearLayout) findViewById(R.id.ll1);
        linearLayout2 = (LinearLayout) findViewById(R.id.ll2);
        btnPlay = (Button) findViewById(R.id.play);
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        radioGroup1 = (RadioGroup) findViewById(R.id.player1);
        radioGroup2 = (RadioGroup) findViewById(R.id.player2);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoPlayer = false;
                etPlayer2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.VISIBLE);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoPlayer = true;
                etPlayer2.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.VISIBLE);
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twoPlayer) {
                    firstPlayer = etPlayer1.getText().toString();
                    secondPlayer = etPlayer2.getText().toString();
                    if (firstPlayer.equalsIgnoreCase("")) {
                        Toast.makeText(context, "Provide first player name!", Toast.LENGTH_SHORT).show();
                    } else if (secondPlayer.equalsIgnoreCase("")) {
                        Toast.makeText(context, "Provide second player name!", Toast.LENGTH_SHORT).show();
                    } else if (firstPlayer.equalsIgnoreCase(secondPlayer)) {
                        Toast.makeText(context, "Choose different player name!", Toast.LENGTH_SHORT).show();
                    } else {
                        tvPlayer1.setText(firstPlayer);
                        tvPlayer2.setText(secondPlayer);
                        hideSoftKeyboard(MainActivity.this);
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.VISIBLE);
                    }
                } else {
                    firstPlayer = etPlayer1.getText().toString();
                    secondPlayer = "System";
                    if (firstPlayer.equalsIgnoreCase("")) {
                        Toast.makeText(context, "Provide first player name!", Toast.LENGTH_SHORT).show();
                    } else if (secondPlayer.equalsIgnoreCase("")) {
                        Toast.makeText(context, "Provide second player name!", Toast.LENGTH_SHORT).show();
                    } else if (firstPlayer.equalsIgnoreCase(secondPlayer)) {
                        Toast.makeText(context, "Choose different player name!", Toast.LENGTH_SHORT).show();
                    } else {
                        tvPlayer1.setText(firstPlayer);
                        tvPlayer2.setText(secondPlayer);
                        hideSoftKeyboard(MainActivity.this);
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sel = radioGroup1.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(sel);
                player1 = radioButton.getText().toString();
                sel = radioGroup2.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(sel);
                player2 = radioButton.getText().toString();
                if (player1.equalsIgnoreCase(player2)) {
                    Toast.makeText(context, "Both players cannot choose same symbol",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (twoPlayer) {
                        Intent intent = new Intent(context, TwoPlayerGameActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("Player1", player1);
                        intent.putExtra("Player2", player2);
                        intent.putExtra("Player1Name", firstPlayer);
                        intent.putExtra("Player2Name", secondPlayer);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(context, OnePlayerGameActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("Player1", player1);
                        intent.putExtra("Player2", player2);
                        intent.putExtra("Player1Name", firstPlayer);
                        intent.putExtra("Player2Name", secondPlayer);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (linearLayout1.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
        } else if (linearLayout2.getVisibility() == View.VISIBLE) {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
        } else {
            finish();
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
