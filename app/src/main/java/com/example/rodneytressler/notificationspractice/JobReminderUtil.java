package com.example.rodneytressler.notificationspractice;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by rodneytressler on 12/30/17.
 */

//This builds the job. setting how often the job should execute (every 60 seconds, and if it should repeat (yes)
public class JobReminderUtil {
    private static final int REMINDER_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(1));
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;
    private static final String REMINDER_JOB_TAG = "click-reminder-tag";

    private static boolean sInitialized;

    synchronized public static void scheduleClickReminder(final Context context) {
        if(sInitialized) {
            return;
        }

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(driver);

        Job constraintReminderJob = jobDispatcher.newJobBuilder()
                .setService(JobUtil.class)
                .setTag(REMINDER_JOB_TAG)
                //This is how you'd make the job only fire under certain circumstances
//                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, REMINDER_INTERVAL_SECONDS))
                .setReplaceCurrent(true)
                .build();

        jobDispatcher.schedule(constraintReminderJob);
        sInitialized = true;
    }
}
