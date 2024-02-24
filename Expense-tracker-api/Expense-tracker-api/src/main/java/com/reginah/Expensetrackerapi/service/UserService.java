package com.reginah.Expensetrackerapi.service;

import com.reginah.Expensetrackerapi.entity.User;
import com.reginah.Expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser(Long id);

    User updateUser(UserModel user, Long id);

    void deleteUser(Long id);

    User findUserByEmail(String email);
}
