package com.ti.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

// 本地Service这是最常用的
public class FrontService extends Service {
    public FrontService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String id = "channel_01";
        String name="我是渠道名字";
        // 添加下列代码将后台Service变成前台Service
        // 构建点击通知后打开MainActivity的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //获取系统通知服务
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this,id)
                    .setContentTitle("前台服务通知的标题")// 设置通知的标题
                    .setContentText("前台服务通知的内容")// 设置通知的内容
                    .setSmallIcon(R.mipmap.ic_launcher)// 设置通知的图标
                    .setContentIntent(pendingIntent)// 设置点击通知后的操作
                    .build();
        } else {
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle("前台服务通知的标题")
                    .setContentText("前台服务通知的内容")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setChannelId(id)
                    .setContentIntent(pendingIntent) //设置pendingIntent,点击通知时就会用到
                    .build();
        }

        startForeground(1, notification);// 让Service变成前台Service,并在系统的状态栏显示出来

        Log.d("前台Service#","onCreate创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("前台Service#","onStartCommand开始服务");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("前台Service#","onDestroy销毁服务");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("前台Service#","onBind绑定服务");

        return null;
    }


}
