package com.crmpratu.crm_api.security;

import com.crmpratu.crm_api.model.Perfil;
import com.crmpratu.crm_api.model.Usuario;
import com.crmpratu.crm_api.repository.PerfilRepository;
import com.crmpratu.crm_api.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public AppUserDetailsService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        return new org.springframework.security.core.userdetails.User(
                email,
                usuario.getSenha(),
                getPermissoes(usuario)
        );
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        if (usuario.getPerfil() == null || usuario.getPerfil().getId() == null) {
            return authorities;
        }

        Perfil perfil = perfilRepository.findById(usuario.getPerfil().getId()).orElse(usuario.getPerfil());
        if (perfil.getPermissoes() == null) {
            return authorities;
        }

        perfil.getPermissoes().forEach(p -> {
            if (p != null && p.getDescricao() != null) {
                authorities.add(new SimpleGrantedAuthority(p.getDescricao().trim().toUpperCase()));
            }
        });

        return authorities;
    }

}
