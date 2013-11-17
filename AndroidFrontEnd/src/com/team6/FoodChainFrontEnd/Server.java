package com.team6.FoodChainFrontEnd;

import java.util.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {
//    public static List<DestinationInfo> getRoutesFromServer(String aDriverID) {
//        return myDestinationInfos;
//    }
    private static String server = "http://ec2-54-216-92-247.eu-west-1.compute.amazonaws.com/api/";
    public static List<DestinationInfo> getRoutesFromServer2(final String aDriverID)
    {
    	new Thread(new Runnable() {
    	    public void run() {
    	    	PersistentStore.myDestinationInfos2 = getJson(aDriverID);
    	    }
    	}).start();
    	
    	
    	while(true) {
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		if(PersistentStore.myDestinationInfos2 != null)
    			return PersistentStore.myDestinationInfos2;
    	}
    }
    
    public static ArrayList<DestinationInfo> getJson(String aDriverID)
    {
    	ArrayList<DestinationInfo> myDestinationInfos = new ArrayList<DestinationInfo>();
		try {
    	JSONParser json = new JSONParser();
    	JSONObject obj = json.getJSONFromUrl(server + "route");
    	
    	JSONArray routes = (JSONArray) obj.get("routes");
    	String id = "";
        String name = "";
        String status = "";
        String postcode = "";
    	String waypoints = "";
    	String driver = "";
        String meal_type = "";
    	boolean found = false;
    	for (int i = 0; i < routes.length(); i++) {
    		  JSONObject route = routes.getJSONObject(i);
    		  id = (String) route.get("id");
    		  waypoints = (String) route.get("waypoints");
    		  driver = (String) route.get("driver");
    		  if(driver.equals(aDriverID))
    		  {
    			  found = true;
    			  break;
    		  }
    	}
    	
    	if(found == false)
    		//TODO: Failure
		    return null;
    	else
    	{
    		String[] vals = waypoints.split(",");
    		int[] waypointIndexes = new int[vals.length];
    		int index = 0;
    		for(String val : vals) {
    			waypointIndexes[index++] = Integer.parseInt(val);
    		}
    		
    		
    		json = new JSONParser();
        	obj = json.getJSONFromUrl(server + "service_user");
        	
        	//String[] ids = new String[]
        	JSONArray susers = (JSONArray) obj.get("service_users");
        	for (int i = 0; i < susers.length(); i++) {
      		  JSONObject user = susers.getJSONObject(i);
      		  id = (String) user.get("id");
      		  if(Contains(waypointIndexes, Integer.parseInt(id)))
      		  {
	              name = (String) user.get("name");
	              postcode = (String) user.get("postcode");
	              myDestinationInfos.add(new DestinationInfo(postcode,id,null, null ));
      		  }
        	}
            for(DestinationInfo destinationInfo : myDestinationInfos) {
                JSONObject mealJSON = json.getJSONFromUrl(server+"service_user_meal/"+destinationInfo.getTheID());
                JSONArray mealJSONArray = (JSONArray) mealJSON.get("service_user_meals");
                ArrayList<String> mealTypes = new ArrayList<String>();
                for(int i = 0; i < mealJSONArray.length(); ++i) {
                    JSONObject meal =  mealJSONArray.getJSONObject(i);
                    meal_type = (String) meal.get("meal_type");
                    mealTypes.add(meal_type);
                }
                destinationInfo.setTheMealInfos(mealTypes);
            }
    	}
    		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return myDestinationInfos;
    }
    
    public static boolean Contains(int[] a, int x) {
    	for(int i : a) 
    	{
    		if(i == x)
    			return true;
    	}
    	return false;
    }
}

