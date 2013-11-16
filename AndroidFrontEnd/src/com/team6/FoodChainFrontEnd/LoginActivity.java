package com.team6.FoodChainFrontEnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        Intent myIntent = getIntent();
        String message = myIntent.getStringExtra(MyActivity.THEDRIVERID);
        /*TextView myTextView = new TextView(this);
        myTextView.setTextSize(40);
        Server.getRoutesFromServer(message);
        myTextView.setText(message);
        setContentView(myTextView);  */
    }
}