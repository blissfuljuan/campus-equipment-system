package edu.cit.laborada.johnjoseph.campusequipmentloan.controller;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.UserModel;
import edu.cit.laborada.johnjoseph.campusequipmentloan.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public record AuthRequest(String username, String password) {}

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        try {
            UserModel created = userService.register(user);
            return ResponseEntity.ok().body(
                    java.util.Map.of(
                            "message", "User registered successfully",
                            "username", created.getUsername(),
                            "roles", created.getRoles()
                    )
            );
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        boolean valid = userService.authenticate(req.username(), req.password());
        if (valid) {
            return ResponseEntity.ok(
                    java.util.Map.of(
                            "message", "Login successful",
                            "username", req.username()
                    )
            );
        } else {
            return ResponseEntity.status(401).body(
                    java.util.Map.of("error", "Invalid username or password")
            );
        }
    }
}