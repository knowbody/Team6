package somepack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Att1 {
	public static String getCordinates(String address,String county) throws IOException, ParserConfigurationException, SAXException{
	    String thisLine;

	    address = address.replace(",", "+");
	    address = address.replace(" ", "+");
	    county = county.replace(" ", "");

	    String fullAddress = address+"+"+county;
	    System.out.println(fullAddress);

	    URL url = new URL("http://maps.google.com/maps/geo?q="+fullAddress+"&output=xml&key=ABQIAAAANGTAqDyDam_07aWkklK2NBSD41w" +
	            "X8VhCBpuiDVjGbFNuXE31lhQB8Gkwy-wmYbmaHIbJtfnlR9I_9A");

	    BufferedReader theHTML = new BufferedReader(new InputStreamReader(url.openStream()));

	    FileWriter fstream = new FileWriter("url.xml");
	    BufferedWriter out = new BufferedWriter(fstream);
	    while ((thisLine = theHTML.readLine()) != null)
	        out.write(thisLine);
	    out.close();

	    File file = new File("url.xml");
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = (Document) db.parse(file);
	    ((org.w3c.dom.Document) doc).getDocumentElement().normalize();
	    NodeList nl = ((org.w3c.dom.Document) doc).getElementsByTagName("code");
	    Element n = (Element)nl.item(0);
	    String st = ((Node) n).getFirstChild().getNodeValue();

	    if (st.equals("200"))
	    {
	        NodeList n2 = ((org.w3c.dom.Document) doc).getElementsByTagName("coordinates");
	        Element nn = (Element)n2.item(0);
	        String st1 = ((Node) nn).getFirstChild().getNodeValue();


	        return st1;
	    }
	    else
	  
	        return null;
	   
	    
	}
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
    	JSONObject obj = null;
		
    	//double[] result = getLatLong("E6+3SE");
    	ArrayList<String> postcodes = new ArrayList<String>();
    	postcodes.add("E10+7NL");
    	postcodes.add("E14+8AH");
    	postcodes.add("E15+3EP");
    	postcodes.add("E2+8QU");
		DataTuple result = getRoute("E6+3SE", postcodes);
    	
    	String a = "";
	//System.out.println(	Att1.getCordinates("E14 3EX","London" ));

}
	
	 static String join(Collection<?> s, String delimiter) {
	     StringBuilder builder = new StringBuilder();
	     Iterator<?> iter = s.iterator();
	     while (iter.hasNext()) {
	         builder.append(iter.next());
	         if (!iter.hasNext()) {
	           break;                  
	         }
	         builder.append(delimiter);
	     }
	     return builder.toString();
	 }
	
	public static DataTuple getRoute(String originPostcode, ArrayList<String> postCodes)
	{
		int timeInSeconds = 0;
		int[] wayPointOrder = new int[postCodes.size()];
		
		
		JSONObject obj = null;
		try {
			try {
				String postcodes = join(postCodes, ",UK|")+",UK";
				obj = JsonReader.readJsonFromUrl("http://maps.googleapis.com/maps/api/directions/json?origin=" + originPostcode + ",UK&destination=" + originPostcode + ",UK&waypoints=optimize:true|"+ postcodes + "&sensor=false");
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	String str = obj.toString();
		int s = str.indexOf("waypoint_order");
		String t = str.substring(s + 16,s + 50);
		
		int s2 = t.indexOf('[');
		int f2 = t.indexOf(']');
		
		String[] r2 = t.substring(s2+1,f2).split(",");
		
		int index = 0;
		for(String val : r2) {
		 wayPointOrder[index++] = Integer.parseInt(val);
		}
		
		
		try {
			JSONArray j2 = obj.getJSONArray("routes");
			JSONArray j3 = (JSONArray)jsonGet(j2, "legs");
			
			ArrayList<JSONObject> js = new ArrayList<JSONObject>();
			for (int i = 0; i < j3.length(); i++) {
				   JSONObject j = j3.getJSONObject(i);
				   Iterator it = j.keys();
				   while (it.hasNext()) {
				      String n = (String) it.next();
				      if(n.equals("duration"))
				    	  js.add((JSONObject)j.get(n));
				   }
				}
			
			for(JSONObject duration : js)
			{
				Integer j5 = (Integer)duration.get("value");
				timeInSeconds += j5;
			}
			
			
			//JSONArray j2 = (JSONArray) obj.get("routes");
			//JSONObject legs = jsonGet(j2, "legs");
			//JSONObject duration = legs.getJSONObject("duration");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new DataTuple(timeInSeconds, wayPointOrder);
	}
	
	private static Object jsonGet(JSONArray j2, String index) throws JSONException
	{
		HashMap<String, Object> pairs = new HashMap<String, Object>();
		for (int i = 0; i < j2.length(); i++) {
		   JSONObject j = j2.getJSONObject(i);
		   Iterator it = j.keys();
		   while (it.hasNext()) {
		      String n = (String) it.next();
		      pairs.put(n, j.get(n));
		   }
		}
		
		Set<String> mm = pairs.keySet();
		
		return pairs.get(index);
	}
	
	public static double[] getLatLong(String postcode)
	{
		JSONObject obj = null;
		try {
			//String postcode = "E6+3SE";
			try {
				obj = JsonReader.readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address="+postcode+",+UK&sensor=false");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	String str = obj.toString();
		int s = str.indexOf("\"location\":{");
		int f = str.indexOf('}', s);
		String sub = str.substring(s+12, f);
		
		String[] s2 = sub.split(",");
		double lat = Double.parseDouble(s2[1].substring(6));
		double lon = Double.parseDouble(s2[0].substring(6));
		
		double[] r = new double[2];
		r[0] = lat;
		r[1] = lon;
		
		return r;
	}
}