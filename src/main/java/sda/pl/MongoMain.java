package sda.pl;

import com.mongodb.*;


public class MongoMain {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDB("kurs");
        System.out.println("Databases");
        mongoClient.getDatabaseNames().forEach(System.out::println);
        System.out.println("Collections");
        database.getCollectionNames().forEach(System.out::println);

        DBCollection songs = database.getCollection("songs");
        BasicDBObject document = new BasicDBObject();
        //dodanie nowych piosenek do bazy
        document.put("name","Who want to live forever");
        document.put("length",3.22);
        document.put("date","2019-01-19T00:00:00:000Z");
        songs.insert(document);

        DBCursor allSongs = songs.find();
        System.out.println("List of songs");
        while(allSongs.hasNext()){
            System.out.println(allSongs.next());
        }
    }
}
