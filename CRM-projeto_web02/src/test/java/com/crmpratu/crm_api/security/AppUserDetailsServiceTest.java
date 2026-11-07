package com.crmpratu.crm_api.security;

import com.crmpratu.crm_api.model.Perfil;
import com.crmpratu.crm_api.model.Permissao;
import com.crmpratu.crm_api.model.Usuario;
import com.crmpratu.crm_api.repository.PerfilRepository;
import com.crmpratu.crm_api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PerfilRepository perfilRepository;

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @Test
    void shouldLoadAuthoritiesFromPersistedProfilePermissions() {
        Usuario usuario = new Usuario();
        usuario.setEmail("frontend.teste@crm.local");
        usuario.setSenha("Teste1234");

        Perfil perfil = new Perfil(1L);
        Permissao permissao = new Permissao();
        permissao.setId(3L);
        permissao.setDescricao("ROLE_SEARCH_USER");
        perfil.setPermissoes(List.of(permissao));
        usuario.setPerfil(perfil);

        when(usuarioRepository.findByEmail("frontend.teste@crm.local")).thenReturn(Optional.of(usuario));
        when(perfilRepository.findById(1L)).thenReturn(Optional.of(perfil));

        UserDetails userDetails = appUserDetailsService.loadUserByUsername("frontend.teste@crm.local");

        assertThat(userDetails.getAuthorities())
                .extracting(authority -> authority.getAuthority())
                .contains("ROLE_SEARCH_USER");
    }
}
