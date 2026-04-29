package com.example.demo.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(String email, String password){
        User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(password, user.getPassword())){
            return "Login Successful";
        }else{
            throw new RuntimeException("Invalid Credentials");
        }
    }

}
