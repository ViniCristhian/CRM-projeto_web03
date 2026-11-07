package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Tarefa;
import com.crmpratu.crm_api.repository.TarefaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa findTarefaById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Tarefa update(Long id, Tarefa tarefa) {
        BeanUtils.copyProperties(tarefa, findTarefaById(id), "id");
        return tarefaRepository.save(tarefa);
    }

}
