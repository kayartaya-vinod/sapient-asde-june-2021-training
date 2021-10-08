package com.sapient.programs;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class PrintProductAsJson {
    public static void main(String[] args) {
        MongoClient mc = MongoClients.create();
        MongoDatabase db = mc.getDatabase("trainingdb");
        MongoCollection<Document> products = db.getCollection("products");

        Bson filter = new BasicDBObject("_id", new ObjectId("611a3c7394dc9393ffb597dd"));
        Document pr = products.find(filter).first();
        System.out.println(pr.toJson());
    }
}
