package com.dhivya.dhivyamart;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String password, String role) {
        String encryptedPassword = encoder.encode(password);

        User user = new User(
                name,
                email,
                encryptedPassword,
                role
        );

        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}