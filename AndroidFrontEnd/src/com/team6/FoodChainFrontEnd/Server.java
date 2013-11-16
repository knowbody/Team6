package com.team6.FoodChainFrontEnd;

import java.util.*;

public class Server {
    public static List<DestinationInfo> getRoutesFromServer(String aDriverID) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        ArrayList<MealInfo> myMealInfos = new ArrayList<MealInfo>();
        myMealInfos.add(MealInfo.AFRICAN);
        DestinationInfo myDestinationInfo = new DestinationInfo("address", "postcode", "extra comments", "phone number", myMealInfos);
        myMealInfos.add(MealInfo.DIABETIC);
        myMealInfos.add(MealInfo.GLUTEN_FREE);
        myMealInfos.add(MealInfo.VEGETARIAN);
        DestinationInfo myDestinationInfo2 = new DestinationInfo("address2", "postcode", "extra comments", "phone number", myMealInfos);
        ArrayList<DestinationInfo> myDestinationInfos = new ArrayList<DestinationInfo>();
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo2) ;
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo)  ;
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo) ;
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo)  ;
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo) ;
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo)  ;
        return myDestinationInfos;
    }
}
