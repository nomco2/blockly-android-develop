package com.google.blockly.android.demo.Coding_app_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.blockly.android.demo.R;

/**
 * Created by KimFamily on 2018-02-04.
 */

public class Starting_Logo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_logo);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 5000);


    }

    private class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), DB_select.class));
            Starting_Logo.this.finish();
        }
    }
}
