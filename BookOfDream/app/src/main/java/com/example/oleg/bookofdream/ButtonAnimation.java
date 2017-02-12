package com.example.oleg.bookofdream;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Oleg on 21.12.2016.
 */
public class ButtonAnimation {
    private CountDownTimer timer;
    private Button animatedButton;

    private Drawable drawableOriginal;
    private Drawable drawableArray[];

    public ButtonAnimation(Context context){
        drawableOriginal = context.getResources().getDrawable(R.drawable.ico_book);
        drawableArray = new Drawable[4];
        drawableArray[0] = context.getResources().getDrawable(R.drawable.ico_book_0);
        drawableArray[1] = context.getResources().getDrawable(R.drawable.ico_book_1);
        drawableArray[2] = context.getResources().getDrawable(R.drawable.ico_book_2);
        drawableArray[3] = context.getResources().getDrawable(R.drawable.ico_book_3);
    }

    public void setAnimatedButton(Button view) {
        resetDrawableResource();
        animatedButton = view;
        if (animatedButton == null) {
            stopAnimation();
        } else {
            startAnimation();
        }
    }

    private void resetDrawableResource() {
        if (animatedButton != null) {
            animatedButton.setCompoundDrawablesWithIntrinsicBounds(null, drawableOriginal, null, null);
        }
    }

    public void stopAnimation() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        resetDrawableResource();
    }

    public void startAnimation() {
        if (timer != null) {
            return;
        }
        if (animatedButton != null) {

            timer = new CountDownTimer(Integer.MAX_VALUE, 100) {
                private int animVal = 0;

                @Override
                public void onTick(long millisUntilFinished) {
                    if (animatedButton != null) {
                        animVal = (animVal + 1) % 4;
                        animatedButton.setCompoundDrawablesWithIntrinsicBounds(null, drawableArray[animVal], null, null);
                    }
                }

                @Override
                public void onFinish() {
                    resetDrawableResource();
                }
            };
            timer.start();
        }
    }
}
