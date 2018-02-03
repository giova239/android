package com.example.steva.tictactoe;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import static android.widget.Toast.*;

public class ActivitySinglePlayer extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;
    private boolean swapTurn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        makeText(this, "Your turn!", LENGTH_SHORT).show();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener((view)-> {
                    if (!((Button) view).getText().toString().equals("")) {
                        return;
                    }

                    if (player1Turn) {
                        ((Button) view).setText("X");
                    } else {
                        ((Button) view).setText("O");
                    }

                    roundCount++;

                    if (checkForWin()) {
                        if (player1Turn) {
                            player1Wins();
                        } else {
                            player2Wins();
                        }
                    } else if (roundCount == 9) {
                        draw();
                    } else {
                        player1Turn = !player1Turn;
                        if(!player1Turn)answer();
                    }
                });
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);

        buttonReset.setOnClickListener((view)-> {
            player1Points = 0;
            player2Points = 0;
            updatePointsText();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
            roundCount = 0;
            player1Turn = true;
            makeText(this, "Your turn!", LENGTH_SHORT).show();
        });
    }
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }



    private void player1Wins() {
        player1Points++;
        makeText(this, "You won!", LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }



    private void player2Wins() {
        player2Points++;
        makeText(this, "Cpu wins!", LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }



    private void draw() {
        makeText(this, "Draw!", LENGTH_SHORT).show();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setClickable(true);
                    }
                }
                roundCount = 0;
                player1Turn = !swapTurn;
                swapTurn = !swapTurn;
                if(player1Turn){
                    makeText(getApplicationContext(), "Your turn!", LENGTH_SHORT).show();
                }else{
                    makeText(getApplicationContext(), "Cpu turn!", LENGTH_SHORT).show();
                    answer();
                }
            }
        }, 3000);
    }



    private void updatePointsText() {
        textViewPlayer1.setText("you:\n" + player1Points);
        textViewPlayer2.setText("cpu:\n" + player2Points);
    }



    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }

        String s = colorWinner();
        buttons[Character.getNumericValue(s.charAt(0))][Character.getNumericValue(s.charAt(1))].setBackgroundColor(Color.RED);
        buttons[Character.getNumericValue(s.charAt(2))][Character.getNumericValue(s.charAt(3))].setBackgroundColor(Color.RED);
        buttons[Character.getNumericValue(s.charAt(4))][Character.getNumericValue(s.charAt(5))].setBackgroundColor(Color.RED);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setClickable(true);
                    }
                }
                buttons[Character.getNumericValue(s.charAt(0))][Character.getNumericValue(s.charAt(1))].setBackgroundColor(Color.BLACK);
                buttons[Character.getNumericValue(s.charAt(2))][Character.getNumericValue(s.charAt(3))].setBackgroundColor(Color.BLACK);
                buttons[Character.getNumericValue(s.charAt(4))][Character.getNumericValue(s.charAt(5))].setBackgroundColor(Color.BLACK);
                roundCount = 0;
                player1Turn = !swapTurn;
                swapTurn = !swapTurn;
                if(player1Turn){
                    makeText(getApplicationContext(), "Your turn!", LENGTH_SHORT).show();
                }else{
                    makeText(getApplicationContext(), "Cpu turn!", LENGTH_SHORT).show();
                    answer();
                }
            }
        }, 3000);
    }

    private String colorWinner() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return i+"0"+i+"1"+i+"2";
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return "0"+i+"1"+i+"2"+i;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return "001122";
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return "021120";
        }
        return "";
    }

    public void answer(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random rand= new Random();
                int randomNum1 = rand.nextInt(3);
                Random rand2= new Random();
                int randomNum2 = rand2.nextInt(3);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setClickable(true);
                    }
                }
                if(buttons[randomNum1][randomNum2].getText()=="") {
                    buttons[randomNum1][randomNum2].performClick();
                }else{
                    answer();
                }
            }
        }, 1500);
    }
}