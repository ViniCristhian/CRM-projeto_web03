package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.TipoInteracao;
import com.crmpratu.crm_api.repository.TipoInteracaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TipoInteracaoService {

    private final TipoInteracaoRepository tipoInteracaoRepository;

    public TipoInteracaoService(TipoInteracaoRepository tipoInteracaoRepository) {
        this.tipoInteracaoRepository = tipoInteracaoRepository;
    }

    public TipoInteracao findTipoInteracaoById(Long id) {
        return tipoInteracaoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public TipoInteracao update(Long id, TipoInteracao tipoInteracao) {
        BeanUtils.copyProperties(tipoInteracao, findTipoInteracaoById(id), "id");
        return tipoInteracaoRepository.save(tipoInteracao);
    }

}
