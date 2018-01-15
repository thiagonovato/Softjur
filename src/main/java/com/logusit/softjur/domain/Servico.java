package com.logusit.softjur.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Servico extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idServico", sequenceName = "idServico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idServico")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 200, nullable = false)
	private String nome;

	@Column(nullable = true)
	private Boolean exigeOAB;

	@Column(nullable = true)
	private String ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getExigeOAB() {
		return exigeOAB;
	}

	public void setExigeOAB(Boolean exigeOAB) {
		this.exigeOAB = exigeOAB;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Servico other = (Servico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
