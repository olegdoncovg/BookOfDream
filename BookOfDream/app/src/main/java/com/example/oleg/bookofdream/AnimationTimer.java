package com.example.oleg.bookofdream;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by Oleg on 24.12.2016.
 */

public class AnimationTimer {
    private long ticket = 0;
    private CountDownTimer timer;
    private List<UpdateListener> listeners = new ArrayList<>();
    private void init() {
        timer = new CountDownTimer(Integer.MAX_VALUE, 100){

            @Override
            public void onTick(long l) {
                long currentTime = System.currentTimeMillis();
                long currentTicket = ticket++;
                for(UpdateListener listener : listeners){
                    listener.onAnimationUpdate(currentTicket, currentTime);
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }
    public void start() {
        init();
        timer.start();
    }
    public void stop() {
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }
    public void addAnimationListener(UpdateListener updateListener){
        listeners.add(updateListener);
    }

    public void removeAnimationListener(UpdateListener updateListener){
        listeners.add(updateListener);
    }

    public void removeAllAnimationListeners(){
        listeners.clear();
    }

    static abstract class UpdateListener {
        abstract void onAnimationUpdate(long currentTicket, long currentTime);
    }
}
