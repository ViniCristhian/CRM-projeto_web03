package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Perfil;
import com.crmpratu.crm_api.model.Usuario;
import com.crmpratu.crm_api.repository.PerfilRepository;
import com.crmpratu.crm_api.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Usuario create(Usuario usuario) {
        Perfil perfilPadrao = perfilRepository.findById(4L)
                .orElseThrow(() -> new IllegalStateException("Perfil leitor não encontrado"));
        usuario.setPerfil(perfilPadrao);

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank())
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioSaved = findUsuarioById(id);

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioSaved.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        BeanUtils.copyProperties(usuario, usuarioSaved, "id", "senha");
        return usuarioRepository.save(usuarioSaved);
    }

    public void updateActiveProperty(Long id, Boolean ativo) {
        Usuario usuarioSaved = findUsuarioById(id);
        usuarioSaved.setAtivo(ativo);
        usuarioRepository.save(usuarioSaved);
    }

}
