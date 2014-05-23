package cs886.w14.proj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cs886.w14.proj.util.ParsedTweet;
import cs886.w14.proj.util.Twitter4JDriver;
import twitter4j.Status;


public class TweetsVizJSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(TweetsVizJSPServlet.class.getName());
	private List<ParsedTweet> tweets;
	
	public TweetsVizJSPServlet() { }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword").toString();
		String results = keyword;
		if (keyword.equals("")) {
			 results = "no input";
		} else {
			Twitter4JDriver.getInstance().init();
		    ArrayList<Status> rawtweets = Twitter4JDriver.getInstance().getQueryResults("keyword");
		    logger.log(Level.INFO, "-------revceived tweets size: " + rawtweets.size());
		    
		    List<ParsedTweet> tweets = ParsedTweet.ParseTweetsFromWeb(rawtweets);
		    for (ParsedTweet t : tweets) {
		    	results += t.toString();
		    }
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
