package com.qualwalk.backend.enumeration;

import com.swantech.lang.core.utility.*;
import lombok.*;

@AllArgsConstructor
public enum LearningModes implements IEnumWithOrdinal<String> {
    OFFLINE("OFFLINE"),
    ONLINE("ONLINE");

    private String ordinal;

    @Override
    public String getOrdinal() {
        return ordinal;
    }
}
