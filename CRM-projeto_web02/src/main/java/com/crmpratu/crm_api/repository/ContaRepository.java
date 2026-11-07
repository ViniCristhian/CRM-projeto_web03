package com.crmpratu.crm_api.repository;

import com.crmpratu.crm_api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
