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
        String myAddress = destinationInfo.getTheAddress();
        String myExtraComments = destinationInfo.getTheExtraComments();
        String myPostCode = destinationInfo.getThePostCode();
        String myPhoneNumber = destinationInfo.getThePhoneNumber();
        List<MealInfo> myMealTypes = destinationInfo.getTheMealInfos();
        String myMealTypeString = "";
        for(MealInfo aMealInfo : myMealTypes) {
            myMealTypeString= myMealTypeString+ aMealInfo.toString()+", ";
        }




        LinearLayout myListView = (LinearLayout)findViewById(R.id.detail_view);
        TextView myAddressTextView = (TextView) findViewById(R.id.text_view1);
        myAddressTextView.setText(myAddress);
        myAddressTextView.setTextSize(40);

        TextView myExtraCommentsTextView = (TextView) findViewById(R.id.text_view2);
        myExtraCommentsTextView.setText(myExtraComments);
        myExtraCommentsTextView.setTextSize(40);

        TextView myPostCodeTextView = (TextView) findViewById(R.id.text_view3);
        myPostCodeTextView.setText(myPostCode);
        myPostCodeTextView.setTextSize(40);

        TextView myPhoneNumberTextView = (TextView) findViewById(R.id.text_view4);
        myPhoneNumberTextView.setText(myPhoneNumber);
        myPhoneNumberTextView.setTextSize(40);

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