package com.example.dcl.dailymarketlist.Service;




import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.format.DateUtils;
import android.widget.RemoteViews;


import com.example.dcl.dailymarketlist.Home;
import com.example.dcl.dailymarketlist.Profile;
import com.example.dcl.dailymarketlist.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessageing extends FirebaseMessagingService {

    RemoteViews expandedView;
    RemoteViews collapsedView;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

         expandedView = new RemoteViews(getPackageName(), R.layout.notification_layout_design);
         collapsedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        expandedView.setImageViewResource(R.id.big_icon, R.drawable.android);
        expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

        collapsedView.setImageViewResource(R.id.big_icon, R.drawable.ic_audiotrack_dark);
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

        Intent leftIntent = new Intent(this, Home.class);
        leftIntent.setAction("left");
        expandedView.setOnClickPendingIntent(R.id.left_button, PendingIntent.getService(this, 0, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT));

       // expandedView.setTextViewText(R.id.notification_message, mEditText.getText());


        if (remoteMessage.getNotification().getTitle().equals("New  Request")){
            showNotification(remoteMessage.getNotification().getBody());
        }


    }

    @SuppressLint("NewApi")
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
              //  .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(new Notification.DecoratedCustomViewStyle())
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
