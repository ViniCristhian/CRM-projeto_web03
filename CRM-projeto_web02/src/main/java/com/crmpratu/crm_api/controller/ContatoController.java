package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.Contato;
import com.crmpratu.crm_api.repository.ContatoRepository;
import com.crmpratu.crm_api.service.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	private final ContatoRepository contatoRepository;
	private final ContatoService contatoService;

	public ContatoController(ContatoRepository contatoRepository, ContatoService contatoService) {
		this.contatoRepository = contatoRepository;
		this.contatoService = contatoService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_CONTACT')")
	@GetMapping("/list")
	public List<Contato> findAll() {
		return contatoRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_CONTACT')")
	@GetMapping("/find/{id}")
	public ResponseEntity<Contato> findById(@PathVariable Long id) {
		return contatoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_CONTACT')")
	@PostMapping("/register")
	public Contato create(@Valid @RequestBody Contato contato) {
		return contatoRepository.save(contato);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_CONTACT')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id, @Valid @RequestBody Contato contato) {
		return ResponseEntity.ok(contatoService.update(id, contato));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_CONTACT')")
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		contatoRepository.deleteById(id);
	}

}
