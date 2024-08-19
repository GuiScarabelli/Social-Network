package com.example.socialnetworkapi.dto.auth;

import com.example.socialnetworkapi.enums.Role;

public record RegisterDto(String login, String email, String password, Role role){
}
