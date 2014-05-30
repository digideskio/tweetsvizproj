package cs886.w14.proj.nlp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;

import twitter4j.Status;
import weka.core.Stopwords;
import cs886.w14.proj.RuntimeParams;
import cs886.w14.proj.TweetsVizJSPServlet;
import cs886.w14.proj.sentiment.ANEWDicWrapper;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.util.Twitter4JDriver;

public class TweetsParser {
	private static String EMOTICONS_FP = "./nlp/emoticons.txt";
	private static String splitters = "\r\n\t.,;:'\"()?!/[]=_`~#";
	private static List<String> blackList = Arrays.asList("\\d*", ".{15}.+", ".", ".*--.*", ".*&.*", ".*@.*");
	private static ANEWDicWrapper _dic = new ANEWDicWrapper("anew/ANEW2010All.txt");
	private static ANEWDicWrapper _emoticon_dic = new ANEWDicWrapper("anew/ANEWEmoticons.txt");
	
	private final static Logger logger = Logger.getLogger(TweetsParser.class.getName());
	
	public static ANEWDicWrapper getDic() {
		return _dic;
	}
	/*
	 * parse all tweets (status)
	 */
	public static List<ParsedTweet> ParseTweetsFromWeb (ArrayList<Status> tweets, Stopwords stopwords) {
	    List<ParsedTweet> parsedTweets = new ArrayList<ParsedTweet>();
	    for (Status t : tweets) {
	    	ParsedTweet pt = new ParsedTweet(t);
	    	if (pt.lang.equals("en")) parsedTweets.add(pt);
	    }

	    computeBagOfWords(parsedTweets, stopwords, EnglishSnowballStemmerFactory.getInstance());
	    return parseSentiment(parsedTweets);
	  }
	
	/*
	 * get bag of words from a tweet msg
	 */
	public static void computeBagOfWords(List<ParsedTweet> parsedTweets,
			Stopwords stopwords, EnglishSnowballStemmerFactory stemmer) {
		List<String> emoticonsList = getEmoticons();
		for (ParsedTweet pt : parsedTweets) {
			for (String emoticon : emoticonsList) {
				if (pt.msg.contains(emoticon)) {
					pt.bagOfEmoticons.add(emoticon);
				}
			}
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
	
	/*
	 * get list of emoticons
	 */
	public static ArrayList<String> getEmoticons() {
		ArrayList<String> emoticonsList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(EMOTICONS_FP));
			String line;
			while ((line = br.readLine()) != null) {
				emoticonsList.add(line);
			}
			br.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, "File not found "+e.toString());
		}
		return emoticonsList;
	}
	
	private static List<ParsedTweet> parseSentiment(List<ParsedTweet> tweets) {
		// generate ANEW parser for each tweet
	    logger.log(Level.INFO, "-------size after lang filter =" + tweets.size());
	    for (ParsedTweet t: tweets) {
	    	logger.log(Level.INFO, "------- new tweets-------");
	    	logger.log(Level.INFO, "bagofwords = " + t.bagOfWords.toString());
	    	logger.log(Level.INFO, "emoticons = " + t.bagOfEmoticons.toString());
	    	t.generateANEWAnalyzer(_dic, _emoticon_dic);
	    }
	    
	    // filter tweets with less than MIN_NUM_OF_VALID_WORDS valid sentimental words
	    logger.log(Level.INFO, "-------size before ANEW analysis = " + tweets.size() );
	    ListIterator li = tweets.listIterator();
	    while(li.hasNext()) {
	    	ParsedTweet tweet = (ParsedTweet)li.next();
	    	if(tweet.analyzer.getNumofValidWords() < RuntimeParams.MIN_NUM_OF_VALID_WORDS) {
	    		li.remove();
	    	}
	    }
	    logger.log(Level.INFO, "-------size after ANEW analysis = " + tweets.size() );
	    return tweets;
	}
	
}
