package cs886.w14.proj.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs886.w14.proj.RuntimeParams;
import cs886.w14.proj.nlp.ParsedTweet;
import cs886.w14.proj.sentiment.ANEWEntry;

public class JsonCompareViewObj {
	private final Logger logger = Logger.getLogger(JsonCompareViewObj.class.getName());
	
	public static String JsonCompareViewFormatter(JsonCompareViewObj cvobj1, JsonCompareViewObj cvobj2) {
		JSONArray list = new JSONArray();
		JSONObject jobj1 = new JSONObject();
		jobj1.put("percentage", cvobj1.percentage);
		jobj1.put("topmsg", cvobj1.topMsg);
		jobj1.put("topwords", cvobj1.topWords);
		JSONObject jobj2 = new JSONObject();
		jobj2.put("percentage", cvobj2.percentage);
		jobj2.put("topmsg", cvobj2.topMsg);
		jobj2.put("topwords", cvobj2.topWords);
		
		/*
		JSONObject marker = new JSONObject();
		marker.put("radius", (int)obj.cfSize);
		marker.put("fillColor", obj.color);
		jobj.put("marker", marker);
		jobj.put("fillOpacity", obj.cfAlpha);
		*/
			
		JSONObject wrapper = new JSONObject();
		wrapper.put("data1", jobj1);
		wrapper.put("data2", jobj2);
		return (wrapper.toJSONString());
	}
	
	public double percentage;
	public List<String> topMsg; // top 5 retweets(favorite) msges
	public List<String> topWords; // words with top 5 frequency
	
	public JsonCompareViewObj(List<ParsedTweet> tweets) {
		topMsg = new ArrayList<String>();
		topWords = new ArrayList<String>();
		percentage = calPercentage(tweets);
		logger.log(Level.INFO, "========== percentage = " + percentage);
		
		HashMap<String, ANEWEntry> bagOfWordsForAllTweets = new HashMap<String, ANEWEntry>();
		TreeSet<ParsedTweet> topFavRetweets = new TreeSet<ParsedTweet>();
		for (ParsedTweet t : tweets) {
			// collect all words appearing in tweets set
			for (ANEWEntry e : t.analyzer.words) {
				if (bagOfWordsForAllTweets.size() != 0 
						&& bagOfWordsForAllTweets.containsKey(e.getWord())) {
					bagOfWordsForAllTweets.get(e.getWord()).wordFreqInTweets += 1;
				} else {
					bagOfWordsForAllTweets.put(e.getWord(), e);
				}
			}
			
			// calculate top fav/retweets
			if (topFavRetweets.size() < RuntimeParams.NUM_OF_TOP_FAV_RETWEETS) {
				topFavRetweets.add(t);
			} else {
				if (topFavRetweets.lower(t) != null) {
					topFavRetweets.pollFirst();
					topFavRetweets.add(t);
				}
			}
	    }
		
		// calulate top common words
		TreeSet<ANEWEntry> topCommonWords = new TreeSet<ANEWEntry>();
		Iterator it = bagOfWordsForAllTweets.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        ANEWEntry curEntry = (ANEWEntry)pairs.getValue();
	        if (topCommonWords.size() < RuntimeParams.NUM_OF_TOP_COMMON_WORDS) {
	        	topCommonWords.add(curEntry);
			} else {
				if (topCommonWords.lower(curEntry) != null) {
					topCommonWords.pollFirst();
					topCommonWords.add(curEntry);
				}
			}
	        // avoids a ConcurrentModificationException
	        it.remove(); 
	    }
	    
	    // pass data
	    Iterator<ParsedTweet> itr = topFavRetweets.iterator();
	    while(itr.hasNext()){
	    	ParsedTweet t = itr.next();
	    	topMsg.add(t.msg);
	    }
	    
	    Iterator<ANEWEntry> itr2 = topCommonWords.iterator();
	    while(itr2.hasNext()){
	    	ANEWEntry e = itr2.next();
	    	topWords.add(e.getWord());
	    }
	}
	
	private double calPercentage(List<ParsedTweet> tweets) {
		int i=0;
		double pct = 0;
		if (tweets != null && tweets.size() != 0) {
			for (ParsedTweet t : tweets) {
		    	if (t.analyzer.getFusedVal() > 5) i++;
		    }
			pct = (double)i / (double)tweets.size();
		}
		return pct;
	}
	
	/*private List<String> calTopWords() {
		int maxWordNum = -1;
	}
	*/
}
