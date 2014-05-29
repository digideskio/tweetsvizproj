package cs886.w14.proj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs886.w14.proj.nlp.ParsedTweet;

public class GsonObj {
	private final Logger logger = Logger.getLogger(GsonObj.class.getName());
	
	public static String GsonFormatter(List<GsonObj> objList) {
		
	 
		JSONArray list = new JSONArray();
		for (GsonObj obj : objList) {
			JSONObject jobj = new JSONObject();
			jobj.put("name", obj.name);
			jobj.put("x", obj.x);
			jobj.put("y", obj.y);
			jobj.put("z", obj.z);
			jobj.put("color", obj.color);
			list.add(jobj);
		}
		JSONObject wrapper = new JSONObject();
		wrapper.put("data", list);
	 
		
		/*Gson gson = new Gson();
		HashMap<String, List<GsonObj>> publisher = new HashMap<String, List<GsonObj>>();
		
		//publisher.put("data", objList);
		List<HashMap<String, List<GsonObj>>> finaldata = new ArrayList<HashMap<String, List<GsonObj>>>();
		finaldata.add(publisher);
		return gson.toJson(publisher);
		*/
		return (wrapper.toJSONString());
	}

	public double x, y, z;
	public String name;
	public String color;
	
	public GsonObj (ParsedTweet pt) {
		x = pt.analyzer.getVal();
		y = pt.analyzer.getAro();
		z = pt.analyzer.getDom();
		name = pt.msg;
		
		int gindex = (int) pt.analyzer.getFusedVal();
		logger.log(Level.INFO, "!!------- gindex -------" + pt.analyzer.getFusedVal());
		logger.log(Level.INFO, "!!------- gindex -------" + gindex);
		
		color = Color3d.getGradientColorHex(Color3d.GRADIENT_COLOR_LEVEL, gindex);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
