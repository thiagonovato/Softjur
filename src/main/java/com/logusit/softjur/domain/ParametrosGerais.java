package com.logusit.softjur.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class ParametrosGerais extends GenericDomain {

	@Id
	@SequenceGenerator(name = "idParametros", sequenceName = "idParametros", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idParametros")
	@Column(name = "codigo", updatable = false, nullable = false, unique = true)
	private Long codigo;

	@Column(length = 500, nullable = false)
	private String pathUpload;

	@Column(length = 500, nullable = false)
	private String pathArquivo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPathUpload() {
		return pathUpload;
	}

	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}

	public String getPathArquivo() {
		return pathArquivo;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}

}
