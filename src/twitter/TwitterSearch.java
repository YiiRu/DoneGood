package twitter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.Iterator;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

//Download tweets to MongoDB datasbase

public final class TwitterSearch {
	
	public DBCollection collection;
	public Mongo mongo;
	public int count = 1;
	
	public void LinkMongodb() throws Exception{
		mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("TwitterMe");
		collection = db.getCollection("DreamD");
		System.out.println("Link MongoDB!");
		
	}
	
	public static void main(String[] args) throws TwitterException, FileNotFoundException, IOException{
		
//		String tweets1= "";
//		String filePath = "D:\\Play\\test1.json";
//		FileWriter fw = new FileWriter(filePath);
//		BufferedWriter bw = new BufferedWriter(fw);
		
		final TwitterSearch pr = new TwitterSearch();
		
		try {
			pr.LinkMongodb();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("JvlCyzmBW4sKwe90iu96eHB9Z")
	      .setOAuthConsumerSecret("t47lL7YHlMYRmJ7PSaGdDG9uFm0LGh0asEzLICZ7aXlBvcZkSp")
	      .setOAuthAccessToken("1042083097972559872-Pqn5KB7XLjukr26EgSFrywo92QQ4lk")
	      .setOAuthAccessTokenSecret("fu5yQ53gzpHyr8tE6id1ULdUF0H8negW2nDn1xqMvWlxj");
		cb.setJSONStoreEnabled(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
	    twitter4j.Twitter twitter = tf.getInstance();
	    
	    
        Query query = new Query("Earthquake");
        QueryResult result;
        
            do {
                result = twitter.search(query);
                
                List<Status> tweets = result.getTweets();
                
                for (Status tweet : tweets) {
//                	tweets1 = tweets1+tweet.getUser().getScreenName() + " - " + tweet.getText();
//                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
//                    bw.write(tweets1);
                	String str = DataObjectFactory.getRawJSON(tweet);
                	System.out.println(str);
                	try {
                		DBObject dbObject = (DBObject)JSON.parse(str);
                		pr.collection.save(dbObject);
                	} catch (Exception e){
                		e.printStackTrace();
                	}
                }
            } while ((query = result.nextQuery()) != null);
            
            Paging paging = new Paging(15, 200);
            List<Status> statuses = twitter.getUserTimeline("Earthquake", paging);
            
            Iterator it = statuses.iterator();
            
            while(it.hasNext()){
            	Status value = (Status)it.next();
            	String str = DataObjectFactory.getRawJSON(value);
            	try{
            		DBObject dbObject = (DBObject)JSON.parse(str);
            		pr.collection.save(dbObject);
            	} catch (Exception e){
            		e.printStackTrace();
            	}
            	System.out.println(str);
            }  
            pr.mongo.close();
        } 
		
	}
	
