package com.bond.shaadi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartnerPreference {
    private List<String> states;
    private List<String> cities;
    private AgeCriteria ageCriteria;
    private List<String> costIncludes;
    private IncomeRange income;
    private boolean isVerified;
    private List<Education> education;
}
