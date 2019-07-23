package com.ti.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

// 本地Service这是最常用的
public class LocalService extends Service {
    public LocalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("本地Service#","onCreate创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("本地Service#","onStartCommand开始服务");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("本地Service#","onDestroy销毁服务");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("本地Service#","onBind绑定服务");

        return null;
    }


}
