package com.bond.shaadi.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relation {
    private String fatherName;
    private String fatherOccupation;
    private String motherName;
    private String motherOccupation;
    private String grandfatherName;
    private String grandMotherName;
    private FamilyIncomeRange familyIncomeRange;

}
