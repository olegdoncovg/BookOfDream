package com.example.oleg.bookofdream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimatedView extends View {
    private static final int STAR_COUNT = 7;
    private static int[][] STAR_RES = {
            {R.drawable.star_1_0, R.drawable.star_1_1, R.drawable.star_1_2, R.drawable.star_1_3, R.drawable.star_1_4},
            {R.drawable.star_2_0, R.drawable.star_2_1, R.drawable.star_2_2, R.drawable.star_2_3, R.drawable.star_2_4},
            {R.drawable.star_3_0, R.drawable.star_3_1, R.drawable.star_3_2, R.drawable.star_3_3, R.drawable.star_3_4},
            {R.drawable.star_4_0, R.drawable.star_4_1, R.drawable.star_4_2, R.drawable.star_4_3, R.drawable.star_4_4}
    };
    private Bitmap[][] starBitmap;
    private Random random = new Random();

    private List<AnimatedStar> animations = new ArrayList<>();

    public AnimatedView(Context context) {
        super(context);
    }

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        starBitmap = new Bitmap[STAR_RES.length][];
        for (int i = 0; i < STAR_RES.length; i++) {
            starBitmap[i] = new Bitmap[STAR_RES[i].length];
            for (int j = 0; j < STAR_RES[i].length; j++) {
                starBitmap[i][j] = BitmapFactory.decodeResource(getResources(), STAR_RES[i][j]);
            }
        }
        for (int i = 0; i < STAR_COUNT; i++) {
            animations.add(createAnimatedStar());
        }
    }

    protected void destroy() {
        if(starBitmap!=null) {
            for (int i = 0; i < STAR_RES.length; i++) {
                for (int j = 0; j < STAR_RES[i].length; j++) {
                    starBitmap[i][j].recycle();
                }
            }
            starBitmap = null;
        }
    }

    private AnimatedStar createAnimatedStar() {
        int type = random.nextInt(STAR_RES.length);
        int x = random.nextInt(getWidth()+1);
        int y = random.nextInt(getHeight()+1);
        return new AnimatedStar(
                Math.abs(type),
                Math.abs(x),
                Math.abs(y));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < animations.size(); i++) {
            AnimatedStar anim = animations.get(i);
            Bitmap bt = starBitmap[anim.getStarType()][anim.getAnimCurrent()];
            canvas.drawBitmap(bt, anim.getX(), anim.getY(), null);
            if (anim.getAnimCurrent() == 0){
                animations.remove(i);
                animations.add(i, createAnimatedStar());
            }
        }
    }

    class AnimatedStar {
        private int starType;
        private int animCurrent;
        private int x, y;

        public AnimatedStar(int starType, int x, int y) {
            this.starType = starType;
            animCurrent = -1;
            this.x = x;
            this.y = y;
        }

//        public int getResId() {
//            animCurrent = (++animCurrent) % STAR_RES[starType].length;
//            return STAR_RES[starType][animCurrent];
//        }

        public int getAnimCurrent() {
            animCurrent = (++animCurrent) % STAR_RES[starType].length;
            return animCurrent;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getStarType() {
            return starType;
        }
    }
}
