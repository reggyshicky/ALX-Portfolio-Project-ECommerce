package com.reginah.ECommerce.services.auth;

import com.reginah.ECommerce.dto.SignupRequest;
import com.reginah.ECommerce.dto.UserDto;
import com.reginah.ECommerce.enums.UserRole;
import com.reginah.ECommerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.reginah.ECommerce.entity.User;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);

        if (null == adminAccount) {
            User user = new User();
        user.setEmail("reggy@gmail.com");
        user.setName("admin");
        user.setRole(UserRole.ADMIN);
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userRepository.save(user);
        }
    }

}
