package tweetProcessor;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patternMatching.BoyerMoore;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Servlet implementation class ProcessInput
 */
@WebServlet("/ProcessInput")
public class ProcessInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Twitter twitter = new TwitterFactory().getInstance();
		
		AccessToken accessToken = new AccessToken("2203938000-ZyfZAtbh6plnWfOlPWR72fO2WOoVjNDYxnYYjZV", "qRDLkjI3A6utesh8l8cUByo324Dxbd5XzdSqZLpA54dIx");
	    twitter.setOAuthConsumer("hciTMq0Aszf3Q8pCDoQ", "ktKeXWscyNJNi0Wus2XdRnUW9Vf8tU6gkQbUZBOcqY");
	    twitter.setOAuthAccessToken(accessToken);
	    List<Status> tweets = null;
	    
	    try {
	    	String q = request.getParameter("keyword");
	        Query query = new Query(q);
	        QueryResult result;
	        result = twitter.search(query);
	        tweets = result.getTweets();
	        for (Status tweet : tweets) {
	            //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	        }
	    }
	    catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
	    BoyerMoore bm = new BoyerMoore(tweets.get(1).getText(), "a");
	    System.out.println("Message :"+tweets.get(1).getText());
	    System.out.println("Sentiment :"+"a");
	    System.out.println("Output :"+bm.run());
	    request.setAttribute("result", tweets);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
