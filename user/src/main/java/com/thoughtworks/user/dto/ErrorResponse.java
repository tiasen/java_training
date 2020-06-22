package com.thoughtworks.user.dto;

import lombok.Value;

@Value
public class ErrorResponse {
    int status;
    String message;
}
