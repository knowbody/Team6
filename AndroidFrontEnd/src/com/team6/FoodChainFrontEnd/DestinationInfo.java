package com.team6.FoodChainFrontEnd;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DestinationInfo {
    private final String theAddress;
    private final String thePostCode;
    private final String theExtraComments;
    private final String thePhoneNumber;
    private final List<MealInfo> theMealInfos;

    public DestinationInfo(String aAddress, String thePostCode, String theExtraComments, String thePhoneNumber, List<MealInfo> theMealInfos) {
        theAddress = aAddress;
        this.thePostCode = thePostCode;
        this.theExtraComments = theExtraComments;
        this.thePhoneNumber = thePhoneNumber;
        this.theMealInfos = theMealInfos;
    }

    public String getTheAddress() {
        return theAddress;
    }

    public String getThePostCode() {
        return thePostCode;
    }

    public String getTheExtraComments() {
        return theExtraComments;
    }

    public String getThePhoneNumber() {
        return thePhoneNumber;
    }

    public List<MealInfo> getTheMealInfos() {
        return theMealInfos;
    }
}
