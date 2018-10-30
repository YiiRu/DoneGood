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

//Download tweets to MongoDB datasbase using Streaming API

public final class TwitterSearchStream {
	
	public DBCollection collection;
	public Mongo mongo;
	public int count = 1;
	
	public void LinkMongodb() throws Exception{
		//Link Mongodb
		//Build a data named TwitterMe
		//Build a collection named DreamD 
		mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("TwitterMe");
		collection = db.getCollection("DreamE");
		System.out.println("Link MongoDB!");
	}
	
	public static void main(String[] args) throws TwitterException, FileNotFoundException, IOException{
		
		final TwitterSearchStream pr = new TwitterSearchStream();
		
		try {
			pr.LinkMongodb();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Authentication
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("JvlCyzmBW4sKwe90iu96eHB9Z")
	      .setOAuthConsumerSecret("t47lL7YHlMYRmJ7PSaGdDG9uFm0LGh0asEzLICZ7aXlBvcZkSp")
	      .setOAuthAccessToken("1042083097972559872-Pqn5KB7XLjukr26EgSFrywo92QQ4lk")
	      .setOAuthAccessTokenSecret("fu5yQ53gzpHyr8tE6id1ULdUF0H8negW2nDn1xqMvWlxj");
		cb.setJSONStoreEnabled(true);
		
		StatusListener listener = new StatusListener(){
			public void onStatus(Status status) {
				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText()); //print tweet text to console
				
				String str = DataObjectFactory.getRawJSON(status);
            	System.out.println(str);
            	try {
            		DBObject dbObject = (DBObject)JSON.parse(str);
            		pr.collection.save(dbObject);   //pr.collection.insert(dbObject);
            		System.out.println(dbObject);
            		pr.count++;
//            		if(pr.count>5) {
//            			pr.mongo.close();
//            			System.exit(0);
//            		}
            		System.out.println(pr.count);
            	} catch (Exception e){
            		e.printStackTrace();
            	}
			}
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + "upToStatusId:" + upToStatusId);
			}
			public void onStallWarning(StallWarning warning){
				System.out.println("Got stall warning:" + warning);
			}
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};
		
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		twitterStream.addListener(listener);
		twitterStream.sample();
		
		String[] trackArray;
//		String[] Track = {"IMDB", "movie", "film", "cinema"};
	    
        FilterQuery filter = new FilterQuery("Halloween2018");
//        filter.track(Track);
        twitterStream.filter(filter);
         
        //pr.mongo.close();
        } 
		
	}
	
