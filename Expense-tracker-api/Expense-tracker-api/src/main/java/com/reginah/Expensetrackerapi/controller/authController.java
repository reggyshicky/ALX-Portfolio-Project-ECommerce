package com.reginah.Expensetrackerapi.controller;
import com.reginah.Expensetrackerapi.entity.User;
import com.reginah.Expensetrackerapi.entity.UserModel;
import com.reginah.Expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<String>("user is logged in", HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<User> add(@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}
