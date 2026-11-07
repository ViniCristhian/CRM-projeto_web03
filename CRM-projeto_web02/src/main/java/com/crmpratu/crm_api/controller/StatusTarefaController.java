package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.StatusTarefa;
import com.crmpratu.crm_api.repository.StatusTarefaRepository;
import com.crmpratu.crm_api.service.StatusTarefaService;
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
@RequestMapping("/status-tarefa")
public class StatusTarefaController {

	private final StatusTarefaRepository statusTarefaRepository;
	private final StatusTarefaService statusTarefaService;

	public StatusTarefaController(StatusTarefaRepository statusTarefaRepository, StatusTarefaService statusTarefaService) {
		this.statusTarefaRepository = statusTarefaRepository;
		this.statusTarefaService = statusTarefaService;
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_TASK_STATUS')")
	@GetMapping("/list")
	public List<StatusTarefa> findAll() {
		return statusTarefaRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_SEARCH_TASK_STATUS')")
	@GetMapping("/find/{id}")
	public ResponseEntity<StatusTarefa> findById(@PathVariable Long id) {
		return statusTarefaRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_TASK_STATUS')")
	@PostMapping("/register")
	public StatusTarefa create(@Valid @RequestBody StatusTarefa statusTarefa) {
		return statusTarefaRepository.save(statusTarefa);
	}

	@PreAuthorize("hasAuthority('ROLE_REGISTER_TASK_STATUS')")
	@PutMapping("/update/{id}")
	public ResponseEntity<StatusTarefa> update(@PathVariable Long id, @Valid @RequestBody StatusTarefa statusTarefa) {
		return ResponseEntity.ok(statusTarefaService.update(id, statusTarefa));
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVE_TASK_STATUS')")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		statusTarefaRepository.deleteById(id);
	}

}
