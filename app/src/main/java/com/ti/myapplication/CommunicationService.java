package com.ti.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

// 本地Service这是最常用的
public class CommunicationService extends Service {

    private CommunicationBinder mBinder = new CommunicationBinder("可通信Service");
    public CommunicationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("可通信Service#","onCreate创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("可通信Service#","onStartCommand开始服务");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("可通信Service#","onDestroy销毁服务");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("可通信Service#","onBind绑定服务");

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("可通信Service#","onUnbind解绑服务");
        return super.onUnbind(intent);
    }

    //新建一个子类继承自Binder类
    class CommunicationBinder extends Binder {

        private String name;
        public CommunicationBinder(String name){
            this.name = name;
        }
        public void service_connect_Activity() {

            Log.d("","Service关联了Activity,并在Activity执行了Service的方法");


        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
