package edu.cit.angus.shayne.campusequipmentloan.controller;

import edu.cit.angus.shayne.campusequipmentloan.service.UserService;
import edu.cit.angus.shayne.campusequipmentloan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Optional<User> user = userService.authenticate(username, password);
        if (user.isPresent()) {
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role", user.get().getRole());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
