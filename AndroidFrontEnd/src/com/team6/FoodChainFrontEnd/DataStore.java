package com.team6.FoodChainFrontEnd;

import android.content.Context;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class DataStore {
    public static void store(List<DestinationInfo> aDestinationInfoCollection, Context aContext) {
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
    public static List<DestinationInfo> load(Context aContext) {
        File myFile = new File(aContext.getFilesDir(), "data.dat");
        FileInputStream myFileInputStream = null;
        List<DestinationInfo> myReturnCollection = null;
        try {
            myFileInputStream = new FileInputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            try {
                myReturnCollection = (List<DestinationInfo>) myObjectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            myObjectInputStream.close();
            myFileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return myReturnCollection;
    }
}
