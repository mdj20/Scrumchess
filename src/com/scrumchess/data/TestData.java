package com.scrumchess.data;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;




public class TestData {
	
	final static String _kind = "testData";
	final static String _fen = "fen";
	final static String _date = "date";
	final static String _id = "id";
	
	// this is going to test the data
	public static void add(int id, String fen){
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		Entity td = new Entity(_kind);
		td.setProperty(_fen, fen);
		td.setProperty(_id,id);
		td.setProperty(_date, new Date());
		dss.put(td);
	}
	
	// query for all of the Stings
	public static List<String> getAllFen(){
		ArrayList<String> ret = new ArrayList<String>();
		
		return ret;
	}
	
	public static ArrayList<String> getAllWithID(int id){
		ArrayList<String> ret = new ArrayList<String>();
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(_kind).setFilter( new FilterPredicate(_id,FilterOperator.EQUAL, id));
		PreparedQuery pq = dss.prepare(q);
		
		StringBuilder sb = new StringBuilder();
		for ( Entity e : pq.asIterable()){
			ret.add(e.getProperty(_id).toString() +" "+e.getProperty(_fen));			
		}
		
		return ret;
	}
	
	
	

}
