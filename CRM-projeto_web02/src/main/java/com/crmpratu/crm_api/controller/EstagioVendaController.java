package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.EstagioVenda;
import com.crmpratu.crm_api.repository.EstagioVendaRepository;
import com.crmpratu.crm_api.service.EstagioVendaService;
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
@RequestMapping("/estagio-venda")
public class EstagioVendaController {

	private final EstagioVendaRepository estagioVendaRepository;
	private final EstagioVendaService estagioVendaService;

	public EstagioVendaController(EstagioVendaRepository estagioVendaRepository, EstagioVendaService estagioVendaService) {
		this.estagioVendaRepository = estagioVendaRepository;
		this.estagioVendaService = estagioVendaService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_SALE_STAGE')")
	@GetMapping("/list")
	public List<EstagioVenda> findAll() {
		return estagioVendaRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_SALES_STAGE')")
	@GetMapping("/find/{id}")
	public ResponseEntity<EstagioVenda> findById(@PathVariable Long id) {
		return estagioVendaRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_SALES_STAGE')")
	@PostMapping("/register")
	public EstagioVenda create(@Valid @RequestBody EstagioVenda estagioVenda) {
		return estagioVendaRepository.save(estagioVenda);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_SALES_STAGE')")
	@PutMapping("/update/{id}")
	public ResponseEntity<EstagioVenda> update(@PathVariable Long id, @Valid @RequestBody EstagioVenda estagioVenda) {
		return ResponseEntity.ok(estagioVendaService.update(id, estagioVenda));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_SALES_STAGE')")
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		estagioVendaRepository.deleteById(id);
	}

}
