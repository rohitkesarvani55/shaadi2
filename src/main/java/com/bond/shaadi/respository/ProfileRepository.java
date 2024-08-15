package com.bond.shaadi.respository;

import com.bond.shaadi.pojo.Image;
import com.bond.shaadi.pojo.Profile;
import com.bond.shaadi.pojo.image.ProfileImage;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private MongoCollection<ProfileImage> getImageCollection() {
        return getDatabase().getCollection("profileImage", ProfileImage.class);
    }

    public void insertProfile(Profile profile) {
        getCollection().insertOne(profile);
    }

    public void saveProfileImage(ProfileImage img) {
        getImageCollection().insertOne(img);
    }

    public ProfileImage getProfileImageByName(String name) {
        Document query = new Document("name", name);

        // Execute the query and retrieve results
        List<ProfileImage> images = new ArrayList<>();
        try (MongoCursor<ProfileImage> cursor = getImageCollection().find(query).iterator()) {
            while (cursor.hasNext()) {
                images.add(cursor.next());
            }
        }
        return images.get(0);
    }

}
