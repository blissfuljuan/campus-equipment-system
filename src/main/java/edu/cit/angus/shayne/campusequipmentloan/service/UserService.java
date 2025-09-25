package edu.cit.angus.shayne.campusequipmentloan.service;

import edu.cit.angus.shayne.campusequipmentloan.model.User;
import edu.cit.angus.shayne.campusequipmentloan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> maybe = repo.findByUsername(username);
        if (maybe.isPresent() && encoder.matches(rawPassword, maybe.get().getPassword())) {
            return maybe;
        }
        return Optional.empty();
    }

    public User register(String username, String rawPassword, String role) {
        User u = new User(username, encoder.encode(rawPassword), role);
        return repo.save(u);
    }
}
