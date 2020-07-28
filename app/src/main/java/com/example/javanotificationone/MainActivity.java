package com.example.javanotificationone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


// https://viblo.asia/p/tao-mot-notification-trong-android-YWOZr0Vr5Q0


public class MainActivity extends AppCompatActivity {

    private static final String EDMT_CHANNEL_ID = "edmt.dev.androidnotificationchannel.EDMTDEV";
    private static final String EDMT_CHANNEL_NAME = "EDMTDEV Channel";
    Button btnShow, btnCancel;
    NotificationCompat.Builder mBuilder;
    NotificationManager manager;
    int mNotificationId = 001;
    String strContent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strContent = "Welcome to DevPro Viet Nam";

        btnShow = (Button) findViewById(R.id.btnShowNotification);
        btnCancel = (Button) findViewById(R.id.btnCancelNotification);

        createChannels();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PendingIntent replyPendingIntent = null;

                mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(),EDMT_CHANNEL_ID)
                                .setSmallIcon(R.drawable.orange)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.orange))
                                .setContentTitle("My notification")
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .addAction(R.drawable.orange, "orange",replyPendingIntent)
                                .setContentText(strContent);

                // Sets id cho notification
                // Gets an instance of the NotificationManager service


                Intent resultIntent = new Intent(getApplicationContext(), NotificationReceiverActivity.class);
                resultIntent.putExtra("content", strContent);

                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                getApplicationContext(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                // Set content intent;
                mBuilder.setContentIntent(resultPendingIntent);

                manager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                manager.notify(mNotificationId, mBuilder.build());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(mNotificationId);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        //IMPORTANCE_DEFAULT = show everywhere , make noise , but don't visually intrude
        //IMPORTANCE_HIGH : show everywhere , make noise and peeks
        //IMPORTANCE_LOW : show everywhere , but isn't intrusive
        //IMPORTANCE_MIN: only show in the shade , below the fold
        //IMPORTANCE_NONE : a notification with no importance , don't show in the shade
        NotificationChannel edmtChannel = new NotificationChannel(EDMT_CHANNEL_ID,EDMT_CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        edmtChannel.enableLights(true);
        edmtChannel.enableVibration(true);
        edmtChannel.setLightColor(Color.GREEN);
        edmtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(edmtChannel);
    }
    public NotificationManager getManager() {
        if(manager == null)
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }


}
