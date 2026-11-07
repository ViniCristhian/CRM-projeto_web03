package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.Oportunidade;
import com.crmpratu.crm_api.repository.OportunidadeRepository;
import com.crmpratu.crm_api.service.OportunidadeService;
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
@RequestMapping("/oportunidade")
public class OportunidadeController {

	private final OportunidadeRepository oportunidadeRepository;
	private final OportunidadeService oportunidadeService;

	public OportunidadeController(OportunidadeRepository oportunidadeRepository, OportunidadeService oportunidadeService) {
		this.oportunidadeRepository = oportunidadeRepository;
		this.oportunidadeService = oportunidadeService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_OPPORTUNITY')")
	@GetMapping("/list")
	public List<Oportunidade> findAll() {
		return oportunidadeRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_OPPORTUNITY')")
	@GetMapping("/find/{id}")
	public ResponseEntity<Oportunidade> findById(@PathVariable Long id) {
		return oportunidadeRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_OPPORTUNITY')")
	@PostMapping("/register")
	public Oportunidade create(@Valid @RequestBody Oportunidade oportunidade) {
		return oportunidadeRepository.save(oportunidade);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_OPPORTUNITY')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Oportunidade> update(@PathVariable Long id, @Valid @RequestBody Oportunidade oportunidade) {
		return ResponseEntity.ok(oportunidadeService.update(id, oportunidade));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_OPPORTUNITY')")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		oportunidadeRepository.deleteById(id);
	}

}
