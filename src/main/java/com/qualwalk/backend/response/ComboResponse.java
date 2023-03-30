package com.qualwalk.backend.response;

import com.qualwalk.backend.entity.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class ComboResponse {
    private CombosHeaderEntity combo;
    private List<CourseEntity> courses;
}
