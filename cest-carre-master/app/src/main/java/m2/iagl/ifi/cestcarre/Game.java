package m2.iagl.ifi.cestcarre;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by valentin on 11/11/17.
 */

public class Game implements Observer{

    private int score;
    private Context context;
    private ViewGroup playground;
    private TextView scoreTextField;
    private Button button;
    private Timer timer;
    private Handler handler;
    private boolean gameIsRunning;


    public Game(Context context, ViewGroup playground, TextView scoreTextField, Button button){
        this.context = context;
        this.playground = playground;
        this.scoreTextField = scoreTextField;
        this.button = button;
        this.timer = new Timer(10, this);
        this.handler = new Handler();
        this.gameIsRunning = false;
    }

    public void start(){
        this.gameIsRunning = true;
        this.button.setEnabled(false);
        this.resetGame();
        this.timer.start();

        handler.postDelayed(new Runnable(){
            public void run(){
                generateSquares();
                handler.postDelayed(this, 1000);
            }
        }, 0);
    }

    public void stop(){
        this.gameIsRunning = false;
        Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playground.removeAllViews();
                button.setEnabled(true);
                button.setText(R.string.start_button);
            }
        });
        this.handler.removeCallbacksAndMessages(null);
    }

    private void resetGame(){
        this.score = 0;
        this.scoreTextField.setText(R.string.score_initial);
    }

    public void increaseScore(Square square){
        this.score++;
        this.scoreTextField.setText("Score : " + this.score);
        this.playground.removeView(square);
        this.generateSquares();
    }

    private void generateSquares(){
        if(this.gameIsRunning){
            playground.addView(new Square(this));
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        boolean isGameFinished = (boolean) o;
        this.button.setText("Temps restant :" + this.timer.getRemainingTime());
        if(isGameFinished) {
            this.stop();
        }
    }

    public Context getContext() {
        return context;
    }
}
