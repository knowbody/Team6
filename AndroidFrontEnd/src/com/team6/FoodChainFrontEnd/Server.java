package com.team6.FoodChainFrontEnd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static Collection<DestinationInfo> getRoutesFromServer(String aDriverID) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        ArrayList<MealInfo> myMealInfos = new ArrayList<MealInfo>();
        myMealInfos.add(MealInfo.AFRICAN);
        DestinationInfo myDestinationInfo = new DestinationInfo("address", "postcode", "extra comments", "phone number", myMealInfos);
        ArrayList<DestinationInfo> myDestinationInfos = new ArrayList<DestinationInfo>();
        myDestinationInfos.add(myDestinationInfo);
        myDestinationInfos.add(myDestinationInfo) ;
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
