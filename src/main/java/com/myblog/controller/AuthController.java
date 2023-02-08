package com.myblog.controller;


import com.myblog.entities.Roles;
import com.myblog.entities.User;
import com.myblog.payload.LoginDto;
import com.myblog.payload.SignUpDto;
import com.myblog.repositories.RoleRepository;
import com.myblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword())
        );
        //if invalid i.e. not present in db, stops here
        //else proceed to next line

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*
        //if jwt token generated
        String token=tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
         */

        return new ResponseEntity<>("User Signed in successfully", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // check if username present in DB
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already Taken", HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email already taken", HttpStatus.BAD_REQUEST);
        }
        //create new user
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passEncoder.encode(signUpDto.getPassword()));


        //to set role for the user
        Roles roles = roleRepo.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));  //to convert to Set
        userRepo.save(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }
}
