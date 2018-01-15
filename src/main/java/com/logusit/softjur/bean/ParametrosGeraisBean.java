package com.logusit.softjur.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.ParametrosGeraisDAO;
import com.logusit.softjur.domain.ParametrosGerais;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ParametrosGeraisBean implements Serializable {
	private ParametrosGerais parametro;

	public ParametrosGerais getParametro() {
		return parametro;
	}

	public void setParametro(ParametrosGerais parametro) {
		this.parametro = parametro;
	}

	@PostConstruct
	public void listar() {
		try {
			parametro = new ParametrosGerais();
			ParametrosGeraisDAO pgDAO = new ParametrosGeraisDAO();
			parametro = pgDAO.listarParametros();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ParametrosGeraisDAO pgDAO = new ParametrosGeraisDAO();
			pgDAO.merge(parametro);

			listar();

			Messages.addGlobalInfo("Alteração salva com sucesso.");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar.");
			erro.printStackTrace();
		}
	}
}
