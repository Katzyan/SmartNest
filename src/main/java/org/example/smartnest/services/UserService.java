package org.example.smartnest.services;

import org.example.smartnest.entities.User;
import org.example.smartnest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void createUser(User user){
        userRepo.save(user);
    }
}
