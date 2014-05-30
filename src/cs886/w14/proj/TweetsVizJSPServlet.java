package cs886.w14.proj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cs886.w14.proj.nlp.ParsedTweet;
import cs886.w14.proj.nlp.TweetsParser;
import cs886.w14.proj.sentiment.ANEWDicWrapper;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.sentiment.TweetAnalysis;
import cs886.w14.proj.util.GsonObj;
import cs886.w14.proj.util.Twitter4JDriver;
import twitter4j.Status;
import weka.core.Stopwords;
import cs886.w14.proj.util.Gaussian;;


public class TweetsVizJSPServlet extends HttpServlet {
	private static String STOPWORDS_FP = "./nlp/stopwords.txt";
	private static final long serialVersionUID = 1L;
	private static ANEWDicWrapper _dic = new ANEWDicWrapper("anew/ANEW2010All.txt");
	private static ANEWDicWrapper _emoticon_dic = new ANEWDicWrapper("anew/ANEWEmoticons.txt");
	
	private final Logger logger = Logger.getLogger(TweetsVizJSPServlet.class.getName());
	private List<ParsedTweet> tweets;
	
	public TweetsVizJSPServlet() {}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword").toString();
		// String isCompareView = req.getParameter("isCompareView").toString();
		String keyword2 = null;
		try {
			keyword2 = req.getParameter("keyword2").toString();
		} catch (Exception e) {
			logger.log(Level.INFO, "-------No second keyword");
		}
		Stopwords stopwords = new Stopwords();
		try {
			stopwords.read(STOPWORDS_FP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(keyword2 == null) {
			String results = keyword + "\n";
			if (keyword.equals("")) {
				 results = "no input";
			} else if (stopwords.is(keyword)) {
				results = "keyword belong to the list of stopword, please enter another keyword to avoid meaningless serach query, thank you!";
			} else {
				// get query results from twitter API
				Twitter4JDriver.getInstance().init();
			    ArrayList<Status> rawtweets = Twitter4JDriver.getInstance().getQueryResults(keyword);
			    logger.log(Level.INFO, "-------revceived tweets size = " + rawtweets.size());
			    tweets = TweetsParser.ParseTweetsFromWeb(rawtweets, stopwords);
			    
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

			    // TEST
			    List<GsonObj> objList = new ArrayList<GsonObj>();
			    for (ParsedTweet t : tweets) {
			    	objList.add(t.getGsonObj());
			    }
			    results = GsonObj.GsonFormatter(objList);
			}
			logger.log(Level.INFO, "-------results = " + results);
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(results);
		} else {
			logger.log(Level.INFO, "-------results = " + keyword2);
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		// logger.log(Level.INFO, "-------done analysis = " + tweets.size() );
	}

}
