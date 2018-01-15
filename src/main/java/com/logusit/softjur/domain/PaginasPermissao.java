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
public class PaginasPermissao extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idPaginasPermissao", sequenceName = "idPaginasPermissao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPaginasPermissao")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 50, nullable = false, unique = true)
	private String nome;

	@Column(length = 20, nullable = false)
	private String perfil;

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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
	public String getPerfilPermitido() {
		String perfilPermitido = "";
		String perfilA = "";
		String perfilC = "";
		String perfilU = "";

		if (perfil.contains("A")) {
			perfilA = "Administrador";
		}

		if (perfil.contains("C")) {
			perfilC = "Correspondente";
		}

		if (perfil.contains("U")) {
			perfilU = "Usuario";
		}
		return perfilPermitido = perfilA + " " + perfilC + " " + perfilU;
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
		PaginasPermissao other = (PaginasPermissao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
