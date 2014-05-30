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
import cs886.w14.proj.sentiment.ANEWDataViz;
import cs886.w14.proj.sentiment.ANEWDicWrapper;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.sentiment.TweetAnalysis;
import cs886.w14.proj.util.GsonSngleViewObj;
import cs886.w14.proj.util.Twitter4JDriver;
import twitter4j.Status;
import weka.core.Stopwords;
import cs886.w14.proj.util.Gaussian;;


public class TweetsVizJSPServlet extends HttpServlet {
	private static String STOPWORDS_FP = "./nlp/stopwords.txt";
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(TweetsVizJSPServlet.class.getName());
	private List<ParsedTweet> tweets, tweets2;
	
	public TweetsVizJSPServlet() {}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword").toString();
		String isCompareView = req.getParameter("compareview").toString();
		String results = keyword + "\n";
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
		
		// single view
		if(isCompareView == "false" || keyword2 == null) {
			logger.log(Level.INFO, "-------single view keyword = " + keyword);
			
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
			    results = ANEWDataViz.getSingleViewData(tweets);
			}
			
		// Compare View 
		} else {
			// get query results from twitter API
			Twitter4JDriver.getInstance().init();
		    ArrayList<Status> rawtweets1 = Twitter4JDriver.getInstance().getQueryResults(keyword);
		    ArrayList<Status> rawtweets2 = Twitter4JDriver.getInstance().getQueryResults(keyword2);
		    logger.log(Level.INFO, "-------revceived tweets (keyword1 =" + keyword + " with size = " + rawtweets1.size());
		    logger.log(Level.INFO, "-------revceived tweets (keyword2 =" + keyword2 + " with size = " + rawtweets2.size());
		    
		    tweets = TweetsParser.ParseTweetsFromWeb(rawtweets1, stopwords);
		    tweets2 = TweetsParser.ParseTweetsFromWeb(rawtweets2, stopwords);
		    results = ANEWDataViz.getCompareViewData(tweets, tweets2);
		}
		
		// pass results to front side
		logger.log(Level.INFO, "-------results = " + results);
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(results);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		// logger.log(Level.INFO, "-------done analysis = " + tweets.size() );
	}
	
	

}
