package twitter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

//Download tweets to a file  

public class Search {
	
	public static void main(String[] args) throws TwitterException, FileNotFoundException, IOException{
		
		String tweets1= "";
		String filePath = "D:\\Play\\test1.json";
		FileWriter fw = new FileWriter(filePath);
		BufferedWriter bw = new BufferedWriter(fw);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("JvlCyzmBW4sKwe90iu96eHB9Z")
	      .setOAuthConsumerSecret("t47lL7YHlMYRmJ7PSaGdDG9uFm0LGh0asEzLICZ7aXlBvcZkSp")
	      .setOAuthAccessToken("1042083097972559872-Pqn5KB7XLjukr26EgSFrywo92QQ4lk")
	      .setOAuthAccessTokenSecret("fu5yQ53gzpHyr8tE6id1ULdUF0H8negW2nDn1xqMvWlxj");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
	    twitter4j.Twitter twitter = tf.getInstance();
	    
	    try {
//          Query query = new Query(args[0]);
            Query query = new Query("#Earthquake");
            QueryResult result;
            do {
                result = twitter.search(query);
                
                List<Status> tweets = result.getTweets();
                
                for (Status tweet : tweets) {
                	tweets1 = tweets1+tweet.getUser().getScreenName() + " - " + tweet.getText();
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    bw.write(tweets1);
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	}
	

}
