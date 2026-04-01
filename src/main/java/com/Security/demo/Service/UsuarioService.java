package com.Security.demo.Service;

import com.Security.demo.model.UsuarioEntity;
import com.Security.demo.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public abstract class UsuarioService implements UserDetailsService {
    private final UsuarioRepository repository;

    protected UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsuarioEntity usuarioEntity = repository.findByLogin(username);
        if(usuarioEntity == null){
            throw new UsernameNotFoundException("Usuario não foi encontrado");
        }

        return User.builder()
                .username(usuarioEntity.getLogin())
                .password(usuarioEntity.getSenha())
                .roles(usuarioEntity.getRole())
                .build();
    }
}
