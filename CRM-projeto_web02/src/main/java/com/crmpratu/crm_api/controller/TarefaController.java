package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.Tarefa;
import com.crmpratu.crm_api.repository.TarefaRepository;
import com.crmpratu.crm_api.service.TarefaService;
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
@RequestMapping("/tarefa")
public class TarefaController {

	private final TarefaRepository tarefaRepository;
	private final TarefaService tarefaService;

	public TarefaController(TarefaRepository tarefaRepository, TarefaService tarefaService) {
		this.tarefaRepository = tarefaRepository;
		this.tarefaService = tarefaService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_TASK')")
	@GetMapping("/list")
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_TASK')")
	@GetMapping("/find/{id}")
	public ResponseEntity<Tarefa> findById(@PathVariable Long id) {
		return tarefaRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_TASK')")
	@PostMapping("/register")
	public Tarefa create(@Valid @RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_TASK')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Tarefa> update(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		return ResponseEntity.ok(tarefaService.update(id, tarefa));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_TASK')")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
	}

}
