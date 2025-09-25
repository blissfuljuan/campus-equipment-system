package edu.cit.lo.joshuanoel.campusequipmentloan.controller;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.userEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/create")
    public userEntity createUser(@RequestBody userEntity user) {
        return userService.createUser(user);
    }

    @GetMapping("/all")
    public List<userEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public userEntity getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}/update")
    public userEntity updateUser(@PathVariable int userId, @RequestBody userEntity user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}/delete")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return "User deleted successfully!";
    }
}
