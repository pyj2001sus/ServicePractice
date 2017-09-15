package kr.co.tjeit.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by the on 2017-09-15.
 */

// StartedService 제작 연습
public class CountService extends Service{

    int currentNum = 0;
    Thread mCountingThread = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("서비스실행알림", "서비스 실행됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.i("서비스실행알림", "서비스 명령 수행 시작");

        if (mCountingThread == null){
            mCountingThread = new Thread(){
                @Override
                public void run() {
                    while (true) {
                        Log.i("카운팅 in Service", currentNum+"");

                        currentNum++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            };
        }



        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("서비스실행알림", "서비스 제거");

        if (mCountingThread !=null){
            mCountingThread.interrupt();
            mCountingThread = null;
            currentNum = 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
