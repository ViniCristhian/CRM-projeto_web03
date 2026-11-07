package com.crmpratu.crm_api.repository;

import com.crmpratu.crm_api.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
