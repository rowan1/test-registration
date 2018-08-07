package com.smartera3s.mongodb;

import com.mongodb.*;
import java.util.*;

/**
 * DataAcessManager Class.
 *
 * This class is an implementation of some of the MongoDB main functions ,in
 * java , aimed to insert new users into <customers> collection in our <demo>
 * database and also checking if credentials already existed in the database.
 *
 * @author ...
 * @version 1.3
 */
public class DataAcessManager{

    private static Mongo instance;
    private DBCollection collection;

    /**
     * Constructor.
     *
     * @param args (optional) should contain hostname and port number for MongoDB
     */
    private Mongo(String... args){
        MongoClient mongoClient;

        if (args.length == 0) {
            mongoClient = new MongoClient("localhost", 27017);
        } else {
            mongoClient = new MongoClient(args[0], Integer.parseInt(args[1]));
        }

        DB database = mongoClient.getDB("demo");
        collection = database.getCollection("customers");
    }

    /**
     * Singleton pattern restricts the instantiation of Mongo class and ensures
     * that only one instance of the database <demo> exists.
     *
     * @return Mongo instance
     */
    public static Mongo getInstance(){
        if (instance == null)
            instance = new Mongo();
        return instance;
    }

    /**
     * insert a document into MongoDB if key is available
     *
     * @param fields (required) the username picked by a new user upon sign-up
     * @return <true> if key is available
     */
    public boolean insert(Map<String,String> fields){
        if(check(fields)){
            BasicDBObject insertQuery = new BasicDBObject();

            for (Map.Entry<String, String> entry : fields.entrySet()) {
                insertQuery.put(entry.getKey(), entry.getValue());
            }

            collection.insert(insertQuery);

            return true;
        }else{
            return false;
        }
    }

    /**
     * delete all documents in MongoDB if a certain key
     *
     * @param fields (required) the username picked by a new user upon sign-up
     * @return <true> if documents deleted successfully
     */
    public boolean delete(Map<String,String> fields){
        if(!check(fields)){
            BasicDBObject deleteQuery = new BasicDBObject();

            for (Map.Entry<String, String> entry : fields.entrySet()) {
                deleteQuery.put(entry.getKey(), entry.getValue());
            }

            collection.remove(deleteQuery);

            return true;
        }else{
            return false;
        }
    }

    /**
     * check for username and password into MongoDB
     *
     * @param keys (required) a Hashmap containing the keys and values to be looked at.
     * @return <true> if the username and password match and exist in <customers> collection.
     */
    public Boolean check(Map<String,String> keys){
        ArrayList result          = new ArrayList();
        BasicDBObject searchQuery = new BasicDBObject();
        List<BasicDBObject> obj   = new ArrayList<BasicDBObject>();

        for (Map.Entry<String, String> entry : keys.entrySet()) {
            obj.add(new BasicDBObject(entry.getKey(), entry.getValue()));
        }

        searchQuery.put("$and",obj);
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            result.add(cursor.next());
        }

        if (result.size() != 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Main function used for testing
     *
     */
    public static void main(String[] args){
        Map<String, String> keys = new HashMap<String, String>();
        keys.put("username", "lol4");
        keys.put("password", "lol4");

        DataAcessManager mongo = DataAcessManager.getInstance();
        mongo.delete(keys);
    }
}

