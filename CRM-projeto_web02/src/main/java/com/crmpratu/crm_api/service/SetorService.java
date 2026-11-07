package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Setor;
import com.crmpratu.crm_api.repository.SetorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor findSetorById(Long id) {
        return setorRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Setor update(Long id, Setor setor) {
        BeanUtils.copyProperties(setor, findSetorById(id), "id");
        return setorRepository.save(setor);
    }

}
