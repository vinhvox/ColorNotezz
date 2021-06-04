package com.example.colornote.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Receiver",Toast.LENGTH_SHORT).show();
        String title = intent.getExtras().getString("title");
        String mess = intent.getExtras().getString("content");
        Intent intent1 = new Intent(context, AlarmNotification.class);
        intent1.putExtra("title", title);
        intent1.putExtra("content", mess);
        context.startService(intent1);
    }
}
