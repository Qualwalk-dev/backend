package com.qualwalk.backend.response;

import com.qualwalk.backend.entity.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryAndCourse {

    private String category;
    private List<String> courses;
}
