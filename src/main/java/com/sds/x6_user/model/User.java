package com.sds.x6_user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.x6_user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserStatus status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate updatedAt;
}
