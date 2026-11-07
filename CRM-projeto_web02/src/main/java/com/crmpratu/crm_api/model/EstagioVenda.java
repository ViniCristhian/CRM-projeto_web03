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
@Table(name = "ESTAGIOS_VENDA")
public class EstagioVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estagio")
	private Long id;

	@NotBlank(message = "O estágio é obrigatório")
	@Column(name = "estagio", unique = true)
	private String estagio;

	private String descricao;

	@OneToMany(mappedBy = "estagioVenda")
	@JsonIgnoreProperties("estagioVenda")
	private List<Oportunidade> oportunidades;

	public EstagioVenda() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Oportunidade> getOportunidades() {
		return oportunidades;
	}

	public void setOportunidades(List<Oportunidade> oportunidades) {
		this.oportunidades = oportunidades;
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
		EstagioVenda other = (EstagioVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
