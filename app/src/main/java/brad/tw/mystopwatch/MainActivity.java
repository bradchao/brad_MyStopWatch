package brad.tw.mystopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;  // false
    private Button btnLeft, btnRight;
    private Timer timer;
    private int counter;    // 0
    private MyTask myTask;
    private TextView mytimer;
    private UIHandler handler;

    private ListView lapList;
    private SimpleAdapter adapter;
    private String[] from = {"laptime"};
    private int[] to = {R.id.item_laptime};
    private LinkedList<HashMap<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        handler = new UIHandler();

        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        mytimer = (TextView)findViewById(R.id.mytimer);

        lapList = (ListView)findViewById(R.id.lapList);
        initListView();

        isRunning = false;
        counter = 0;
    }

    private void initListView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this,data,R.layout.layout_item,
                from,to);
        lapList.setAdapter(adapter);
    }

    @Override
    public void finish() {
        timer.purge();
        timer.cancel();
        timer = null;
        super.finish();
    }

    public void doLeft(View v){
        if (isRunning){
            doLap();
        }else{
            doReset();
        }
    }
    private void doLap(){
        HashMap<String,String> lap = new HashMap<>();
        lap.put(from[0], "" + counter);
        data.add(0, lap);
        adapter.notifyDataSetChanged();
    }
    private void doReset(){
        counter = 0;
        handler.sendEmptyMessage(0);

        data.clear();
        adapter.notifyDataSetChanged();
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
            handler.sendEmptyMessage(0);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mytimer.setText("" + counter);
        }
    }

}
