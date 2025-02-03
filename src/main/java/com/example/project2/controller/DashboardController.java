package com.example.project2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        List<String> userRoles = authentication.getAuthorities()
                                              .stream()
                                              .map(GrantedAuthority::getAuthority)
                                              .collect(Collectors.toList());
        model.addAttribute("userRoles", userRoles);
        return "dashboard";
    }
}
