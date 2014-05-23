package cs886.w14.proj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.util.StemmerException;

import twitter4j.GeoLocation;
import twitter4j.Status;
import weka.core.Stopwords;
import weka.core.stemmers.Stemmer;

public class ParsedTweet {
	public String user;
	public Date dateTime;
	public double []loc; // latitude and longitude
	public String msg;
	public List<String> bagOfWords;
	
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
		
		/* stemmer and weka jar only support static access
		SnowballStemmer stemmer = new SnowballStemmer();
		stemmer.setStemmer("english");
		*/
	    List<ParsedTweet> parsedTweets = new ArrayList<ParsedTweet>();
	    for (Status t : tweets) {
	    	ParsedTweet pt = new ParsedTweet(t);
	    	pt.computeBagOfWords(splitters, blackList, stopwords);
	    	parsedTweets.add(pt);
	    }
	    return parsedTweets;
	  }
	
	public ParsedTweet(Status t) {
		user = t.getUser().getScreenName();
		dateTime = t.getCreatedAt();
		msg = t.getText();
		
		loc = getTweetLocInfo(t);
	}

	public String toString() {
	    return "user = " + user + "\r\n"
	        + "Date and Time = " + dateTime + "\r\n"
	        + "location = " + loc.toString() + "\r\n"
	        + "msg = " + msg + "\r\n"
	        + "bagOfWords = " + bagOfWords.toString() + "\r\n"
	        + "======================================================================\r\n";
	  }
	
	
	/*
	 * get bag of words from a tweet msg
	 */
	public void computeBagOfWords(String splitters, List<String> blackList, Stopwords stopwords){// , Stemmer stemmer) {

		for (int i = 0; i < splitters.length(); i++) {
			msg = msg.replace(splitters.charAt(i), ' ');
		}
		String[] tokens = msg.split(" ");
		HashSet<String> tokenSet = new HashSet<String>();
		for (String token : tokens) {
			boolean legal = !stopwords.is(token);
			for (String regex : blackList) {
				if (token.matches(regex)) {
					legal = false;
					break;
				}
			}
			if (!legal) continue;
			// String stemmedToken = stemmer.stem(token);
			String stemmedToken;
			try {
				stemmedToken = EnglishSnowballStemmerFactory.getInstance().process(token);
				if (!stopwords.is(stemmedToken) && !tokenSet.contains(stemmedToken)) {
					tokenSet.add(stemmedToken);
				}
			} catch (StemmerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bagOfWords = new ArrayList<String>(tokenSet);
	}
	
	/*
	 * get loc info from a tweet msg
	 */
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
