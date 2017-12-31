package com.example.rodneytressler.notificationspractice;

import android.app.IntentService;
import android.content.Context;
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


    //This merely calls the proper method in our ReminderTasks class.
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ReminderTasks.executeAction(intent.getAction(), getApplicationContext());
    }

}
