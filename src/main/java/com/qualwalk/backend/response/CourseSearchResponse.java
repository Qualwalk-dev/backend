package com.qualwalk.backend.response;

import com.qualwalk.backend.entity.*;
import com.swantech.lang.core.domain.*;

import java.util.*;

public class CourseSearchResponse extends BaseSearchApiResponse<CourseEntity> {

    public CourseSearchResponse() {
        super();
    }

    public CourseSearchResponse(Integer totalRecordCount) {
        super(totalRecordCount);
    }

    public CourseSearchResponse(List<CourseEntity> records, int recordsAsked) {
        super(records, recordsAsked);
    }
}
