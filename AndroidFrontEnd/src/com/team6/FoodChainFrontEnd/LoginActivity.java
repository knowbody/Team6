package com.team6.FoodChainFrontEnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Map;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        Intent myIntent = getIntent();
        String myDriverID = myIntent.getStringExtra(MyActivity.THEDRIVERID);
        Collection<DestinationInfo> myRoutesFromServer = Server.getRoutesFromServer(myDriverID);
        DataStore.store(myRoutesFromServer, this);
        LinearLayout myScrollView = (LinearLayout) findViewById(R.id.scroll_layout);
        for(DestinationInfo destinationInfo : myRoutesFromServer) {
            TextView myTextView = new TextView(this);
            myTextView.setTextSize(40);
            myTextView.setText(destinationInfo.getThePostCode());
            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent myIntent = new Intent(this, LocationDetailsActivity.class);
//                    myIntent.putExtra()
//                    startActivity(myIntent);
                }
            });
            myScrollView.addView(myTextView);

        }
    }
}