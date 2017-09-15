package kr.co.tjeit.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by the on 2017-09-15.
 */

public class MusicService extends Service {

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();

//        음악 재생 도구를 생성. sample.mp3를 불러달라라
       player = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

//        재생기가 곤재하고, 동시에 아직 재생중이 아니라면
        if (player != null && !player.isPlaying()){
//            음악을 재생한다
            player.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

//        재생기가 있을 경우에만 추가질문
        if (player != null){
//            플레이어가 재생중이라면
            if (player.isPlaying()){
//                정지
                player.stop();
            }
//            재생기를 메모리에서 제거거
            player.release();
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
