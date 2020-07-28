package com.example.javanotificationone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationReceiverActivity extends AppCompatActivity {

    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        txtContent = (TextView) findViewById(R.id.txtContent);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("content");
        txtContent.setText(msg);

    }
}
