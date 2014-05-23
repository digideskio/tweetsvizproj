package cs886.w14.proj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;

import twitter4j.GeoLocation;
import twitter4j.Status;
import weka.core.Stopwords;

public class ParsedTweet {
	public String user;
	public Date dateTime;
	public double []loc; // latitude and longitude
	public String msg;
	public List<String> bagOfWords;
	public String lang; // two-letter iso language code
	
	public List<ParsedTweet> retweets;
	boolean numOfFavorites;
	
	public static List<ParsedTweet> ParseTweetsFromWeb (ArrayList<Status> tweets) {
		String splitters = "\r\n\t.,;:'\"()?!/[]=_`~#";
		List<String> blackList = Arrays.asList("\\d*", ".{15}.+", ".", ".*--.*", ".*&.*", ".*@.*");
		Stopwords stopwords = new Stopwords();
		try {
			stopwords.read("./stopwords.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    List<ParsedTweet> parsedTweets = new ArrayList<ParsedTweet>();
	    for (Status t : tweets) {
	    	ParsedTweet pt = new ParsedTweet(t);
	    	if (pt.lang.startsWith("en")) parsedTweets.add(pt);
	    }
	    
	    computeBagOfWords(parsedTweets, splitters, blackList, stopwords, EnglishSnowballStemmerFactory.getInstance());
	    return parsedTweets;
	  }
	
	/*
	 * get bag of words from a tweet msg
	 */
	public static void computeBagOfWords(List<ParsedTweet> parsedTweets,
			String splitters, List<String> blackList, Stopwords stopwords,
			EnglishSnowballStemmerFactory stemmer) {
		for (ParsedTweet pt : parsedTweets) {
			for (int i = 0; i < splitters.length(); i++) {
				pt.msg = pt.msg.replace(splitters.charAt(i), ' ');
			}
			String[] tokens = pt.msg.split(" ");
			HashSet<String> tokenSet = new HashSet<String>();
			for (String token : tokens) {
				boolean legal = !stopwords.is(token);
				for (String regex : blackList) {
					if (token.matches(regex)) {
						legal = false;
						break;
					}
				}
				if (!legal)
					continue;
				try {
					String stemmedToken = stemmer.process(token);
					if (!stopwords.is(stemmedToken)
							&& !tokenSet.contains(stemmedToken)) {
						tokenSet.add(stemmedToken);
					}
				} catch (StemmerException e) {
					e.printStackTrace();
				}
			}
			pt.bagOfWords = new ArrayList<String>(tokenSet);
		}
	}
	
	public ParsedTweet(Status t) {
		user = t.getUser().getScreenName();
		dateTime = t.getCreatedAt();
		msg = t.getText();
		loc = getTweetLocInfo(t);
		lang = t.getLang();
	}

	public String toString() {
	    return "user = " + user + "\r\n"
	        + "Date and Time = " + dateTime + "\r\n"
	        + "location = " + loc.toString() + "\r\n"
	        + "msg = " + msg + "\r\n"
	        + "lang = " + lang + "\r\n"
	        + "bagOfWords = " + bagOfWords.toString() + "\r\n"
	        + "======================================================================\r\n";
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
}
