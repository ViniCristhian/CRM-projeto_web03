package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.StatusTarefa;
import com.crmpratu.crm_api.repository.StatusTarefaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StatusTarefaService {

    private final StatusTarefaRepository statusTarefaRepository;

    public StatusTarefaService(StatusTarefaRepository statusTarefaRepository) {
        this.statusTarefaRepository = statusTarefaRepository;
    }

    public StatusTarefa findStatusTarefaById(Long id) {
        return statusTarefaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public StatusTarefa update(Long id, StatusTarefa statusTarefa) {
        BeanUtils.copyProperties(statusTarefa, findStatusTarefaById(id), "id");
        return statusTarefaRepository.save(statusTarefa);
    }

}
