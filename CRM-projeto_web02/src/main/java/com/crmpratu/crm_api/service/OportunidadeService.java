package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Oportunidade;
import com.crmpratu.crm_api.repository.OportunidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OportunidadeService {

    private final OportunidadeRepository oportunidadeRepository;

    public OportunidadeService(OportunidadeRepository oportunidadeRepository) {
        this.oportunidadeRepository = oportunidadeRepository;
    }

    public Oportunidade findOportunidadeById(Long id) {
        return oportunidadeRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Oportunidade update(Long id, Oportunidade oportunidade) {
        BeanUtils.copyProperties(oportunidade, findOportunidadeById(id), "id");
        return oportunidadeRepository.save(oportunidade);
    }

}
