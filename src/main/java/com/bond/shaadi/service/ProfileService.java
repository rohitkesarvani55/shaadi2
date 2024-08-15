package com.bond.shaadi.service;

import com.bond.shaadi.pojo.Profile;
import com.bond.shaadi.pojo.image.ProfileImage;
import com.bond.shaadi.respository.ProfileRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public void addProfile(Profile profile) {
        profileRepository.insertProfile(profile);
    }

    public void storeImage(MultipartFile file) throws IOException {
        ProfileImage image = new ProfileImage();
        image.setName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());

        profileRepository.saveProfileImage(image);
    }

    public ProfileImage getImageById(String name) {
        return profileRepository.getProfileImageByName(name);
    }
}