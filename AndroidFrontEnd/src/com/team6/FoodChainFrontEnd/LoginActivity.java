package com.team6.FoodChainFrontEnd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

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
        List<DestinationInfo> myRoutesFromServer = Server.getRoutesFromServer2(myDriverID);
        PersistentStore.myDestinationInfos = myRoutesFromServer;
        LinearLayout myScrollView = (LinearLayout) findViewById(R.id.scroll_layout);
        final Context thisContext = this;
        int i = 0;
        for(DestinationInfo destinationInfo : myRoutesFromServer) {
            View myView = new View(this);
//            myView.setLayoutParams(new LinearLayout.LayoutParams(100,100));
            myView.setBackgroundColor(android.R.color.darker_gray);
            myView.setVisibility(View.VISIBLE);
//                    android:layout_width="fill_parent"
//            android:layout_height="1dp"
//            android:background="@android:color/darker_gray"
//            android:visibility="visible"
            TextView myTextView = new TextView(this);
            myTextView.setTextSize(40);
            myTextView.setTextColor(Color.parseColor(getString(R.color.sysGreen)));
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
            myScrollView.addView(myView);
            myScrollView.addView(myTextView);
            i++;
        }
    }
}