package com.qualwalk.backend.request;

import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

@Data
public class ValidateOtp {
    @NotNull
    private String username;

    @NotNull
    @Length(min = 6, max = 6)
    private String otp;
}
