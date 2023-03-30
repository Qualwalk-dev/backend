package com.qualwalk.backend.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.qualwalk.backend.enumeration.*;
import com.swantech.lang.core.domain.*;
import lombok.*;

import javax.persistence.*;
import java.math.*;
import java.time.*;

@Data
public class CourseEntity implements SearchEntity {

    private String course;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal finalPrice;
    private BigDecimal discount;
    private CourseCategory courseCategory;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate nextBatchDate;

    private byte[] image;
    private String imageName;
    private BigDecimal imageSize;
    private String imageContentType;

    @JsonIgnore
    private int totalRecordsCount;

    @Override
    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }
}
