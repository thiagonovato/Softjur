package com.logusit.softjur.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idUsuario", sequenceName = "idUsuario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUsuario")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 32, nullable = false)
	private String senha;

	@Column(length = 50, nullable = false, unique = true)
	private String login;

	@Transient
	private String senhaSemCriptografia;

	@Transient
	private String senhaSemCriptografia1;

	@Transient
	private String senhaSemCriptografia2;

	@Column(length = 10, nullable = false)
	private String perfil;

	@Column(nullable = false)
	private Boolean ativo;

	@OneToOne
	@JoinColumn(nullable = false, unique = true)
	private Pessoa pessoa;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public String getSenhaSemCriptografia1() {
		return senhaSemCriptografia1;
	}

	public void setSenhaSemCriptografia1(String senhaSemCriptografia1) {
		this.senhaSemCriptografia1 = senhaSemCriptografia1;
	}

	public String getSenhaSemCriptografia2() {
		return senhaSemCriptografia2;
	}

	public void setSenhaSemCriptografia2(String senhaSemCriptografia2) {
		this.senhaSemCriptografia2 = senhaSemCriptografia2;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Transient
	public String getPerfilFormatado() {
		String perfilFormatado = null;

		if (perfil.equals("A")) {
			perfilFormatado = "Administrador";
		} else if (perfil.equals("U")) {
			perfilFormatado = "Usuário";
		} else if (perfil.equals("C")) {
			perfilFormatado = "Correspondente";
		}

		return perfilFormatado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((senhaSemCriptografia == null) ? 0 : senhaSemCriptografia.hashCode());
		result = prime * result + ((senhaSemCriptografia1 == null) ? 0 : senhaSemCriptografia1.hashCode());
		result = prime * result + ((senhaSemCriptografia2 == null) ? 0 : senhaSemCriptografia2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (senhaSemCriptografia == null) {
			if (other.senhaSemCriptografia != null)
				return false;
		} else if (!senhaSemCriptografia.equals(other.senhaSemCriptografia))
			return false;
		if (senhaSemCriptografia1 == null) {
			if (other.senhaSemCriptografia1 != null)
				return false;
		} else if (!senhaSemCriptografia1.equals(other.senhaSemCriptografia1))
			return false;
		if (senhaSemCriptografia2 == null) {
			if (other.senhaSemCriptografia2 != null)
				return false;
		} else if (!senhaSemCriptografia2.equals(other.senhaSemCriptografia2))
			return false;
		return true;
	}

}
