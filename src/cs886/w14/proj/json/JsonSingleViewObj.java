package cs886.w14.proj.json;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs886.w14.proj.RuntimeParams;
import cs886.w14.proj.nlp.ParsedTweet;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.util.Color3d;

public class JsonSingleViewObj {
	private final Logger logger = Logger.getLogger(JsonSingleViewObj.class.getName());
	
	public static String JsonSingleViewFormatter(List<JsonSingleViewObj> objList) {
		JSONArray list = new JSONArray();
		for (JsonSingleViewObj obj : objList) {
			JSONObject jobj = new JSONObject();
			jobj.put("name", obj.name);
			jobj.put("x", obj.x);
			jobj.put("y", obj.y);
			jobj.put("z", obj.z);
			// jobj.put("color", obj.color);
			JSONObject marker = new JSONObject();
			marker.put("radius", (int)obj.cfSize);
			marker.put("fillColor", obj.color);
			jobj.put("marker", marker);
			jobj.put("fillOpacity", obj.cfAlpha);
			list.add(jobj);
		}
		JSONObject wrapper = new JSONObject();
		wrapper.put("data", list);
		return (wrapper.toJSONString());
	}

	public double x, y, z;
	public String name;
	public String color;
	public double cfAlpha, cfSize;
	
	public JsonSingleViewObj (ParsedTweet pt) {
		x = pt.analyzer.getVal();
		y = pt.analyzer.getAro();
		z = pt.analyzer.getDom();
		name = pt.originalMsg;
		DecimalFormat df = new DecimalFormat("#.#");      
		cfSize = RuntimeParams.SCATTER_RADIUS_MIN + RuntimeParams.SCATTER_RADIUS_MAX_INTERVAL * Double.valueOf(df.format(pt.confidence));
		cfAlpha = Double.valueOf(df.format(pt.confidenceANEWfq));
		
		int gindex = (int) pt.analyzer.getFusedVal();
		Color3d rgb = Color3d.getGradientColor3d(Color3d.GRADIENT_COLOR_LEVEL, gindex);
		color = new Color3d(rgb.r, rgb.g, rgb.b, cfAlpha).toRGBAValue();
		logger.log(Level.INFO, "=====color===: " + color);
	}
	
	public JsonSingleViewObj(ANEWEntry e) {
		x = e.getValMn();
		y = e.getAroMn();
		z = e.getDomMn();
		name = e.getWord();
		color = "rgba(204,248,255,1)";
		cfSize = RuntimeParams.SCATTER_HIGHLIGHTS_RADIUS;
		cfAlpha = 1;
		logger.log(Level.INFO, "=====include highlights word = : " + name);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
