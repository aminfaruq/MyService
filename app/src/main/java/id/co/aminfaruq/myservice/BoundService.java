package id.co.aminfaruq.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {

    final String TAG = BoundService.class.getSimpleName();
    MyBinder mBinder = new MyBinder();
    final long startTime = System.currentTimeMillis();


    CountDownTimer mTimer = new CountDownTimer(100000, 100) {
        @Override
        public void onTick(long millisUntilFinished) {
            long elapsedTimer = System.currentTimeMillis() - startTime;
            Log.d(TAG, "onTick: " + elapsedTimer);

        }

        @Override
        public void onFinish() {

        }
    };

    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        mTimer.start();
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        mTimer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
