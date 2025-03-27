package org.example.smartnest.controllers;

import org.example.smartnest.dto.AuthRequest;
import org.example.smartnest.entities.User;
import org.example.smartnest.repositories.UserRepository;
import org.example.smartnest.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    public UserController() {
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
//        String display = "";
//        for(User user : users){
//            display += user.getEmail() + " ";
//        }
        return users;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        User user = userRepo.findByUsername(authRequest.getUsername());
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username");
        }
        if(!user.getPassword().equals(authRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, user.getUsername()));
    }

}

