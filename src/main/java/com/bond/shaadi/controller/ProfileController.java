package com.bond.shaadi.controller;

import com.bond.shaadi.pojo.Image;
import com.bond.shaadi.pojo.Profile;
import com.bond.shaadi.pojo.image.ProfileImage;
import com.bond.shaadi.service.ProfileService;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postData(@RequestBody Profile body) {
        profileService.addProfile(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            profileService.storeImage(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{imgName}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String imgName) {
        ProfileImage imageOptional = profileService.getImageById(imgName);

        if (imageOptional != null) {
            ProfileImage image = imageOptional;

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                    .body(new ByteArrayResource(image.getData()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
