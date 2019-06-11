package com.example.task2delta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class ConnectGrid extends View implements View.OnTouchListener{

    private int rows, columns;
    private Paint paint;
    private int screenWidth, screenHeight;
    private int increment;
    private int xMargin;
    private int yMargin = 150;
    private char turn;
    private Drawable xImage;
    private Drawable yImage;
    public GridCell[][] spots;
    private Context context;
    private Drawable c;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ConnectGrid(Context context,int rows,int columns) {
        super ( context);
        c = getResources().getDrawable(R.drawable.bg);
        xImage = getResources().getDrawable(R.drawable.r );
        yImage = getResources().getDrawable(R.drawable.s );
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        setBackground (c);
        screenWidth = size.x;
        screenHeight = size.y;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);

        this.context = context;
        this.rows = rows;
        this.columns = columns;

        increment = increment = screenWidth / (columns + 2);
        xMargin = increment;
        spots = new GridCell[rows][columns];
        this.setOnTouchListener(this);

        turn = 'r';

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++) {
                GridCell curCell = new GridCell(xMargin + increment * col, yMargin + increment * row, xMargin + increment * (col + 1), yMargin + increment * (row + 1), xImage, yImage);

                spots[row][col] = curCell;
            }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                spots[row][col].draw(canvas, paint);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return false;

        int x = (int) event.getX();
        int y = (int) event.getY();

        for(int gX = 0; gX < spots.length; gX++){
            for(int gY = 0; gY < spots[gX].length; gY++){
                GridCell gridCell = spots[gX][gY];
                if(x > gridCell.getX1() && x < gridCell.getX2())
                    if(y > gridCell.getY1() && y < gridCell.getY2()) {
                        dropInColumn(turn, gY);


                        if (checkForLineOfFour()) {
                            finishGame(turn);
                        }

                        turn = (turn == 'r') ? 's' : 'r';

                        postInvalidate();
                        return true;
                    }
            }
        }
        return false;
    }

    private void dropInColumn(char letter, int dropColumn){
        for(int i = spots.length - 1; i > -1; i--){
            if(spots[i][dropColumn].getLetter() == ' '){
                spots[i][dropColumn].setLetter(letter);
                break;
            }
        }
    }

    private void finishGame(char turn) {
        Intent intent = new Intent(context, finalActivity.class);
        intent.putExtra ( "rows", rows );
        intent.putExtra ( "columns", columns );
        intent.putExtra("Winnner", turn);
        context.startActivity(intent);
    }

    private boolean checkForLineOfFour() {
        // Horizontal
        for (int row = 0; row < spots.length; row++) {
            int inARow = 1;
            char first = spots[row][1].getLetter();
            for (int col = 0; col < spots[row].length; col++) {
                char cur = spots[row][col].getLetter();

                if (first == cur && first != ' ')
                    inARow++;
                else {
                    first = cur;
                    inARow = 1;
                }

                if (inARow == 4) {
//					System.out.println("Horizontal");
                    return true;
                }
            }
        }

        // Vertical
        for (int col = 0; col < spots[0].length; col++) {
            int inARow = 1;
            char first = spots[1][col].getLetter();
            for (int row = 0; row < spots.length; row++) {
                char cur = spots[row][col].getLetter();

                if (first == cur && first != ' ')
                    inARow++;
                else {
                    first = cur;
                    inARow = 1;
                }

                if (inARow == 4) {
//					System.out.println("Vertical");
                    return true;
                }
            }
        }

        // Forward Diagonal
        System.out.println();
        for (int col = 0; col < spots[0].length; col++) {
            for (int row = 0; row < spots.length; row++) {
                char first = spots[row][col].getLetter();
                int inARow = 1;
                for (int diagonal = 1; diagonal <= 4; diagonal++) {
                    if (row + diagonal < rows && col + diagonal < columns) {
                        char cur = spots[row + diagonal][col + diagonal].getLetter();

                        if (first == cur && first != ' ')
                            inARow++;
                        else {
                            first = cur;
                            inARow = 1;
                        }

                        if (inARow == 4) {
//							System.out.println("Back Diagonal");
                            return true;
                        }
                    }
                }

                inARow = 1;
                for (int diagonal = 1; diagonal <= 4; diagonal++) {
                    if (row - diagonal > 0 && col + diagonal < columns) {
                        char cur = spots[row - diagonal][col + diagonal].getLetter();

                        if (first == cur && first != ' ')
                            inARow++;
                        else {
                            first = cur;
                            inARow = 1;
                        }

                        if (inARow == 4) {
//							System.out.println("Forward Diagonal");
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

}