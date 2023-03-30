package com.qualwalk.backend.response;

import com.qualwalk.backend.entity.*;
import com.swantech.lang.core.domain.*;

import java.util.*;

public class EnquirySearchResponse extends BaseSearchApiResponse<EnquiryEntity> {
    public EnquirySearchResponse() {
        super();
    }

    public EnquirySearchResponse(Integer totalRecordCount) {
        super(totalRecordCount);
    }

    public EnquirySearchResponse(List<EnquiryEntity> records, int recordsAsked) {
        super(records, recordsAsked);
    }
}
