package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.Usuario;
import com.crmpratu.crm_api.repository.UsuarioRepository;
import com.crmpratu.crm_api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioService = usuarioService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_USER')")
	@GetMapping("/list")
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@PostMapping("/register")
	public Usuario create(@Valid @RequestBody Usuario usuario) {
		return usuarioService.create(usuario);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.update(id, usuario));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_USER')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		usuarioService.updateActiveProperty(id, false);
	}

}
