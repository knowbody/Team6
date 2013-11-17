package com.team6.FoodChainFrontEnd;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DestinationInfo implements Parcelable {
    private final String thePostCode;
    private final String theID;
    private String theExtraComments;
    private List<String> theMealInfos;

    public DestinationInfo(String thePostCode, String theID, String theExtraComments, List<String> theMealInfos) {
        this.thePostCode = thePostCode;
        this.theID = theID;
        this.theExtraComments = theExtraComments;
        this.theMealInfos = theMealInfos;
    }


    public String getThePostCode() {
        return thePostCode;
    }

    public String getTheExtraComments() {
        return theExtraComments;
    }

    public List<String> getTheMealInfos() {
        return theMealInfos;
    }

    public void setTheExtraComments(String theExtraComments) {
        this.theExtraComments = theExtraComments;
    }

    public void setTheMealInfos(List<String> theMealInfos) {
        this.theMealInfos = theMealInfos;
    }

    public String getTheID() {
        return theID;
    }

    @Override
    public int describeContents() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //To change body of implemented methods use File | Settings | File Templates.
        dest.writeString(this.theID);
        dest.writeString(this.thePostCode);
        dest.writeStringList(this.theMealInfos);
    }
}
