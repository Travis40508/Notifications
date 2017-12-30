package com.example.rodneytressler.notificationspractice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by rodneytressler on 12/30/17.
 */

public class Service extends IntentService {




    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Service(String name) {
        super(name);
    }
    public Service() {
        super("Service");
    }

    public static final String ACTION_NO_CLICKED = "no-clicked";
    public static final String ACTION_CONFIRM_CLICKED = "yes-clicked";

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent.getAction().equals(ACTION_NO_CLICKED)) {
            NotificationsUtil.clearAllNotifications(getApplicationContext());
        } else if(intent.getAction().equals(ACTION_CONFIRM_CLICKED)) {
            NotificationsUtil.logSuccess();
            NotificationsUtil.clearAllNotifications(getApplicationContext());
        }
    }
}
