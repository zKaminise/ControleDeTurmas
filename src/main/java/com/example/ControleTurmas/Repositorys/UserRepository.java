package com.example.ControleTurmas.Repositorys;

import com.example.ControleTurmas.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
