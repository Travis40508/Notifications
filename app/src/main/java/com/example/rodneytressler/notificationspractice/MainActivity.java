package com.example.rodneytressler.notificationspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotification = findViewById(R.id.button_notification);
        listenForButtonClick();

        JobReminderUtil.scheduleClickReminder(this);
    }

    //Calls the building of our notification, using the helper methods to determine which activity to open onClick and how to get the large icon.
    private void listenForButtonClick() {
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationsUtil.remindUserBecauseClick(MainActivity.this);
            }
        });
    }
}
