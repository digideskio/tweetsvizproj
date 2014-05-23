package cs886.w14.proj.util;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4J {
	/**
	 * logger on the server util side
	 */
	private final Logger logger = Logger.getLogger(Twitter4J.class.getName());
	
	/**
	 * Singleton
	 */
	private static final Twitter4J INSTANCE = new Twitter4J();

	private Twitter twitter = null;
	
	private Twitter4J() {}
	
	public static Twitter4J getInstance() {
		return INSTANCE;
	}
	
	public void init() {
		logger.log(Level.INFO, "-------start connecting");
		// get app-auth token
		OAuth2Token token = getToken(RuntimeParams.CONSUMER_KEY,
				RuntimeParams.CONSUMER_SECRET);
		
		// connect to twitter
		twitter = connectToTwitter(RuntimeParams.CONSUMER_KEY,
				RuntimeParams.CONSUMER_SECRET, 
				token.getTokenType(),
				token.getAccessToken());
		logger.log(Level.INFO, "-------connected to Twitter Inc.");
	}
	
	/*
	 * search more than 100 tweets
	 */
	public ArrayList<Status> getQueryResults(String keyword) {
		
		Query query = new Query(keyword);
		long lastID = Long.MAX_VALUE;
		ArrayList<Status> tweets = new ArrayList<Status>();
		while (tweets.size() < RuntimeParams.MAX_NUM_OF_TWEETS) {
			if (RuntimeParams.MAX_NUM_OF_TWEETS - tweets.size() > 100) {
				query.setCount(100);
			} else {
				query.setCount(RuntimeParams.MAX_NUM_OF_TWEETS - tweets.size());
			}
				
			try {
				QueryResult result = twitter.search(query);
				tweets.addAll(result.getTweets());
				for (Status t : tweets) {
					if (t.getId() < lastID) lastID = t.getId();
				}
			} catch (TwitterException te) {
				te.printStackTrace();
				logger.log(Level.SEVERE, "Failed to search more tweets: " + te.getMessage());
				System.exit(-1);
			}
			query.setMaxId(lastID - 1);
		}
		
		return tweets;
	}
	
	/*
	 * filter tweets without loc info
	 * TODO
	 */
	public String getTweetsLocInfo(ArrayList<Status> tweets) {
		String rtn = "";
		for (int i = 0; i < tweets.size(); i++) {
			Status t = (Status) tweets.get(i);
			GeoLocation loc = t.getGeoLocation();
			String user = t.getUser().getScreenName();
			String msg = t.getText();
			if (loc != null) {
				Double lat = t.getGeoLocation().getLatitude();
				Double lon = t.getGeoLocation().getLongitude();
				rtn += i + " USER: " + user + " wrote: " + msg
						+ " located at " + lat + ", " + lon;
			} else
				rtn += i + " USER: " + user + " wrote: " + msg;
		}
		
		return rtn;
	}

	/*
	 * connect to the Twitter Inc. server
	 */
	private Twitter connectToTwitter(String conKey, String conSect, String tokenType, String token) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setApplicationOnlyAuthEnabled(true)
				.setOAuthConsumerKey(conKey)
				.setOAuthConsumerSecret(conSect)
				.setOAuth2TokenType(tokenType) 
				.setOAuth2AccessToken(token); 
			
		return new TwitterFactory(cb.build()).getInstance();
	}
	
	/*
	 * get bearer keyfrom Twitter inc.
	 */
	private OAuth2Token getToken(String conKey, String conSect){
		OAuth2Token token = null;
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setApplicationOnlyAuthEnabled(true)
				.setOAuthConsumerKey(conKey)
				.setOAuthConsumerSecret(conSect);
		try { 
			token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token(); 
		} catch (Exception e) { 
			logger.log(Level.SEVERE, "Can't get OAuth2 token"); 
			e.printStackTrace(); 
			System.exit(0); 
		}
		
		return token;
	}

	/*
	 * accept regex query
	 * TODO
	 */
	private String queryFormatter(String keyword) {
		// TODO
		return keyword;
	}
	
}
