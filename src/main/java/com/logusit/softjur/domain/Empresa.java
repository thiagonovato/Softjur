package com.logusit.softjur.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Empresa extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idEmpresa", sequenceName = "idEmpresa", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idEmpresa")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 200, nullable = false)
	private String nomeCompleto;

	@Column(length = 100, nullable = false)
	private String nomeResumido;

	@Column(length = 100, nullable = false)
	private String nomeContato;

	@Column(length = 14, nullable = false)
	private String telefone;

	@Column(length = 100, nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;

	@Column(length = 100, nullable = false)
	private String rua;

	@Column(nullable = false)
	private String numero;

	@Column(length = 30, nullable = false)
	private String bairro;

	@Column(length = 10, nullable = false)
	private String cep;

	@Column(length = 10)
	private String complemento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeResumido() {
		return nomeResumido;
	}

	public void setNomeResumido(String nomeResumido) {
		this.nomeResumido = nomeResumido;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		Empresa other = (Empresa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
