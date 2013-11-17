package com.team6.FoodChainFrontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.List;

public class LocationDetailsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);
        DestinationInfo destinationInfo = PersistentStore.myDestinationInfos.get(PersistentStore.index);
        String myExtraComments = destinationInfo.getTheExtraComments();
        String myPostCode = destinationInfo.getThePostCode();
        List<String> myMealTypes = destinationInfo.getTheMealInfos();
        String myMealTypeString = "";
        for(String aMealInfo : myMealTypes) {
            myMealTypeString= myMealTypeString+ aMealInfo.toString()+", ";
        }

        myMealTypeString = (myMealTypeString.subSequence(0,myMealTypeString.length()-2)).toString();

        TextView myExtraCommentsTextView = (TextView) findViewById(R.id.text_view2);
        myExtraCommentsTextView.setText(myExtraComments);
        myExtraCommentsTextView.setTextSize(40);

        TextView myPostCodeTextView = (TextView) findViewById(R.id.text_view3);
        myPostCodeTextView.setText(myPostCode);
        myPostCodeTextView.setTextSize(40);

        TextView myMealTypeStringTextView = (TextView) findViewById(R.id.text_view5);
        myMealTypeStringTextView.setText(myMealTypeString);
        myMealTypeStringTextView.setTextSize(40);

//
//        myListView.addView(myAddressTextView);
//        myListView.addView(myExtraCommentsTextView);
//        myListView.addView(myPostCodeTextView);
//        myListView.addView(myPhoneNumberTextView);
//        myListView.addView(myMealTypeStringTextView);
    }
}