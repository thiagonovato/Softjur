package com.logusit.softjur.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Audiencia extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idAudiencia", sequenceName = "idAudiencia", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idAudiencia")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRealizar;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaRealizar;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Correspondente correspondente;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Orgao orgao;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Escritorio escritorio;

	@ManyToOne
	@JoinColumn(nullable = true)
	private TipoAudiencia tipoAudiencia;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Status status;

	@Column(length = 100, nullable = true)
	private String numeroProcesso;

	@Column(length = 100, nullable = true)
	private String numeroInterno;

	@Column(length = 100, nullable = true)
	private String numeroConsulta;

	@Column(length = 100, nullable = true)
	private String autor;

	@Column(nullable = true)
	private String orientacoes;

	@Column(nullable = true)
	private Boolean ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataRealizar() {
		return dataRealizar;
	}

	public void setDataRealizar(Date dataRealizar) {
		this.dataRealizar = dataRealizar;
	}

	public Date getHoraRealizar() {
		return horaRealizar;
	}

	public void setHoraRealizar(Date horaRealizar) {
		this.horaRealizar = horaRealizar;
	}

	public Correspondente getCorrespondente() {
		return correspondente;
	}

	public void setCorrespondente(Correspondente correspondente) {
		this.correspondente = correspondente;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	public TipoAudiencia getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public String getNumeroConsulta() {
		return numeroConsulta;
	}

	public void setNumeroConsulta(String numeroConsulta) {
		this.numeroConsulta = numeroConsulta;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
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
		Audiencia other = (Audiencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
