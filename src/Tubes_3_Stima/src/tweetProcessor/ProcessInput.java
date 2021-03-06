package tweetProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patternMatching.BoyerMoore;
import patternMatching.KMP;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Twitter twitter = new TwitterFactory().getInstance();

		AccessToken accessToken = new AccessToken(
				"2203938000-ZyfZAtbh6plnWfOlPWR72fO2WOoVjNDYxnYYjZV",
				"qRDLkjI3A6utesh8l8cUByo324Dxbd5XzdSqZLpA54dIx");
		twitter.setOAuthConsumer("hciTMq0Aszf3Q8pCDoQ",
				"ktKeXWscyNJNi0Wus2XdRnUW9Vf8tU6gkQbUZBOcqY");
		twitter.setOAuthAccessToken(accessToken);
		List<Status> tweets = null;

		// Get data from index.jsp
		String keyword = request.getParameter("keyword");
		String selectedMode = request.getParameter("metode");
		String positif = request.getParameter("positif");
		String negatif = request.getParameter("negatif");

		// Save sentiments
		String[] sentimenPositif = positif.split(" ");
		String[] sentimenNegatif = negatif.split(" ");

		// Initialize algorithm method
		BoyerMoore bm = new BoyerMoore();
		KMP kmp = new KMP();

		// Initialize data for result.jsp
		ArrayList<Status> positiveResult = new ArrayList<Status>();
		ArrayList<Status> negativeResult = new ArrayList<Status>();
		ArrayList<String> positiveKeyword = new ArrayList<String>();
		ArrayList<String> negativeKeyword = new ArrayList<String>();

		try {
			Query query = new Query(keyword);
			query.setCount(100);
			QueryResult queryResult;
			queryResult = twitter.search(query);
			tweets = queryResult.getTweets();
			if (selectedMode.equals("BM")) {
				System.out.println("Boyer Moore selected");
				System.out
						.println("Number of analyzed tweets " + tweets.size());
				int temp = 1; // debug
				for (Status tweet : tweets) {
					boolean positiveStatus = false;
					String cleanTweet = tweet.getText().replaceAll("[^\\w\\s]",
							""); // Tweet tanpa simbol
					bm.setString(cleanTweet);
					System.out.println("Analyzed Tweet:\n" + temp +". " + cleanTweet); temp++;
					// Sentimen positif
					for (int i = 0; i < sentimenPositif.length; i++) {
						if (bm.run(sentimenPositif[i])) {
							positiveResult.add(tweet);
							positiveKeyword.add(sentimenPositif[i]); // Add keyword
							positiveStatus = true;
							break; // Langsung asumsi tweet positif
						}
					}
					// Sentimen negatif
					if (!positiveStatus) { // Kalo udah positif nggak mungkin jadi negatif
						for (int i = 0; i < sentimenNegatif.length; i++) {
							if (bm.run(sentimenNegatif[i])) {
								negativeResult.add(tweet);
								negativeKeyword.add(sentimenNegatif[i]); // Add keyword
								positiveStatus = false;
								break; // Langsung asumsi tweet negatif
							}
						}
					}
				}
			} else if (selectedMode.equals("KMP")){
				System.out.println("KMP mode selected");
				System.out
				.println("Number of analyzed tweets " + tweets.size());
				int temp = 1; // debug
				for (Status tweet : tweets) {
					boolean positiveStatus = false;
					String cleanTweet = tweet.getText().replaceAll("[^\\w\\s]",
							""); // Tweet tanpa simbol
					System.out.println("Analyzed Tweet:\n" + temp +". " + cleanTweet); temp++;
					// Sentimen positif
					for (int i = 0; i < sentimenPositif.length; i++) {
						if (KMP.KMPMatch(cleanTweet, sentimenPositif[i]) != -1) {
							positiveResult.add(tweet);
							positiveKeyword.add(sentimenPositif[i]); // Add keyword
							positiveStatus = true;
							break; // Langsung asumsi tweet positif
						}
					}
					// Sentimen negatif
					if (!positiveStatus) { // Kalo udah positif nggak mungkin jadi negatif
						for (int i = 0; i < sentimenNegatif.length; i++) {
							if (KMP.KMPMatch(cleanTweet, sentimenPositif[i]) != -1) {
								negativeResult.add(tweet);
								negativeKeyword.add(sentimenNegatif[i]); // Add keyword
								positiveStatus = false;
								break; // Langsung asumsi tweet negatif
							}
						}
					}
				}
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("positiveResult", positiveResult);
		request.setAttribute("negativeResult", negativeResult);
		request.setAttribute("positiveKeyword", positiveKeyword);
		request.setAttribute("negativeKeyword", negativeKeyword);
		request.setAttribute("mode", selectedMode);
		System.out.println("Finished packing");
		RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
		view.forward(request, response);
		System.out.println("Finished send");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
