package com.example.broadcastdiscovery;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;

    Button sendFakeEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();
        this.registerReceiver(myBroadcastReceiver, new IntentFilter("FAKE_EVENT_INFO"));

        sendFakeEventButton = (Button) findViewById(R.id.sendFakeEventButton);

        sendFakeEventButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent eventIntent = new Intent("FAKE_EVENT_INFO");
                        Log.i("BR_TAG"," Event Sent");
                        sendBroadcast(eventIntent);
                    }
                }
        );

    }
}