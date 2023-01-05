package com.example.broadcastdiscovery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;
    private MyBroadcastReceiverBatteryLow myBroadcastReceiverBatteryLow;

    Button sendFakeEventButton;
    static TextView eventReceivedDisplayEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();
        myBroadcastReceiverBatteryLow = new MyBroadcastReceiverBatteryLow();
        this.registerReceiver(myBroadcastReceiver, new IntentFilter("FAKE_EVENT_INFO"));
        //this.registerReceiver(myBroadcastReceiverBatteryLow, new IntentFilter("Intent.ACTION_POWER_CONNECTED"));
        this.registerReceiver(myBroadcastReceiverBatteryLow, new IntentFilter("Intent.ACTION_BATTERY_LOW"));

        sendFakeEventButton = (Button) findViewById(R.id.sendFakeEventButton);
        eventReceivedDisplayEditText = (TextView) findViewById(R.id.eventReceivedDisplayTextView);

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

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(myBroadcastReceiver, new IntentFilter("FAKE_EVENT_INFO"));
        this.registerReceiver(myBroadcastReceiverBatteryLow, new IntentFilter("Intent.ACTION_BATTERY_LOW"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(myBroadcastReceiver);
        this.unregisterReceiver(myBroadcastReceiverBatteryLow);
    }

    static void handleBatteryLowEvent(){
        eventReceivedDisplayEditText.setText("Battery low event received");
    }

    public static class MyBroadcastReceiverBatteryLow extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("BR_TAG"," Battery low event received ");
            handleBatteryLowEvent();
        }
    }

}