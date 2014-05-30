package cs886.w14.proj.nlp;

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
import cs886.w14.proj.sentiment.ANEWDicWrapper;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.sentiment.TweetAnalysis;
import cs886.w14.proj.util.GsonSngleViewObj;

public class ParsedTweet {
	public String user;
	public Date dateTime;
	public double []loc; // latitude and longitude
	public String msg;
	public List<String> bagOfWords;
	public List<String> bagOfEmoticons;
	public String lang; // two-letter iso language code
	public TweetAnalysis analyzer;
	public double confidence; // # of valid ANEW words / bagOfWords.size
	public double confidenceANEWfq; 
	
	// TODO
	public int numOfFavorites, numOfRetweets;
	
	
	private final Logger logger = Logger.getLogger(ParsedTweet.class.getName());
	
	public ParsedTweet(Status t) {
		user = t.getUser().getScreenName();
		dateTime = t.getCreatedAt();
		msg = removeUrl(t.getText());
		loc = getTweetLocInfo(t);
		lang = t.getLang();
		bagOfEmoticons = new ArrayList<String>();
		analyzer = new TweetAnalysis();
		numOfRetweets = t.getRetweetCount();
		numOfFavorites = t.getFavoriteCount();
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
		calConfidence(dic);
    	for (String e: bagOfEmoticons) {
    		ANEWEntry entry = edic.getEntrybyWord(e);
    		if(entry != null) {
    			analyzer.addWord(entry);
    			logger.log(Level.INFO, "EMOTICON HIT!" + e);
    		}
    	}
	}
	
	// description of each data point showing in the tooltip
	public GsonSngleViewObj getGsonObj() {
		return new GsonSngleViewObj(this);
	}
	
	// exclude emoticons
	private void calConfidence(ANEWDicWrapper dic) {
		if (bagOfWords != null && bagOfWords.size() != 0 && analyzer!= null) {
			confidence = (double)analyzer.words.size() / (double)bagOfWords.size();
			logger.log(Level.INFO, "===== analyzer.words.size===: " + analyzer.words.size());
			logger.log(Level.INFO, "===== bagOfWords.size ===: " + bagOfWords.size());
			logger.log(Level.INFO, "===== analyzer confidence===: " + confidence);
			if (analyzer.words.size() != 0) {
				// ANEW fq confidence
				int sum = 0;
				for (ANEWEntry e : analyzer.words) {
					sum += e.getWdnum();
				}
				confidenceANEWfq = normalize(dic.fqMin, dic.fqMax, sum/analyzer.words.size());
			}
		}
	}
	
	private double normalize(double min, double max, double value) {
		if (max >= min && value >= min && value <= max) {
			return (value - min) / (max - min);
		} else {
			logger.log(Level.WARNING, "unable to normalize!");
			return -1;
		}
	}
	
	public String getANEWCoord() {
		String rtn = null;
		if (analyzer != null) {
			rtn = "[" + 
					analyzer.getVal() + "," + 
					analyzer.getAro() + "," + 
					analyzer.getDom() + "]";
		}
		return rtn;
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
		logger.log(Level.INFO, "tweets = " + text);
        String rtn = text;
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(Pattern.quote(urlPattern), Pattern.CASE_INSENSITIVE);
        // Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        
        Matcher m = p.matcher(rtn);
        logger.log(Level.INFO, "Pattern = " + p.toString());
        logger.log(Level.INFO, "Matcher = " + m.toString());
        int i = 0;
        while (m.find()) {
            rtn = rtn.replaceAll(m.group(i),"").trim();
            i++;
        }
        return rtn;
    }
    
}
