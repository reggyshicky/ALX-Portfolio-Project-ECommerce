package com.reginah.ECommerce.services.auth;

import com.reginah.ECommerce.dto.SignupRequest;
import com.reginah.ECommerce.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}
