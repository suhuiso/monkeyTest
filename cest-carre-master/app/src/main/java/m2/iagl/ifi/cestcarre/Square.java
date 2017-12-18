package m2.iagl.ifi.cestcarre;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by valentin on 11/11/17.
 */

public class Square extends View {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private Game game;
    private Random random;
    private Paint paint;
    private Rect rect;

    public Square(Game game){
        super(game.getContext());
        this.game = game;
        this.paint = new Paint();
        this.random = new Random();
        this.rect = new Rect();
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.rgb(255, 0, 0));
        int randomX = this.random.nextInt(canvas.getWidth() - WIDTH),
            randomY = this.random.nextInt(canvas.getHeight() - HEIGHT);
        this.rect.set(randomX, randomY, randomX + WIDTH, randomY + HEIGHT);
        canvas.drawRect(this.rect, this.paint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && checkPosition(event)){
            this.game.increaseScore(this);
        }
        return true;
    }

    private boolean checkPosition(MotionEvent event){
        return this.rect.contains((int) event.getX(), (int) event.getY());
    }
}
