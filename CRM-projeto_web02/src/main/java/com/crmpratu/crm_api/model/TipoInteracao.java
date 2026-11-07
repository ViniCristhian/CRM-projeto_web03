package com.crmpratu.crm_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "TIPOS_INTERACAO")
public class TipoInteracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_interacao")
	private Long id;

	@NotBlank(message = "O tipo é obrigatório")
	@Column(name = "tipo", unique = true)
	private String tipo;

	private String descricao;

	@OneToMany(mappedBy = "tipoInteracao")
	@JsonIgnoreProperties("tipoInteracao")
	private List<Interacao> interacoes;

	public TipoInteracao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Interacao> getInteracoes() {
		return interacoes;
	}

	public void setInteracoes(List<Interacao> interacoes) {
		this.interacoes = interacoes;
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
		TipoInteracao other = (TipoInteracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
