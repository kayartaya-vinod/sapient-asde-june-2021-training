package com.sapient.programs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetMongodbInformation {

    public static void main(String[] args) {
        MongoClient mc = MongoClients.create(); // defaults 127.0.0.1:27017
        MongoDatabase db = mc.getDatabase("trainingdb");

        log.debug("DB {} has the following collections", db.getName());
        MongoIterable<String> collectionNames = db.listCollectionNames();

        for (String cn : collectionNames) {
            log.debug("{}", cn);
        }

        MongoCollection<Document> customers = db.getCollection("customers");
        for (Document c : customers.find()) {
            log.debug("{}", c.get("name", String.class));
        }
        mc.close();
    }
}
