package com.example.chatapp_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequestDto {
    @JsonProperty("username")
    private String userName;
    private String password;
}
