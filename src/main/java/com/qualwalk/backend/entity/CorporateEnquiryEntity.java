package com.qualwalk.backend.entity;

import lombok.*;

@Data
public class CorporateEnquiryEntity extends EnquiryEntity {

    private Boolean isCorporate;
    private String company;
    private String phone;
    private String jobTitle;

}
