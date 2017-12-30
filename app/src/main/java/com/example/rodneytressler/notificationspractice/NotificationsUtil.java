package com.example.rodneytressler.notificationspractice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.content.ContextCompat;

/**
 * Created by rodneytressler on 12/30/17.
 */

public class NotificationsUtil {

    //This sets all of our ids for our notification, our pending intent to open up the activity onClick, and the channel id for Oreo.

    private static final int CLICK_NOTIFICATION_ID = 1200;
    private static final int CLICK_PENDING_INTENT_ID = 3417;
    private static final String CLICK_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";


    //Chooses which activity to open up when our notification is clicked
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                CLICK_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    //Returns our large icon for our notification
    private static Bitmap largeIcon (Context context) {
        Resources res = context.getResources();

        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background);
        return largeIcon;
    }

    //Builds our actual notification, making sure to add a channel if the version is Oreo or greater
    public static void remindUserBecauseClick(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    CLICK_REMINDER_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CLICK_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("Notification Test")
                .setContentText("You did it, Trav!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is so cool, this is a really large string that I'm typing"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(CLICK_NOTIFICATION_ID, notificationBuilder.build());
    }
}
