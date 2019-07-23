package com.ti.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CommunicationService.CommunicationBinder myBinder;


    // 创建ServiceConnection的匿名类
    private ServiceConnection connection = new ServiceConnection() {

        // 重写onServiceConnected()方法和onServiceDisconnected()方法
        // 在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity与Service解除关联的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 实例化Service的内部类myBinder
            // 通过向下转型得到了MyBinder的实例
            myBinder = (CommunicationService.CommunicationBinder) service;
            // 在Activity调用Service类的方法
            myBinder.service_connect_Activity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_local_service_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLocalServiceIntent = new Intent(MainActivity.this,LocalService.class);
                startService(startLocalServiceIntent);

            }
        });
        findViewById(R.id.btn_local_service_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopLocalServiceIntent = new Intent(MainActivity.this,LocalService.class);
                stopService(stopLocalServiceIntent);

            }
        });




        findViewById(R.id.bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 构建绑定服务的Intent对象
                Intent bindServiceIntent = new Intent(MainActivity.this,CommunicationService.class);
                // 第一个参数:Intent对象
                // 第二个参数:上面创建的ServiceConnection实例
                // 第三个参数:标志位
                // 这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(bindServiceIntent,connection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用unbindService()解绑服务
                // 参数是上面创建的Serviceconnection实例
                unbindService(connection);
            }
        });

        findViewById(R.id.start_front_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startFrontServiceIntent = new Intent(MainActivity.this,FrontService.class);
                startService(startFrontServiceIntent);
            }
        });
        findViewById(R.id.stop_front_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopFrontServiceIntent = new Intent(MainActivity.this,FrontService.class);
                stopService(stopFrontServiceIntent);
            }
        });

    }
}
