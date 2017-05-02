package com.example.wlwlxgg.mymusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.utils.StatusBar;

/**
 * Created by wlwlxgg on 2017/4/17.
 */

public class SpalshActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        StatusBar.compat(this, Color.BLACK);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SpalshActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

        super.onCreate(savedInstanceState);
    }
}
