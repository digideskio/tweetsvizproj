package cs886.w14.proj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cs886.w14.proj.util.Twitter4J;
import twitter4j.Status;


public class TweetsVizJSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(TweetsVizJSPServlet.class.getName());
	
	public TweetsVizJSPServlet() { }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword").toString();
		String results = keyword;
		if (keyword.equals("")) {
			 results = "no input";
		} else {
			Twitter4J.getInstance().init();
		    ArrayList<Status> tweets = Twitter4J.getInstance().getQueryResults("keyword");
		    logger.log(Level.INFO, "-------revceived tweets size: " + tweets.size());
		    results = tweets.toString();
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
