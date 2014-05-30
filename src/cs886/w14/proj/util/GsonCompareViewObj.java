package cs886.w14.proj.util;

import java.util.List;

import cs886.w14.proj.nlp.ParsedTweet;

public class GsonCompareViewObj {
	public double percentage;
	public List<String> topMsg; // top 5 retweets(favorite) msges
	public List<String> topWords; // words with top 5 frequency
	
	public GsonCompareViewObj(List<ParsedTweet> tweets) {
		int maxRetweetsFavo = -1;
		
		for (ParsedTweet t : tweets) {
			if ((t.numOfFavorites + t.numOfRetweets) > maxRetweetsFavo) {
				maxRetweetsFavo = t.numOfFavorites + t.numOfRetweets;
			}
	    }
	}
	
	private double calPercentage(List<ParsedTweet> tweets) {
		int i=0;
		double pct = 0;
		if (tweets != null && tweets.size() != 0) {
			for (ParsedTweet t : tweets) {
		    	if (t.analyzer.getFusedVal() > 5) i++;
		    }
			pct = i / tweets.size();
		}
		return pct;
	}
	
	/*private List<String> calTopWords() {
		int maxWordNum = -1;
	}
	*/
}
