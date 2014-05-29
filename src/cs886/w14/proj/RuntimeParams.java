package cs886.w14.proj;


public class RuntimeParams {
	/**
	 * used when connecting to Twitter
	 */
	public static final String CONSUMER_KEY = "YVvMFidL5QtJier6MiSqEiDCK";
	public static final String CONSUMER_SECRET = "Ko9rZQxiG45qo38oDfJeL5obBZ9JoRoeVyLQLqytduH4E7mkeT";
	public static final String TOKEN = "938394698-4NrgDempnull8FETBYjyH9kvKfXdCSoQRTMBA7Lc";
	public static final String TOKEN_SECRET = "jE6xV22ffGieUhI2EIKqweWPf6WyMvx80Y0vYKzD1jA1f";
	
	/**
	 * maximum num of tweets within one query
	 */
	public static final int MAX_NUM_OF_TWEETS = 512;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	public static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	public static final int MIN_NUM_OF_VALID_WORDS = 2;
}
