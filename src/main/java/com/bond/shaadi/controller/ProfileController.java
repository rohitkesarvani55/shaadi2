package com.bond.shaadi.controller;

import com.bond.shaadi.pojo.Profile;
import com.bond.shaadi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postData(@RequestBody Profile body) {
        profileService.addProfile(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }}
