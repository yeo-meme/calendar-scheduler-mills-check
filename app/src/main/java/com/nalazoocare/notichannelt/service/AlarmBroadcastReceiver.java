package com.nalazoocare.notichannelt.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nalazoocare.notichannelt.R;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-04-14
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmBroadcastReceiver", "onReceive");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.kim) //알람 아이콘
                .setContentTitle("나라에서 해주는 광견병 무료접종일")  //알람 제목
                .setContentText("지금부터 일주일간 가까운 병원 방문하기") //알람 내용
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); //알람 중요도

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTICATION_ID, builder.build()); //알람 생성
    }
}
