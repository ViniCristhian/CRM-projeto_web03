package com.crmpratu.crm_api.repository;

import com.crmpratu.crm_api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
