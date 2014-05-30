package cs886.w14.proj.json;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import cs886.w14.proj.RuntimeParams;
import cs886.w14.proj.nlp.ParsedTweet;
import cs886.w14.proj.nlp.TweetsParser;
import cs886.w14.proj.sentiment.ANEWEntry;
import twitter4j.Status;

public class JsonParser {
	private final static Logger logger = Logger.getLogger(JsonParser.class.getName());
	
	// viz single 3d View
	public static String getSingleViewData(List<ParsedTweet> tweets, List<ANEWEntry> highlights) {
		String results = null;
	    List<JsonSingleViewObj> objList = new ArrayList<JsonSingleViewObj>();
	    for (ParsedTweet t : tweets) {
	    	objList.add(t.JsonSingleViewObj());
	    }
	    
	    for (ANEWEntry he : highlights) {
	    	objList.add(new JsonSingleViewObj(he));
	    }
	    
	    results = JsonSingleViewObj.JsonSingleViewFormatter(objList);
	    return results;
	}
	
	// viz compare view
	public static String getCompareViewData(List<ParsedTweet> tweets1, List<ParsedTweet> tweets2) {
		String results = null;
		JsonCompareViewObj obj1 = new JsonCompareViewObj(tweets1);
		JsonCompareViewObj obj2 = new JsonCompareViewObj(tweets2);
		results = JsonCompareViewObj.JsonCompareViewFormatter(obj1, obj2);
		return results;
	}
}
