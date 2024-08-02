package com.bond.shaadi.service;

import com.bond.shaadi.pojo.Profile;
import com.bond.shaadi.respository.ProfileRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public void addProfile(Profile profile) {
        profileRepository.insertProfile(profile);
    }
}