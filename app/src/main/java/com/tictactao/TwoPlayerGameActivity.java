package com.tictactao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.*;

public class TwoPlayerGameActivity extends Activity {
    String player1Turn;
    String player2Turn;
    String player1Name;
    String player2Name;
    static int i = 0;
    int j;
    GridView gridview;
    int visitedp1[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int visitedp2[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    final Context context = this;
    TextView textView;
    TextView tvHeading;
    TextView tvTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Intent intent = getIntent();
        player1Turn = intent.getStringExtra("Player1");
        player2Turn = intent.getStringExtra("Player2");
        player1Name = intent.getStringExtra("Player1Name");
        player2Name = intent.getStringExtra("Player2Name");
        tvHeading = (TextView) findViewById(R.id.tv_heading);
        tvTurn = (TextView) findViewById(R.id.tv_turn);
        tvHeading.setText("Game playing between " + player1Name + " and " + player2Name);
        tvTurn.setText(player1Name + " your turn!");
        gridview = (GridView) findViewById(R.id.gridview1);
        for (int k = 0; k < 9; ++k) {
            visitedp1[k] = -1;
            visitedp2[k] = -1;
        }
        i = 0;
        GridAdapter adapter = new GridAdapter(this);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int flag = 0;
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_grid);
                CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view);
                textView = (TextView) cardView.findViewById(R.id.tv_move);
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
                        textView.setText(player1Turn);
                        visitedp1[position] = 1;
                    } else {
                        tvTurn.setText(player1Name + " your turn!");
                        textView.setText(player2Turn);
                        visitedp2[position] = 1;
                    }
                    check(visitedp1, visitedp2);
                    i = i + 1;
                }
            }

        });
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
        if (flag == 1) {
            Intent intent = new Intent(context, WinActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("winner", winner);
            intent.putExtra("you", player1Name);
            intent.putExtra("other", player2Name);
            startActivity(intent);
            finish();
        }
        else if (i == 8) {
            Intent gamedraw = new Intent(context, DrawGameActivity.class);
            gamedraw.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            gamedraw.putExtra("you", player1Name);
            gamedraw.putExtra("other", player2Name);
            startActivity(gamedraw);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
