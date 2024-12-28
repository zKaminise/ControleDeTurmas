package com.example.ControleTurmas.Controllers;

import com.example.ControleTurmas.Dtos.LoginRequestDTO;
import com.example.ControleTurmas.Dtos.LoginResponseDTO;
import com.example.ControleTurmas.Entity.User;
import com.example.ControleTurmas.Repositorys.UserRepository;
import com.example.ControleTurmas.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint para login
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas!");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponseDTO(token);
    }

    // Endpoint para cadastro de novo usuário
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
