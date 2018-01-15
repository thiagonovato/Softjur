package com.logusit.softjur.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Status extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idStatus", sequenceName = "idStatus", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idStatus")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 200, nullable = true)
	private String descricao;

	@Column(length = 50, nullable = true)
	private String label;

	@Column(nullable = false)
	private Boolean visaoGeral;

	@Column(nullable = false)
	private Boolean visaoCorrespondente;

	@Column(nullable = false)
	private Boolean ativo;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getVisaoGeral() {
		return visaoGeral;
	}

	public void setVisaoGeral(Boolean visaoGeral) {
		this.visaoGeral = visaoGeral;
	}

	public Boolean getVisaoCorrespondente() {
		return visaoCorrespondente;
	}

	public void setVisaoCorrespondente(Boolean visaoCorrespondente) {
		this.visaoCorrespondente = visaoCorrespondente;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;
	}

	@Transient
	public String getVisaoGeralFormatado() {
		String visaoGeralFormatado = "Não";

		if (visaoGeral) {
			visaoGeralFormatado = "Sim";
		}

		return visaoGeralFormatado;
	}

	@Transient
	public String getVisaoCorrespondenteFormatado() {
		String visaoCorrespondenteFormatado = "Não";

		if (visaoCorrespondente) {
			visaoCorrespondenteFormatado = "Sim";
		}

		return visaoCorrespondenteFormatado;
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
		Status other = (Status) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
