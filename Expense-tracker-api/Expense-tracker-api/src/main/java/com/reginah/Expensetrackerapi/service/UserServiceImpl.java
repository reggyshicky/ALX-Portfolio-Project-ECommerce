package com.reginah.Expensetrackerapi.service;

import com.reginah.Expensetrackerapi.entity.User;
import com.reginah.Expensetrackerapi.entity.UserModel;
import com.reginah.Expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.reginah.Expensetrackerapi.exceptions.ResourceNotFoundException;
import com.reginah.Expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserModel user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new ItemAlreadyExistsException("User is already registered with email:"+user.email());
        }
        var newUser = User.builder()
                .name(user.name())
                .password(passwordEncoder.encode(user.password()))
                .age(user.age())
                .email(user.email())
                .build();
//        User newUser = new User();
//        BeanUtils.copyProperties(user, newUser);

        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id:"+id));
    }

    @Override
    public User updateUser(UserModel user, Long id) {
        User existingUser = readUser(id);
        existingUser.setName(user.name() != null ? user.name() : existingUser.getName());
        existingUser.setPassword(user.email() != null ? user.email() : existingUser.getEmail());
        existingUser.setPassword(user.password() != null ? user.password() : existingUser.getPassword());
        existingUser.setAge(user.age() != null ? user.age() : existingUser.getAge());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = readUser(id);
        userRepository.delete(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No user found!"));
    }


}
