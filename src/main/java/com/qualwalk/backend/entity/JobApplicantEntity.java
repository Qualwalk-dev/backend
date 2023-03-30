package com.qualwalk.backend.entity;

import lombok.*;

@Data
public class JobApplicantEntity {
    private String id;
    private String email;
    private String phoneNumber;
    private String designation;
    private String description;
}
