package com.qualwalk.backend.entity;

import lombok.*;

@Data
public class JobApplicationEntity {

    private Integer id;
    private String email;
    private String phoneNumber;
    private Integer jobId;

}
