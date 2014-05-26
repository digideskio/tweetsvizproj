package cs886.w14.proj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cs886.w14.proj.nlp.TweetsParser;
import cs886.w14.proj.util.ParsedTweet;
import cs886.w14.proj.util.Twitter4JDriver;
import cs886.w14.proj.util.ANEWDicWrapper;
import cs886.w14.proj.util.ANEWEntry;
import twitter4j.Status;
import weka.core.Stopwords;
import cs886.w14.proj.util.TweetAnalysis;
import cs886.w14.proj.util.Gaussian;;


public class TweetsVizJSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(TweetsVizJSPServlet.class.getName());
	private List<ParsedTweet> tweets;
	private static ANEWDicWrapper _dic = new ANEWDicWrapper("anew/ANEW2010All.txt");
	
	public TweetsVizJSPServlet() { 
		

	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword").toString();
		Stopwords stopwords = new Stopwords();
		try {
			stopwords.read("./nlp/stopwords.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String results = keyword + "\n";
		if (keyword.equals("")) {
			 results = "no input";
		} else if (stopwords.is(keyword)) {
			results = "keyword belong to the list of stopword, please enter another keyword to avoid meaningless serach query, thank you!";
		} else {
			Twitter4JDriver.getInstance().init();
		    ArrayList<Status> rawtweets = Twitter4JDriver.getInstance().getQueryResults(keyword);
		    logger.log(Level.INFO, "-------revceived tweets size: " + rawtweets.size());
		    
		    // FOR MINGYU LIU TO START
		    tweets = TweetsParser.ParseTweetsFromWeb(rawtweets, stopwords);
		    logger.log(Level.INFO, "-------parsed tweets size: " + tweets.size());
		    
<<<<<<< Upstream, based on origin/Corona
		    // TEST
		    for (ParsedTweet t : tweets) {
		    	logger.log(Level.INFO, t.toString());
		    	results += t.toString();
=======
		    for(ParsedTweet tweet: tweets ){
		    	logger.log(Level.INFO, "new tweet " +tweet.bagOfWords.toString());
		    	for(String word: tweet.bagOfWords) {
		    		ANEWEntry entry = _dic.getEntrybyWord(word);
		    		
		    		if(entry != null)
		    		{
		    			tweet.analyzer.addWord(entry);
		    			logger.log(Level.INFO, "HIT!" +word);
		    		}
		    	}
>>>>>>> 4be6731 added anew dicionary and sentiment analyzer
		    }
		    
		    logger.log(Level.INFO, "Size before "+tweets.size() );
		    ListIterator li = tweets.listIterator();
		    
		    while(li.hasNext()) {
		    	ParsedTweet tweet = (ParsedTweet)li.next();
		    	if(tweet.analyzer.getNumofValidWords() <2) {
		    		li.remove();
		    	}
		    }
		    
		    logger.log(Level.INFO, "Size after "+tweets.size() );
		    // TEST
//		    for (ParsedTweet t : tweets) {
//		    	results += t.toString();
//	
//		    }
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(results);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
