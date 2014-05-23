package cs886.w14.proj.nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;

import twitter4j.Status;
import weka.core.Stopwords;
import cs886.w14.proj.util.ParsedTweet;
import cs886.w14.proj.util.Twitter4JDriver;

public class TweetsParser {
	public static String splitters = "\r\n\t.,;:'\"()?!/[]=_`~#";
	public static List<String> blackList = Arrays.asList("\\d*", ".{15}.+", ".", ".*--.*", ".*&.*", ".*@.*");
	
	public static List<ParsedTweet> ParseTweetsFromWeb (ArrayList<Status> tweets, Stopwords stopwords) {
	    List<ParsedTweet> parsedTweets = new ArrayList<ParsedTweet>();
	    for (Status t : tweets) {
	    	ParsedTweet pt = new ParsedTweet(t);
	    	if (pt.lang.startsWith("en")) parsedTweets.add(pt);
	    }
	    
	    computeBagOfWords(parsedTweets, stopwords, EnglishSnowballStemmerFactory.getInstance());
	    return parsedTweets;
	  }
	
	/*
	 * get bag of words from a tweet msg
	 */
	public static void computeBagOfWords(List<ParsedTweet> parsedTweets,
			Stopwords stopwords, EnglishSnowballStemmerFactory stemmer) {
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

}
