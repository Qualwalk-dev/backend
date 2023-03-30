package com.qualwalk.backend.criteria;

import com.qualwalk.backend.enumeration.*;
import com.swantech.lang.core.domain.*;
import lombok.*;

@Data
public class SearchCourseCriteria extends BaseSearchCriteria<CourseSortBy> {

    private String searchString;

}
