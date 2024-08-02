package com.bond.shaadi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Profile {
    private Self self;
    private List<Education> education;
    private ProfileImg profileImg;
    private Relation relation;
    private PartnerPreference partnerPreference;
}
