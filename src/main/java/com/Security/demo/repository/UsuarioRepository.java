package com.Security.demo.repository;

import com.Security.demo.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByLogin(String login);

}
