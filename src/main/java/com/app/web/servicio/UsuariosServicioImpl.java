package com.app.web.servicio;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.web.dto.UsuariosRegistroDTO;
import com.app.web.modelos.Rol;
import com.app.web.modelos.Usuarios;
import com.app.web.repositorio.RolRepositorio;
import com.app.web.repositorio.UsuariosRepositorio;

@Service
public class UsuariosServicioImpl implements UsuariosServicio, UserDetailsService {

    private final UsuariosRepositorio usuariosRepositorio;
    private final RolRepositorio rolRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuariosServicioImpl(
            UsuariosRepositorio usuariosRepositorio,
            RolRepositorio rolRepositorio,
            BCryptPasswordEncoder passwordEncoder) {

        this.usuariosRepositorio = usuariosRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTRARSE
    @Override
    public Usuarios guardar(UsuariosRegistroDTO registroDTO) {

        // 👉 OBTENER ROL EXISTENTE (NO CREAR UNO NUEVO)
        Rol rolUser = rolRepositorio.findByNombre("ROLE_USER");

        Usuarios usuario = new Usuarios(
                registroDTO.getNombre(),
                registroDTO.getApellido(),
                registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                java.util.List.of(rolUser)
        );

        return usuariosRepositorio.save(usuario);
    }

    // LOGIN
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuarios usuario = usuariosRepositorio.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                mapearRoles(usuario.getRoles())
        );
    }

    // ROLES
    private Collection<? extends GrantedAuthority> mapearRoles(
            Collection<Rol> roles) {

        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
    }
}
