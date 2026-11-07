package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Contato;
import com.crmpratu.crm_api.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Contato findContatoById(Long id) {
        return contatoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Contato update(Long id, Contato contato) {
        BeanUtils.copyProperties(contato, findContatoById(id), "id");
        return contatoRepository.save(contato);
    }

}
