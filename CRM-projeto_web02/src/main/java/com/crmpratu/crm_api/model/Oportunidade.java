package com.crmpratu.crm_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "OPORTUNIDADES")
public class Oportunidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_oportunidade")
	private Long id;

	@NotBlank(message = "O nome do negócio é obrigatório")
	@Column(name = "nome_negocio")
	private String nomeNegocio;

	@NotNull
	private BigDecimal valor;

	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	@Column(name = "data_fechamento")
	private LocalDate dataFechamento;

	@ManyToOne
	@JoinColumn(name = "id_estagio")
	@JsonIgnoreProperties("oportunidades")
	private EstagioVenda estagioVenda;

	@ManyToOne
	@JoinColumn(name = "id_conta")
	@JsonIgnoreProperties("oportunidades")
	private Conta conta;

	@OneToMany(mappedBy = "oportunidade")
	@JsonIgnoreProperties("oportunidade")
	private List<Tarefa> tarefas;

	public Oportunidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeNegocio() {
		return nomeNegocio;
	}

	public void setNomeNegocio(String nomeNegocio) {
		this.nomeNegocio = nomeNegocio;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public EstagioVenda getEstagioVenda() {
		return estagioVenda;
	}

	public void setEstagioVenda(EstagioVenda estagioVenda) {
		this.estagioVenda = estagioVenda;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
