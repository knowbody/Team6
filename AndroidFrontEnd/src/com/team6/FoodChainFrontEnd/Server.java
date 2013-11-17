package com.team6.FoodChainFrontEnd;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private static String server = "http://ec2-54-216-92-247.eu-west-1.compute.amazonaws.com/api/";
    public static void getRoutesFromServer2(final String aDriverID)
    {
    	new Thread(new Runnable() {
    	    //Thread to stop network calls on the UI thread
    	    public void run() {
    	    	getJson(aDriverID);
    	    }
    	}).start();
    }
    
    private static void getJson(String aDriverID)
    {
		try {
    	JSONParser json = new JSONParser();
    	JSONObject obj = json.getJSONFromUrl(server + "route");
    	
    	JSONArray routes = (JSONArray) obj.get("route");
    	String id = "";
    	String waypoints = "";
    	String driver = "";
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
		 return;
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
        	
        	String[] ids = new String[]
        	
        	JSONArray susers = (JSONArray) obj.get("service_users");
        	for (int i = 0; i < susers.length(); i++) {
      		  JSONObject user = susers.getJSONObject(i);
      		  id = (String) route.get("id");
        	}
    	}
    	
    	
    	
    	
    		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

