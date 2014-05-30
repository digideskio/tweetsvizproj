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
		JSONArray data1 = new JSONArray();
		JSONArray data2 = new JSONArray();
		for (JsonSingleViewObj obj : objList) {
			JSONObject jobj = new JSONObject();
			jobj.put("x", obj.x);
			jobj.put("y", obj.y);
			jobj.put("z", obj.z);
			jobj.put("name", obj.name);
			JSONObject marker = new JSONObject();
			marker.put("radius", (int)obj.cfSize);
			marker.put("fillColor", obj.color);
			jobj.put("marker", marker);
			jobj.put("fillOpacity", obj.cfAlpha);
			
			if (obj.highlight) {
				jobj.put("type", "Sentiment");
				data2.add(jobj);
			} else {
				jobj.put("type", "Tweets");
				data1.add(jobj);
			}
		}
		JSONObject series1 = new JSONObject();
		JSONObject series2 = new JSONObject();
		series1.put("data", data1);
		series2.put("data", data2);
		series2.put("shape", "squarepin");
		JSONArray wrapper = new JSONArray();
		wrapper.add(series1);
		wrapper.add(series2);
		return (wrapper.toJSONString());
	}

	public double x, y, z;
	public String name;
	public String color;
	public double cfAlpha, cfSize;
	public boolean highlight = false;
	
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
	}
	
	public JsonSingleViewObj(ANEWEntry e) {
		DecimalFormat df = new DecimalFormat("#.##");  
		x = Double.valueOf(df.format(e.getValMn()));
		y = Double.valueOf(df.format(e.getAroMn()));
		z = Double.valueOf(df.format(e.getDomMn()));
		name = e.getWord();
		color = Color3d.HIGHLIGHTS_COLOR;
		cfSize = RuntimeParams.SCATTER_HIGHLIGHTS_RADIUS;
		cfAlpha = 1;
		highlight = true;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	private String insertNewLine(String str) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < str.length(); i++) {
			if (i%20 == 0) {
				sb.insert(i++, '\n');
			}
		}
	    return sb.toString();
	}

}
