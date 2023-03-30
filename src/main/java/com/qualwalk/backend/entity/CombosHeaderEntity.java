package com.qualwalk.backend.entity;

import lombok.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Data
public class CombosHeaderEntity {

    private String comboCode;
    private String comboDescription;
    private LocalDate nextBatchDate;
    private BigDecimal price;
    private List<ComboDetailEntity> courses;
    private byte[] image;
    private String imageName;
    private BigDecimal imageSize;
    private String imageContentType;

}
