package cs886.w14.proj.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.GeoLocation;
import twitter4j.Status;
import cs886.w14.proj.TweetsVizJSPServlet;
import cs886.w14.proj.util.ANEWEntry;
import cs886.w14.proj.util.TweetAnalysis;

public class ParsedTweet {
	public String user;
	public Date dateTime;
	public double []loc; // latitude and longitude
	public String msg;
	public List<String> bagOfWords;
	public List<String> bagOfEmoticons;
	public String lang; // two-letter iso language code
	public TweetAnalysis analyzer;
	private final Logger logger = Logger.getLogger(ParsedTweet.class.getName());

	// TODO
	public List<ParsedTweet> retweets;
	public boolean numOfFavorites;
	
	public ParsedTweet(Status t) {
		user = t.getUser().getScreenName();
		dateTime = t.getCreatedAt();
		msg = removeUrl(t.getText());
		loc = getTweetLocInfo(t);
		lang = t.getLang();
		bagOfEmoticons = new ArrayList<String>();
		analyzer = new TweetAnalysis();
	}

	public String toString() {
	    return "user = " + user + "\r\n"
	        + "Date and Time = " + dateTime + "\r\n"
	        + "location = " + loc.toString() + "\r\n"
	        + "msg = " + msg + "\r\n"
	        + "lang = " + lang + "\r\n"
	        + "bagOfWords = " + bagOfWords.toString() + "\r\n"
	        + "bagOfEmoticons = " + bagOfEmoticons.toString() + "\r\n"
	        + "======================================================================\r\n";
	}
	
	public void generateANEWAnalyzer(ANEWDicWrapper dic, ANEWDicWrapper edic) {
		for (String word: bagOfWords) {
    		ANEWEntry entry = dic.getEntrybyWord(word);
    		if(entry != null) {
    			analyzer.addWord(entry);
    			logger.log(Level.INFO, "WORD HIT!" + word);
    		}
    	}
    	for (String e: bagOfEmoticons) {
    		ANEWEntry entry = edic.getEntrybyWord(e);
    		if(entry != null) {
    			analyzer.addWord(entry);
    			logger.log(Level.INFO, "EMOTICON HIT!" + e);
    		}
    	}
	}
	
	private double[] getTweetLocInfo(Status t) {
		double[] loc = { 0, 0 };
		GeoLocation locInfo = t.getGeoLocation();
		if (locInfo != null) {
			loc[0] = t.getGeoLocation().getLatitude();
			loc[1] = t.getGeoLocation().getLongitude();
		}
		return loc;
	}
	
	/*
	 * remove hyper links within msg
	 */
	private String removeUrl(String text)
    {
        String rtn = text;
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(rtn);
        int i = 0;
        while (m.find()) {
            rtn = rtn.replaceAll(m.group(i),"").trim();
            i++;
        }
        return rtn;
    }
    
}
