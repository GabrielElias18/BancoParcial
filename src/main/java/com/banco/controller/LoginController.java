package com.banco.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        // Aquí verificas el rol y rediriges
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRADOR"))) {
            return "redirect:/admin/cuentas"; // Redirige al dashboard de admin
        } else {
            return "redirect:/usuario/saldo"; // Redirige al home de usuario
        }
    }
}
