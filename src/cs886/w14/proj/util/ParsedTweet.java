package cs886.w14.proj.util;

import java.util.Date;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Status;

public class ParsedTweet {
	public String user;
	public Date dateTime;
	public double []loc; // latitude and longitude
	public String msg;
	public List<String> bagOfWords;
	public String lang; // two-letter iso language code
	
	// TODO
	public List<ParsedTweet> retweets;
	public boolean numOfFavorites;
	
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
