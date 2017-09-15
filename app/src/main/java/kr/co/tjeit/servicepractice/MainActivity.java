package kr.co.tjeit.servicepractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int currentNum = 0;

    Thread mCountThread = null;

    private android.widget.Button startCountBtn;
    private android.widget.Button endCountBtn;
    private Button getCurrentNumBtn;
    private Button serviceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.serviceBtn = (Button) findViewById(R.id.serviceBtn);
        this.getCurrentNumBtn = (Button) findViewById(R.id.getCurrentNumBtn);
        this.endCountBtn = (Button) findViewById(R.id.endCountBtn);
        this.startCountBtn = (Button) findViewById(R.id.startCountBtn);

        startCountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCountThread == null) {
                    mCountThread = new Thread("Count Thread") {
                        @Override
                        public void run() {

                            while (currentNum < 10000) {

                                Log.i("TJE_COUNT", currentNum + "");

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }

                                currentNum++;
                            }
                        }
                    };
                    mCountThread.start();
                }
            }
        });

        endCountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountThread != null) {
                    mCountThread.interrupt();
                    currentNum = 0;

                    mCountThread = null;
                }
            }
        });

        getCurrentNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "현재숫자 : " + currentNum, Toast.LENGTH_SHORT).show();
            }
        });

        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
