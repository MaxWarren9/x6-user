package com.sds.x6_user.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean success;
    final String message;
}