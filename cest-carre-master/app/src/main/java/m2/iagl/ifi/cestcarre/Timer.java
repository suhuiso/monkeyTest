package m2.iagl.ifi.cestcarre;

import java.util.Observable;
import java.util.TimerTask;

/**
 * Created by valentin on 11/11/17.
 */

public class Timer extends Observable {

    private int duration, remainingTime;
    private java.util.Timer timer;
    private Game game;

    public Timer(int duration, Game game) {
        this.duration = duration;
        this.remainingTime = this.duration;
        this.timer = new java.util.Timer();
        this.game = game;
    }

    public void start(){
        this.remainingTime = this.duration;
        this.timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (--remainingTime == 0){
                    timer.cancel();
                }
                notifyObservers();
            }
        }, 0, 1000);
    }

    public int getRemainingTime(){
        return this.remainingTime;
    }

    @Override
    public void notifyObservers() {
        this.game.update(this, this.remainingTime == 0);
    }
}
