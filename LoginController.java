package com.dhivya.dhivyamart;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public void login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            HttpSession session,
            HttpServletResponse response) throws IOException {

        User user = userService.loginUser(email, password);

        if (user == null) {
            response.sendRedirect("/?error=invalid");
            return;
        }

        if (!user.getRole().equalsIgnoreCase(role)) {
            response.sendRedirect("/?error=role");
            return;
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getName());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getRole());

        if (user.getRole().equalsIgnoreCase("BUYER")) {
            response.sendRedirect("/products-page");
        }
        else if (user.getRole().equalsIgnoreCase("SELLER")) {
            response.sendRedirect("/seller-products");
        }
        else if (user.getRole().equalsIgnoreCase("ADMIN")) {
            response.sendRedirect("/admin");
        }
    }
}