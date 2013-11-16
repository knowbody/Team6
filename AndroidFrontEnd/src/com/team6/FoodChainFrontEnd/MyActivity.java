package com.team6.FoodChainFrontEnd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MyActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    public static final String THEDRIVERID = "DriverID";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void login(View aView) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        EditText myEditText = (EditText) findViewById(R.id.edit_login);
        String myDriverID = myEditText.getText().toString();
        myIntent.putExtra(THEDRIVERID, myDriverID);
        startActivity(myIntent);
    }
}
