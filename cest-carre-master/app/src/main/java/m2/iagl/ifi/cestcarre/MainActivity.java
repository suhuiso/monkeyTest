package m2.iagl.ifi.cestcarre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.middle);
        TextView textView = findViewById(R.id.score);
        Button button = findViewById(R.id.launcher);

        this.game = new Game(this, linearLayout, textView, button);
    }

    public void startGame(View view){
        this.game.start();
    }
}
