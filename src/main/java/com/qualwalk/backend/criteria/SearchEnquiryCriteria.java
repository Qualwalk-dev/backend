package com.qualwalk.backend.criteria;

import com.qualwalk.backend.enumeration.*;
import com.swantech.lang.core.domain.*;
import lombok.*;

@Getter
@Setter
public class SearchEnquiryCriteria extends BaseSearchCriteria<EnquirySortBy> {
    private String searchString;
}
