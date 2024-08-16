package com.example.socialnetworkapi.dto.auth;

import com.example.socialnetworkapi.enums.Role;

public record RegisterDto(String login, String password, Role role){
}
