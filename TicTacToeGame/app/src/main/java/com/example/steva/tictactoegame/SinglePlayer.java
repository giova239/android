package com.example.steva.tictactoegame;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class SinglePlayer extends AppCompatActivity {

    private static TicTacToe game = new TicTacToe();

    private static Button buttons[][] = new Button[3][3];

    private static int playerPoints=0;

    private static int cpuPoints=0;

    private static TextView textViewPlayer;

    private static TextView textViewCpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        textViewPlayer = findViewById(R.id.text_view_p1);
        textViewCpu = findViewById(R.id.text_view_p2);

        printTurn();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                int row = j;
                int col = i;
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener((View view) -> {
                    if (!game.placeMark(row, col)) {
                        return;
                    }
                    updateField();
                    if (game.checkForWin()) {
                        playerPoints++;
                        updatePoints();
                        dialog('x');
                    } else if (game.isBoardFull()) {
                        dialog('d');
                    } else {
                        game.changePlayer();

                        for (int r = 0; r < 3; r++) {
                            for (int c = 0; c < 3; c++) {
                                buttons[r][c].setClickable(false);
                            }
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                game.computer_move_easy();
                                updateField();
                                if (game.checkForWin()) {
                                    cpuPoints++;
                                    updatePoints();
                                    dialog('o');
                                } else if (game.isBoardFull()) {
                                    dialog('d');
                                } else {
                                    game.changePlayer();
                                }
                                for (int r = 0; r < 3; r++) {
                                    for (int c = 0; c < 3; c++) {
                                        buttons[r][c].setClickable(true);
                                    }
                                }
                            }
                        }, 400);
                    }

                });
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);

        buttonReset.setOnClickListener((view)-> {
            playerPoints=0;
            cpuPoints=0;
            updatePoints();
            game = new TicTacToe();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
            printTurn();
        });

    }
    protected void dialog(char player){
        String s = game.winnerToString();
        if(player!='d'){
            buttons[Character.getNumericValue(s.charAt(1))][Character.getNumericValue(s.charAt(0))].setBackgroundColor(Color.RED);
            buttons[Character.getNumericValue(s.charAt(3))][Character.getNumericValue(s.charAt(2))].setBackgroundColor(Color.RED);
            buttons[Character.getNumericValue(s.charAt(5))][Character.getNumericValue(s.charAt(4))].setBackgroundColor(Color.RED);
        }

        AlertDialog.Builder altdial = new AlertDialog.Builder(SinglePlayer.this);
        if(player == 'x') {
            altdial.setMessage("YOU WON!").setCancelable(false);
        }else if(player == 'o'){
            altdial.setMessage("CPU WON!").setCancelable(false);
        }else{
            altdial.setMessage("DRAW!").setCancelable(false);
        }
        altdial.setPositiveButton("REMATCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                game.initializeBoard();
                updateField();
                if(player!='d') {
                    buttons[Character.getNumericValue(s.charAt(1))][Character.getNumericValue(s.charAt(0))].setBackgroundColor(Color.WHITE);
                    buttons[Character.getNumericValue(s.charAt(3))][Character.getNumericValue(s.charAt(2))].setBackgroundColor(Color.WHITE);
                    buttons[Character.getNumericValue(s.charAt(5))][Character.getNumericValue(s.charAt(4))].setBackgroundColor(Color.WHITE);
                }
                printTurn();
                if(game.getPlayerOneTurn()){
                    game.computer_move_easy();
                    updateField();
                    game.changePlayer();
                }
            }
        });
        altdial.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                game.initializeBoard();
                finish();
            }
        });

        AlertDialog alert = altdial.create();
        alert.setTitle("GAME OVER");
        alert.show();
    }

    protected void updateField(){
        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!buttons[j][i].getText().equals(Character.toString(board[i][j])) && !(board[i][j] == '-' && buttons[j][i].getText().equals(""))) {
                    if(board[i][j]  == '-'){
                        buttons[j][i].setText("");
                    }else{
                        buttons[j][i].setText(Character.toString(board[i][j]));
                    }
                }
            }
        }
    }

    protected void printTurn(){
        if(game.getPlayer()=='x'){
            makeText(this, "Player 1 turn!", Toast.LENGTH_SHORT).show();
        }else{
            makeText(this, "Player 2 turn!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void updatePoints(){
        textViewPlayer.setText("you:\n" + playerPoints);
        textViewCpu.setText("cpu:\n" + cpuPoints);
    }

}
