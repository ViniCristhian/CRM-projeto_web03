package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.EstagioVenda;
import com.crmpratu.crm_api.repository.EstagioVendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstagioVendaService {

    private final EstagioVendaRepository estagioVendaRepository;

    public EstagioVendaService(EstagioVendaRepository estagioVendaRepository) {
        this.estagioVendaRepository = estagioVendaRepository;
    }

    public EstagioVenda findEstagioVendaById(Long id) {
        return estagioVendaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public EstagioVenda update(Long id, EstagioVenda estagioVenda) {
        BeanUtils.copyProperties(estagioVenda, findEstagioVendaById(id), "id");
        return estagioVendaRepository.save(estagioVenda);
    }

}
