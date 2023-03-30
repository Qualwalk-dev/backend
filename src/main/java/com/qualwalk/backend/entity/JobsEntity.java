package com.qualwalk.backend.entity;

import lombok.*;

@Data
public class JobsEntity {

    private Integer id;
    private String designation;
    private String location;
    private Integer minYears;
    private Integer maxYears;
    private String description;

}
