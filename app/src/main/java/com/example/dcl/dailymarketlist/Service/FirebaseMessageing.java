package com.example.dcl.dailymarketlist.Service;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;


import com.example.dcl.dailymarketlist.Profile;
import com.example.dcl.dailymarketlist.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessageing extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification().getTitle().equals("New  Request")){
            showNotification(remoteMessage.getNotification().getBody());
        }


    }

    private void showNotification(String body) {
        PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),0,new Intent(),PendingIntent.FLAG_ONE_SHOT);

        android.app.Notification.Builder builder=new android.app.Notification.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(android.app.Notification.DEFAULT_LIGHTS| android.app.Notification.DEFAULT_SOUND)
                .setContentTitle("New Bazar List Request")
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        NotificationManager notificationManager=(NotificationManager)getBaseContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


    }
    /* @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       if (remoteMessage.getNotification().getTitle().equals("New Bazar Request")){
           showNotification(remoteMessage.getNotification().getBody());
       }
    }

    private void showNotification(String body) {
        PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),0,new Intent(),PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder=new Notification.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND)
                .setContentTitle("New Bazar List Request")
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager notificationManager=(NotificationManager)getBaseContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


    }*/
}
