package com.qualwalk.backend.entity;

import com.fasterxml.jackson.annotation.*;
import com.qualwalk.backend.enumeration.*;
import com.swantech.lang.core.domain.*;
import lombok.*;

import java.time.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnquiryEntity implements SearchEntity {

    @JsonIgnore
    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private LearningModes learningModes;
    private String message;
    private LocalDateTime createdOn;

    @JsonIgnore
    private int totalRecordsCount;

    @Override
    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }

}
