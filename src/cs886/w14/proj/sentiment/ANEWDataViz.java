package cs886.w14.proj.sentiment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import cs886.w14.proj.RuntimeParams;
import cs886.w14.proj.nlp.ParsedTweet;
import cs886.w14.proj.nlp.TweetsParser;
import cs886.w14.proj.util.GsonSngleViewObj;
import twitter4j.Status;

public class ANEWDataViz {
	private final static Logger logger = Logger.getLogger(ANEWDataViz.class.getName());
	
	// viz single 3d View
	public static String getSingleViewData(List<ParsedTweet> tweets) {
		String results = null;
	    List<GsonSngleViewObj> objList = new ArrayList<GsonSngleViewObj>();
	    for (ParsedTweet t : tweets) {
	    	objList.add(t.getGsonObj());
	    }
	    results = GsonSngleViewObj.GsonFormatter(objList);
	    return results;
	}
	
	// viz compare view
	public static String getCompareViewData(List<ParsedTweet> tweets1, List<ParsedTweet> tweets2) {
		
		return "";
	}
}
