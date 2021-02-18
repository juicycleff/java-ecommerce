package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.dto.LoginRegisterDto;
import io.recruitment.assessment.api.models.Role;
import io.recruitment.assessment.api.models.User;
import io.recruitment.assessment.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody LoginRegisterDto cmd) {
        User user = new User(
                cmd.getUsername(),
                passwordEncoder.encode(cmd.getPassword()),
                Role.CUSTOMER
        );
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

