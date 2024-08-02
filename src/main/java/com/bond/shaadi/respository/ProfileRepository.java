package com.bond.shaadi.respository;

import com.bond.shaadi.pojo.Profile;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {

    @Autowired
    MongoClient mongoClient;

    private MongoDatabase getDatabase() {
        return mongoClient.getDatabase("matrimonial");
    }

    private MongoCollection<Profile> getCollection() {
        return getDatabase().getCollection("profile", Profile.class);
    }

    public void insertProfile(Profile profile) {
        getCollection().insertOne(profile);
    }
}
