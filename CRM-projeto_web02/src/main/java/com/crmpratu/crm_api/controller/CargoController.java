package com.crmpratu.crm_api.controller;

import com.crmpratu.crm_api.model.Cargo;
import com.crmpratu.crm_api.repository.CargoRepository;
import com.crmpratu.crm_api.service.CargoService;
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
@RequestMapping("/cargo")
public class CargoController {

    private final CargoRepository cargoRepository;
    private final CargoService cargoService;

    public CargoController(CargoRepository cargoRepository, CargoService cargoService) {
        this.cargoRepository = cargoRepository;
        this.cargoService = cargoService;
    }

    @PreAuthorize("hasAuthority('ROLE_SEARCH_POSITION')")
    @GetMapping("/list")
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_SEARCH_POSITION')")
    @GetMapping("/find/{id}")
    public ResponseEntity<Cargo> findById(@PathVariable Long id) {
        return cargoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ROLE_REGISTER_POSITION')")
    @PostMapping("/register")
    public Cargo create(@Valid @RequestBody Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @PreAuthorize("hasAuthority('ROLE_REGISTER_POSITION')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Cargo> update(@PathVariable Long id, @Valid @RequestBody Cargo cargo) {
        return ResponseEntity.ok(cargoService.update(id, cargo));
    }

    @PreAuthorize("hasAuthority('ROLE_REMOVE_POSITION')")
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id) {
        cargoRepository.deleteById(id);
    }
}
