package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Conta;
import com.crmpratu.crm_api.repository.ContaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta findContaById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Conta update(Long id, Conta conta) {
        BeanUtils.copyProperties(conta, findContaById(id), "id");
        return contaRepository.save(conta);
    }

}
