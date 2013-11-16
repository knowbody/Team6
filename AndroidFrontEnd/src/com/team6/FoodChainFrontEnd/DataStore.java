package com.team6.FoodChainFrontEnd;

import android.content.Context;

import java.io.*;
import java.util.Collection;

public class DataStore {
    public static void store(Collection<DestinationInfo> aDestinationInfoCollection, Context aContext) {
        File myFile = new File(aContext.getFilesDir(), "data.dat");
        FileOutputStream myFileOutputStream = null;
        try {
            myFileOutputStream = new FileOutputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
            myObjectOutputStream.writeObject(aDestinationInfoCollection);
            myObjectOutputStream.close();
            myFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static boolean dataExists(Context aContext) {
        File myFile = new File(aContext.getFilesDir(), "data.dat");
        return myFile.exists();

    }
}
