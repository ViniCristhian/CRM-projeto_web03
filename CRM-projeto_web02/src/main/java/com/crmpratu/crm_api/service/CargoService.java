package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Cargo;
import com.crmpratu.crm_api.repository.CargoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo findCargoById(Long id) {
        return cargoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Cargo update(Long id, Cargo cargo) {
        BeanUtils.copyProperties(cargo, findCargoById(id), "id");
        return cargoRepository.save(cargo);
    }

}
