package com.qualwalk.backend.request;

import lombok.*;

@Getter
@Setter
public class UserData {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String organization;
    private Integer yearsOfExperience;

}
