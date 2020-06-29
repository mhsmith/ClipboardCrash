package com.chaquo.clipboardcrash;

import android.content.*;
import android.os.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private TextView tvCaption;
    private int count = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCaption = findViewById(R.id.tvCaption);
        schedule();
    }

    private void schedule() {
        // Use a random interval in case different machines crash with different intervals.
        long time = SystemClock.uptimeMillis() + new Random().nextInt(200);
        handler.postAtTime(new Runnable() {
            @Override public void run() {
                String text = String.valueOf(count);
                tvCaption.setText(text);
                ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setText(text);
                count++;
                schedule();
            }
        }, time);
    }
}