package com.example.ControleTurmas.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "admin", description = "Usuario para Login")
    private String username;

    @Schema(example = "admin", description = "Senha para Login")
    private String password;

    private String resetPasswordToken;

    private LocalDateTime tokenExpirationTime;
}
