package brad.tw.mystopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;  // false
    private Button btnLeft, btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        isRunning = false;
    }

    public void doLeft(View v){

    }
    public void doRight(View v){
        isRunning = !isRunning;

        btnRight.setText(isRunning?"STOP":"START");
        btnLeft.setText(isRunning?"LAP":"RESET");

    }

}
