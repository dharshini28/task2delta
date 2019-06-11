package com.example.task2delta;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class GridCell {

    private float x1, y1, x2, y2;
    private char letter;
    private Drawable yDrawable, xDrawable;

    public GridCell(float x1, float y1, float x2, float y2, Drawable xDrawable, Drawable yDrawable) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.yDrawable = yDrawable;
        this.xDrawable = xDrawable;
        this.letter = ' ';
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine ( x1, y1, x2, y1, paint );
        canvas.drawLine ( x2, y1, x2, y2, paint );
        canvas.drawLine ( x1, y2, x2, y2, paint );
        canvas.drawLine ( x1, y1, x1, y2, paint );
        canvas.drawLine ( x1, y1, x1, y2, paint );

        if( letter != ' ' ) {
            if( letter == 'r' ) {
                xDrawable.setBounds ( (int) x1, (int) y1, (int) x2, (int) y2 );
                xDrawable.draw ( canvas );
            }
            if( letter == 's' ) {
                yDrawable.setBounds ( (int) x1, (int) y1, (int) x2, (int) y2 );
                yDrawable.draw ( canvas );
            }
        }
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public void setLetter(char c) {
        this.letter = c;
    }


    public char getLetter() {
        return letter;
    }
}

