package com.dhivya.dhivyamart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

private final UserService userService;

public RegisterController(UserService userService) {
    this.userService = userService;
}

@GetMapping("/register")
public String registerPage() {
    return "register";
}

@GetMapping("/register-user")
public String registerUser(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String role) {

    userService.registerUser(name, email, password, role);

    return "redirect:/";
}

}
