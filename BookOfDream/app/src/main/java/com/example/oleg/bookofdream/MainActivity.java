package com.example.oleg.bookofdream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Map<Integer, Button> buttonsMap = new HashMap<>();
    ButtonAnimation buttonAnimation;
    AnimationTimer animationTimer = new AnimationTimer();
    AnimatedView starView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();

        DBSource dbSource = new DBSource(this);
        fillMenuist(dbSource.getBookNamesList(), (LinearLayout) findViewById(R.id.menu_base_list));
    }

    private void fillMenuist(List<String> list, LinearLayout verticalLinear) {
        for (int i = 0, line = 0; i < list.size(); line++) {
            if (line % 2 == 0 && list.size() - i > 1) {
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.menu_lite_2, null);
                verticalLinear.addView(linearLayout);
                buttonsMap.put(i++, ((Button)linearLayout.findViewById(R.id.button_1)));
                buttonsMap.put(i++, ((Button)linearLayout.findViewById(R.id.button_2)));
            } else {
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.menu_lite_1, null);
                verticalLinear.addView(linearLayout);

                buttonsMap.put(i++, ((Button)linearLayout.findViewById(R.id.button_1)));
            }
        }
        for(int i = 0; i< buttonsMap.size(); i++){
            buttonsMap.get(i).setText(list.get(i));
            buttonsMap.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        buttonAnimation.setAnimatedButton((Button)view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(buttonAnimation==null){
            buttonAnimation = new ButtonAnimation(this);
        }
        buttonAnimation.startAnimation();
        animationTimer.start();

        starView = (AnimatedView) findViewById(R.id.menu_animated_bg);
        starView.init();
        animationTimer.addAnimationListener(new AnimationTimer.UpdateListener() {
            @Override
            void onAnimationUpdate(long currentTicket, long currentTime) {
                starView.invalidate();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        buttonAnimation.stopAnimation();
        animationTimer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        buttonAnimation.stopAnimation();
        animationTimer.stop();
        animationTimer.removeAllAnimationListeners();
        starView.destroy();
    }
}