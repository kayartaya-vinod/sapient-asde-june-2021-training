package com.sapient.programs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddNewCustomer {

    public static void main(String[] args) {

        MongoClient mc = MongoClients.create();
        MongoDatabase db = mc.getDatabase("trainingdb");
        MongoCollection<Document> customers = db.getCollection("customers");

        Document c1 = new Document("name", "Kishore")
            .append("city", "Vasco")
            .append("email", "kishore@xmpl.com")
            .append("state", "Goa")
            .append("phone", "9876856767");

        customers.insertOne(c1);
        log.debug("{} inserted to customers collection", c1);

        mc.close();
    }
}
