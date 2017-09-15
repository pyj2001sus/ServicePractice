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

// 일단, 1~10000까지 1만초동안 카운트 하는 서비스
//    실제로, 음악을 재생하는 서비스로 변경

public class CountService2 extends Service {

    //    숫자는 1부터 출발
    int count = 1;
    //    숫자를 세는 역할을 전담할 별개의 쓰레드드
    Thread countingThread = null;

    @Override
    public void onCreate() {
        super.onCreate();
        count = 1;
        countingThread = new Thread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        countingThread = new Thread("카운팅쓰레드") {
            @Override
            public void run() {
                while (count < 10000){
                    Log.d("뮤직서비스 카운트", count+"초");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
//                        만약 인터럽트가 들어온다면 반복문 종료
                        break;
                    }
                }
            }
        };

        countingThread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (countingThread != null){
//            반복문을 빠져나오게 하기 위한 인터럽트
            countingThread.interrupt();

//            다른 쓰레드를 저장할 수 있도록 공간을 비움
            countingThread = null;
//            다시 1부터 출발할 수 있게 초기화
            count = 1;

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
