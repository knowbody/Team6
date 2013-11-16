package com.team6.FoodChainFrontEnd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        Intent myIntent = getIntent();
        String myDriverID = myIntent.getStringExtra(MyActivity.THEDRIVERID);
        List<DestinationInfo> myRoutesFromServer = Server.getRoutesFromServer(myDriverID);
        PersistentStore.myDestinationInfos = myRoutesFromServer;
        LinearLayout myScrollView = (LinearLayout) findViewById(R.id.scroll_layout);
        final Context thisContext = this;
        int i = 0;
        for(DestinationInfo destinationInfo : myRoutesFromServer) {
            TextView myTextView = new TextView(this);
            myTextView.setTextSize(40);
            myTextView.setText(destinationInfo.getThePostCode());
            final int index = i;
            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(thisContext, LocationDetailsActivity.class);
                    PersistentStore.index = index;
                    startActivity(myIntent);
                }
            });
            myScrollView.addView(myTextView);
            i++;
        }
    }
}