package com.qualwalk.backend.enumeration;

import com.swantech.lang.core.utility.*;


public enum CourseCategory implements IEnumWithOrdinal<String> {
    PROGRAMMING("PRGRMMNG"),
    DEVELOPMENT("DVLPMENT"),
    TESTING("TESTING"),
    CLOUD_COMPUTING("CLDCOMPT"),
    DATA_SCIENCE("DATASCNC"),
    CYBER_SECURITY("CYBRSECT"),
    DEVOPS("DEVOPS"),
    AGILE("AGILE"),
    SOFTWARE_TOOLS("SOFTTOOL");

    private String ordinal;

    CourseCategory(String ordinal) {
        this.ordinal = ordinal;
    }


    @Override
    public String getOrdinal() {
        return ordinal;
    }
}
