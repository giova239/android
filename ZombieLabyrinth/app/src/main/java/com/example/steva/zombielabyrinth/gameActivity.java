package com.example.steva.zombielabyrinth;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class gameActivity extends AppCompatActivity {

    float x1,x2,y1,y2;
    final static float MIN_DISTANCE = 150.0f;

    Labyrinth game;
    cell cellFactory = new cell();

    int xPosition;
    int yPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();

        game = new Labyrinth(extras.getInt("row",0),extras.getInt("column", 0));

        xPosition = game.getStartX();
        yPosition = game.getStartY();

        Log.d("debug", game.toString());

        changeFragment("moveUp");
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float deltaX =Math.abs(x2 - x1);
                float deltaY =Math.abs(y2 - y1);

                if(deltaX > deltaY){
                    //x
                    if (deltaX > MIN_DISTANCE) {
                        if (x2 > x1) {
                            //swipe right
                            //Toast.makeText(this, "SWIPE RIGHT", Toast.LENGTH_SHORT).show ();
                            this.findViewById(R.id.player).setRotation(180);
                            //180

                            try{
                                if(game.maz[yPosition][xPosition-1] != '*'){
                                    xPosition --;
                                    changeFragment("moveLeft");
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException exception){

                            }
                        }
                        else {
                            //swipe left
                            //Toast.makeText(this, "SWIPE LEFT", Toast.LENGTH_SHORT).show ();
                            this.findViewById(R.id.player).setRotation(0);
                            //0

                            try{
                                if(game.maz[yPosition][xPosition+1] != '*'){
                                    xPosition ++;
                                    changeFragment("moveRight");
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException exception){

                            }
                        }
                    }
                }else{
                    //y
                    if (deltaY > MIN_DISTANCE) {
                        if (y2 > y1) {
                            //swipe down
                            //Toast.makeText(this, "SWIPE DOWN", Toast.LENGTH_SHORT).show();
                            this.findViewById(R.id.player).setRotation(270);
                            //270

                            try{
                                if(game.maz[yPosition-1][xPosition] != '*'){
                                    yPosition --;
                                    changeFragment("moveUp");
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException exception){

                            }
                        } else {
                            //swipe up
                            //Toast.makeText(this, "SWIPE UP", Toast.LENGTH_SHORT).show();
                            this.findViewById(R.id.player).setRotation(90);
                            //90

                            try{
                                if(game.maz[yPosition+1][xPosition] != '*'){
                                    yPosition ++;
                                    changeFragment("moveDown");
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException exception){

                            }
                        }
                    }
                }

                checkwin();

                if(deltaX < MIN_DISTANCE && deltaY < MIN_DISTANCE){
                    //tap
                    //Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show ();

                    //SHOOT
                }

        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(String move) {


        FragmentManager fm = getFragmentManager();
        Log.d("debug", "current on:" + this.game.maz[yPosition][xPosition]);
        cell temp = cellFactory.getFragment(getDrawableName(),this.game.maz[yPosition][xPosition] == 'S', this.game.maz[yPosition][xPosition] == 'E');
        Fragment nextFragment = temp;
        FragmentTransaction ft = fm.beginTransaction();

        switch(move){
            case "moveUp":
                ft.setCustomAnimations(R.animator.slide_in_top, R.animator.slide_out_down);
                break;
            case "moveDown":
                ft.setCustomAnimations(R.animator.slide_in_down, R.animator.slide_out_top);
                break;
            case "moveLeft":
                ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                break;
            case "moveRight":
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
        }
        ft.replace(R.id.fragment_container, nextFragment);
        ft.commit();

        /*Thread playerAnimator = new Thread()
        {
            public void run()
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gameActivity.super.findViewById(R.id.player).setBackground(R.drawable.move1);
                }
            }
        };
        playerAnimator.start();*/
    }

    private String getDrawableName(){
        String temp = "labyrinth";

        try {
            if(this.game.maz[yPosition-1][xPosition] != '*'){       //1
                temp += "1";
            }else{
                temp += "0";
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            temp+= "0";
            Log.d("debug", "nullFirst");
        }

        try {
            if(this.game.maz[yPosition][xPosition+1] != '*'){       //2
                temp += "1";
            }else{
                temp += "0";
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            temp+= "0";
            Log.d("debug", "nullSecond");
        }

        try {
            if(this.game.maz[yPosition+1][xPosition] != '*'){       //3
                temp += "1";
            }else{
                temp += "0";
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            temp+= "0";
            Log.d("debug", "nullThird");
        }

        try {
            if(this.game.maz[yPosition][xPosition-1] != '*'){       //4
                temp += "1";
            }else{
                temp += "0";
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            temp+= "0";
            Log.d("debug", "nullFourth");
        }

        return temp;
    }

    public void checkwin(){
        if(game.maz[yPosition][xPosition] == 'E'){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    AlertDialog.Builder altdial = new AlertDialog.Builder(gameActivity.this);
                    altdial.setMessage("YOU WON!").setCancelable(false);
                    altdial.setPositiveButton("REMATCH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            gameActivity.super.recreate();
                        }
                    });
                    altdial.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog alert = altdial.create();
                    alert.setTitle("GAME OVER");
                    alert.show();
                }
            }, 1000);
        }
    }
}
