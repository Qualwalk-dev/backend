package com.qualwalk.backend.entity;

import com.qualwalk.backend.enumeration.*;
import lombok.*;

import javax.persistence.*;

@Data
public class CourseCategoryEntity {

    private CourseCategory category;

    private String description;
}
