import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Trends;
import twitter4j.*;

public class TwitterAPI {
	
	public int MAX_TRENDS = 10;
	public String toptrends[] = new String[MAX_TRENDS];
	
	public TwitterAPI() throws TwitterException {
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		
		builder.setDebugEnabled(true);
		builder.setOAuthConsumerKey("Ofrvo54qwqNgImrOvmkeghnsE");
		builder.setOAuthConsumerSecret("fTMGUlG6UeXrWHkR4SDOmgM4TnM8wp4zn6DlpsAQyTxW0DhC8x");
		builder.setOAuthAccessToken("2457270114-t28so66E0iiBKnWXzDAkXszqojFNeix7gszeS6l");
		builder.setOAuthAccessTokenSecret("OZe8cJSjoHF7wvHGIE4wyDX41X68Kdlb4MFfLH66uZFfR");
		builder.setJSONStoreEnabled(true);
		
		TwitterFactory tf = new TwitterFactory(builder.build());
		Twitter twitter = tf.getInstance();
		
		Trends trends = twitter.getPlaceTrends(23424977); // You can change this number to change where the trends are pulled from. Currently it's set to 23424977 which is the united states.
		// gloabl woeID is 1.
		
		for(int counter = 0; counter < trends.getTrends().length; counter++)
		{
			if(counter == MAX_TRENDS)
			{
				break;
			}
			toptrends[counter] = trends.getTrends()[counter].getName();
		}
		
	}
	
	public String[] getTopTrends()
	{
		return toptrends;
	}

}
