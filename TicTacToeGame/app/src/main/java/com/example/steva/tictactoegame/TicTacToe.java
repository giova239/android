package com.example.steva.tictactoegame;

/**
 * Created by steva on 08-Feb-18.
 */

public class TicTacToe {

    private char[][] board;
    private char currentPlayerMark;
    private boolean playerOneTurn;

    public TicTacToe() {
        this.playerOneTurn=true;
        board = new char[3][3];
        initializeBoard();
    }


    // Set/Reset the board back to all empty values and swap starting turn.
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        if(playerOneTurn){
            currentPlayerMark = 'x';
        }
        else{
            currentPlayerMark = 'o';
        }

        playerOneTurn=!playerOneTurn;
    }


    // Returns true if the board is full, false otherwise.
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }


    // Returns true if there is a win, false otherwise.
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }


    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }


    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }


    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }


    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }


    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }
        else {
            currentPlayerMark = 'x';
        }
    }


    // Places a mark at the cell specified by row and col with the mark of the current player.
    //Returns true if the place is right, false if the cell is already occupied.
    public boolean placeMark(int row, int col) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }else{
                    return false;
                }
    }

    public char[][] getBoard(){
        return this.board;
    }

    public char getPlayer() {return this.currentPlayerMark;}

    public void computer_move_easy(){
        int move;
        do{
            move = (int) (Math.random() * 9);
        }
        while(!placeMark(move/3,move%3));
    }

    public String winnerToString() {

        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] == (this.board[i][1])
                    && this.board[i][0] == (this.board[i][2])
                    && !(this.board[i][0] == '-')) {
                return i+"0"+i+"1"+i+"2";
            }
        }

        for (int i = 0; i < 3; i++) {
            if (this.board[0][i] == (this.board[1][i])
                    && this.board[0][i] == (this.board[2][i])
                    && !(this.board[0][i] == '-')) {
                return "0"+i+"1"+i+"2"+i;
            }
        }

        if (this.board[0][0] == (this.board[1][1])
                && this.board[0][0] == (this.board[2][2])
                && !(this.board[0][0] == '-')) {
            return "001122";
        }

        if (this.board[0][2] == (this.board[1][1])
                && this.board[0][2] == (this.board[2][0])
                && !(this.board[0][2] == '-')) {
            return "021120";
        }
        return "";
    }

    public boolean getPlayerOneTurn (){
        return this.playerOneTurn;
    }
}