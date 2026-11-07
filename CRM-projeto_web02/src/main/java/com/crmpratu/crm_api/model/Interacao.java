package com.crmpratu.crm_api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "INTERACOES")
public class Interacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_interacao")
	private Long id;

	@Column(name = "data_interacao")
	private LocalDateTime dataInteracao;

	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_tipo_interacao")
	@JsonIgnoreProperties("interacoes")
	private TipoInteracao tipoInteracao;

	@ManyToOne
	@JoinColumn(name = "id_contato")
	@JsonIgnoreProperties("interacoes")
	private Contato contato;

	public Interacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataInteracao() {
		return dataInteracao;
	}

	public void setDataInteracao(LocalDateTime dataInteracao) {
		this.dataInteracao = dataInteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoInteracao getTipoInteracao() {
		return tipoInteracao;
	}

	public void setTipoInteracao(TipoInteracao tipoInteracao) {
		this.tipoInteracao = tipoInteracao;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
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
		Interacao other = (Interacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
