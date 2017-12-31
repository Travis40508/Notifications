package com.example.rodneytressler.notificationspractice;

import android.content.Context;

/**
 * Created by rodneytressler on 12/31/17.
 */

public class ReminderTasks {

    //This class' job is to take actions and determine which util methods to call afterwards.

    public static final String ACTION_NO_CLICKED = "no-clicked";
    public static final String ACTION_CONFIRM_CLICKED = "yes-clicked";
    public static final String ACTION_REMIND_OPEN_APP = "remind-open-app";

    public static void executeAction(String action, Context context) {
        if(action.equals(ACTION_NO_CLICKED)) {
            NotificationsUtil.clearAllNotifications(context);
        } else if(action.equals(ACTION_CONFIRM_CLICKED)) {
            NotificationsUtil.logSuccess();
            NotificationsUtil.clearAllNotifications(context);
        } else if (action.equals(ACTION_REMIND_OPEN_APP)) {
            NotificationsUtil.remindUserBecauseClick(context);
        }
    }
}
