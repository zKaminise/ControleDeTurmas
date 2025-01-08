package com.example.ControleTurmas.Controllers;

import com.example.ControleTurmas.Dtos.LoginRequestDTO;
import com.example.ControleTurmas.Dtos.LoginResponseDTO;
import com.example.ControleTurmas.Entity.User;
import com.example.ControleTurmas.Exceptions.CredenciaisInvalidas;
import com.example.ControleTurmas.Repositorys.UserRepository;
import com.example.ControleTurmas.Security.JwtUtil;
import com.example.ControleTurmas.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UserService userService;

    // Endpoint para login
    @PostMapping("/login")
    @Operation(summary = "Fazer login no sistema", description = "Essa função é responsável por Fazer login no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario não encontrado")
    })
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new CredenciaisInvalidas("Usuário ou senha inválidos!");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponseDTO(token);
    }

//     Endpoint para cadastro de novo usuário
//    @PostMapping("/register")
//    @Operation(summary = "Registrar um novo usuario e senha", description = "Essa função é responsável por Registrar um novo usuario e senha")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", content = {
//                    @Content(schema = @Schema(implementation = User.class))
//            })
//    })
//    public ResponseEntity<String> register(@RequestBody User user) {
//        if (userService.isUsernameTaken(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Esse Usuário já existe!");
//        }
//        userService.registerUser(user);
//        return ResponseEntity.ok("Usuário registrado com sucesso!");
//    }
}
