package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.TipoInteracao;
import com.crmpratu.crm_api.repository.TipoInteracaoRepository;
import com.crmpratu.crm_api.service.TipoInteracaoService;
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
@RequestMapping("/tipo-interacao")
public class TipoInteracaoController {

	private final TipoInteracaoRepository tipoInteracaoRepository;
	private final TipoInteracaoService tipoInteracaoService;

	public TipoInteracaoController(TipoInteracaoRepository tipoInteracaoRepository, TipoInteracaoService tipoInteracaoService) {
		this.tipoInteracaoRepository = tipoInteracaoRepository;
		this.tipoInteracaoService = tipoInteracaoService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_INTERACTION_TYPE')")
	@GetMapping("/list")
	public List<TipoInteracao> findAll() {
		return tipoInteracaoRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_INTERACTION_TYPE')")
	@GetMapping("/find/{id}")
	public ResponseEntity<TipoInteracao> findById(@PathVariable Long id) {
		return tipoInteracaoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_INTERACTION_TYPE')")
	@PostMapping("/register")
	public TipoInteracao create(@Valid @RequestBody TipoInteracao tipoInteracao) {
		return tipoInteracaoRepository.save(tipoInteracao);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_INTERACTION_TYPE')")
	@PutMapping("/update/{id}")
	public ResponseEntity<TipoInteracao> update(@PathVariable Long id, @Valid @RequestBody TipoInteracao tipoInteracao) {
		return ResponseEntity.ok(tipoInteracaoService.update(id, tipoInteracao));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_INTERACTION_TYPE')")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoInteracaoRepository.deleteById(id);
	}

}
