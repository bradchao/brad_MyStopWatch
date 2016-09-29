package brad.tw.mystopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;  // false
    private Button btnLeft, btnRight;
    private Timer timer;
    private int counter;    // 0
    private MyTask myTask;
    private TextView mytimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();

        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        mytimer = (TextView)findViewById(R.id.mytimer);

        isRunning = false;
        counter = 0;
    }

    @Override
    public void finish() {
        timer.purge();
        timer.cancel();
        timer = null;
        super.finish();
    }

    public void doLeft(View v){

    }
    public void doRight(View v){
        isRunning = !isRunning;

        btnRight.setText(isRunning?"STOP":"START");
        btnLeft.setText(isRunning?"LAP":"RESET");

        if (isRunning){
            doStart();
        }else{
            doStop();
        }
    }
    private void doStart(){
        myTask = new MyTask();
        timer.schedule(myTask, 10, 10);
    }
    private void doStop(){
        myTask.cancel();
    }
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            counter++;
        }
    }


}
