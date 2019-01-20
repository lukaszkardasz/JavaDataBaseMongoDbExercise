package pl;

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
        /*document.put("name","Who want to live forever");
        document.put("length",3.22);
        document.put("date","2019-01-19T00:00:00:000Z");
        songs.insert(document);*/

        //Definiujemy dokumenty do wyszukiwania
        BasicDBObject newValue = new BasicDBObject();
        newValue.put("lenght",10.10);

        //UPDATE OBIEKTU
        //dodajemuy nwy dokument bo wszystko co jest w zapytaniu pomiedzy {} nawiasami to obiekty-dokumenty
        BasicDBObject query = new BasicDBObject("name","Ona już nie tańczy dla mnie");
        BasicDBObject updateObj = new BasicDBObject("$set",newValue);
        WriteResult update = songs.update(query, updateObj);
        System.out.println(update);


        DBCursor allSongs = songs.find();
        System.out.println("List of songs");
        while(allSongs.hasNext()){
            System.out.println(allSongs.next());
        }
    }
}
