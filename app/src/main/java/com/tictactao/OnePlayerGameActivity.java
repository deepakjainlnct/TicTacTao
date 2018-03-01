package com.tictactao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class OnePlayerGameActivity extends Activity {
    String player1Turn;
    String player2Turn;
    String player1Name;
    AtomicBoolean winflag= new AtomicBoolean();
    String player2Name;
    static int i = 0;
    int j;
    GridView gridview;
    int visitedp1[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int visitedp2[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    final Context context = this;
    //TextView textView;
    TextView tvHeading;
    TextView tvTurn;
    public transient CountDown coustomAds;
    OneGridAdapter adapter;
    ArrayList<OnePlayerModel> list;
    int performPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        winflag.set(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Intent intent = getIntent();
        player1Turn = intent.getStringExtra("Player1");
        player2Turn = intent.getStringExtra("Player2");
        player1Name = intent.getStringExtra("Player1Name");
        player2Name = intent.getStringExtra("Player2Name");
        tvHeading = (TextView) findViewById(R.id.tv_heading);
        list = new ArrayList<>();
        tvTurn = (TextView) findViewById(R.id.tv_turn);
        tvHeading.setText("Game playing between " + player1Name + " and " + player2Name);
        tvTurn.setText(player1Name + " your turn!");
        gridview = (GridView) findViewById(R.id.gridview1);
        for (int k = 0; k < 9; ++k) {
            visitedp1[k] = -1;
            visitedp2[k] = -1;
        }
        i = 0;
        final String[] str = new String[1];
        adapter = new OneGridAdapter(this);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int flag = 0;
                for (j = 0; j < 9; ++j) {
                    if (visitedp1[position] == 1 || visitedp2[position] == 1) {
                        flag = 1;
                        Toast.makeText(context, "Invalid move....", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (flag == 0) {
                    if (i % 2 == 0) {
                        tvTurn.setText(player2Name + " your turn!");
                        str[0] = player1Turn;
                        visitedp1[position] = 1;
                    } else {
                        tvTurn.setText(player1Name + " your turn!");
                        str[0] = player2Turn;
                        visitedp2[position] = 1;
                    }
                    highlightListItem(position, str);
                    check(visitedp1, visitedp2);
                    i = i + 1;
                    if (position != performPosition && i < 8) {
                        coustomAds = new CountDown((Long.valueOf("500")), (Long.valueOf("100")));
                        coustomAds.start();
                    }
                }
            }
        });
    }

    private void highlightListItem(int position, String[] str) {
        OnePlayerModel onePlayerModel = new OnePlayerModel();
        onePlayerModel.setMove(str[0]);
        onePlayerModel.setPosition(position);
        list.add(onePlayerModel);
        OneGridAdapter contactsAdapter = (OneGridAdapter) gridview.getAdapter();
        contactsAdapter.setSelectedItem(list);
        gridview.setAdapter(contactsAdapter);
    }

    public void check(int visitedp1[], int visitedp2[]) {
        int flag = 0;
        String winner = null;
        // player1
        if ((visitedp1[0] == 1) && (visitedp1[4] == 1) && (visitedp1[8] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[2] == 1) && (visitedp1[4] == 1)
                && (visitedp1[6] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[0] == 1) && (visitedp1[3] == 1)
                && (visitedp1[6] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[1] == 1) && (visitedp1[4] == 1)
                && (visitedp1[7] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[2] == 1) && (visitedp1[5] == 1)
                && (visitedp1[8] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[0] == 1) && (visitedp1[1] == 1)
                && (visitedp1[2] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[3] == 1) && (visitedp1[4] == 1)
                && (visitedp1[5] == 1)) {
            flag = 1;
            winner = player1Name;
        } else if ((visitedp1[6] == 1) && (visitedp1[7] == 1)
                && (visitedp1[8] == 1)) {
            flag = 1;
            winner = player1Name;
        }


        // player2
        if ((visitedp2[0] == 1) && (visitedp2[4] == 1) && (visitedp2[8] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[2] == 1) && (visitedp2[4] == 1)
                && (visitedp2[6] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[0] == 1) && (visitedp2[3] == 1)
                && (visitedp2[6] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[1] == 1) && (visitedp2[4] == 1)
                && (visitedp2[7] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[2] == 1) && (visitedp2[5] == 1)
                && (visitedp2[8] == 1)) {
            flag = 1;
            winner = player2Name;

        } else if ((visitedp2[0] == 1) && (visitedp2[1] == 1)
                && (visitedp2[2] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[3] == 1) && (visitedp2[4] == 1)
                && (visitedp2[5] == 1)) {
            flag = 1;
            winner = player2Name;
        } else if ((visitedp2[6] == 1) && (visitedp2[7] == 1)
                && (visitedp2[8] == 1)) {
            flag = 1;
            winner = player2Name;
        }

        if (flag == 1 && !winflag.get()) {
            Intent intent = new Intent(context, WinActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("winner", winner);
            intent.putExtra("you", player1Name);
            intent.putExtra("other", "");
            startActivity(intent);
            finish();
            winflag.set(true);
        } else if (i == 8) {
            Intent gamedraw = new Intent(context, DrawGameActivity.class);
            gamedraw.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            gamedraw.putExtra("you", player1Name);
            gamedraw.putExtra("other", "");
            startActivity(gamedraw);
            finish();

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public class CountDown extends CountDownTimer {
        /**
         * Initialize BEGameCountDownTimer
         */
        public CountDown(final long startTime, final long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long count) {

        }

        /**
         * Initialize onFinish
         */
        @Override
        public void onFinish() {
            try {
                callSystem();
            } catch (Exception e) {
            }
        }
    }

    private void callSystem() {
        try {
            boolean flag = false;
            int position = generateRandomNumber(0, 8);
            for (OnePlayerModel onePlayerModel : list) {
                if (onePlayerModel.getPosition() == position) {
                    flag = true;
                }
            }
            if (flag) {
                callSystem();
            } else {
                performPosition = position;
                gridview.performItemClick(gridview, position, gridview.getItemIdAtPosition(position));
            }
        } catch (Exception e) {
        }
    }

    public static int generateRandomNumber(final int min, final int max) {
        final Random randomNumber = new Random();
        return randomNumber.nextInt(max - min + 1) + min;
    }

}
