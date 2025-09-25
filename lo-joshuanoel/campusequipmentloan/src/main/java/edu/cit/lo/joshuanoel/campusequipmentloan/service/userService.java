package edu.cit.lo.joshuanoel.campusequipmentloan.service;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.userEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.repository.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    private final userRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public userEntity createUser(userEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<userEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public userEntity getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public userEntity updateUser(int userId, userEntity updatedUser) {
        userEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}