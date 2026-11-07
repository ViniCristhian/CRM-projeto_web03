package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Interacao;
import com.crmpratu.crm_api.repository.InteracaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class InteracaoService {

    private final InteracaoRepository interacaoRepository;

    public InteracaoService(InteracaoRepository interacaoRepository) {
        this.interacaoRepository = interacaoRepository;
    }

    public Interacao findInteracaoById(Long id) {
        return interacaoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Interacao update(Long id, Interacao interacao) {
        BeanUtils.copyProperties(interacao, findInteracaoById(id), "id");
        return interacaoRepository.save(interacao);
    }

}
