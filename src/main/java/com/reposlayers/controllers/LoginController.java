package com.reposlayers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reposlayers.models.User;
import com.reposlayers.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User u = userRepository.findByUsername(username);
            if (u != null) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    request.getSession().setAttribute("user", u);
                    response.sendRedirect("Dashboard.html");
                } else {
                    response.sendRedirect("index.html");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
