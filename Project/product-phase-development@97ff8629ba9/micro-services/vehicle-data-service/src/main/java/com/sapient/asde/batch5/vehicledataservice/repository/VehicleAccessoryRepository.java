package com.sapient.asde.batch5.vehicledataservice.repository;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleAccessoryRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VehicleAccessoryRepository {
    @Value("#{@environment.getProperty('mongodbUrl')}")
    String mongodbUrl;
    public Boolean saveInDb(MultipartFile file, String userId) throws ServiceException, ParseException, IOException {
        byte[] bytes = file.getBytes();
        String completeData = new String(bytes);

        log.trace("this is {}", completeData);
        try (MongoClient mc = MongoClients.create(mongodbUrl);) 
        {
            MongoDatabase db = mc.getDatabase("mycarsolution");
            MongoCollection<Document> accessories = db.getCollection("vehicle_accessories");
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(completeData);
            Boolean check = true;
            for (Object o : a) {
                JSONObject accessory = (JSONObject) o;

                if (!(accessory.containsKey("price") && accessory.containsKey("name")
                        && accessory.containsKey("description") && accessory.containsKey("pictureUrls"))) {
                    check = false;
                    break;
                }
            }
            if (check) {
                for (Object o : a) {

                    JSONObject accessory = (JSONObject) o;
                    log.info("array is {}", accessory);

                    Document doc = Document.parse(accessory.toString());
                    doc.append("dealerId", userId);

                    accessories.insertOne(doc);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
