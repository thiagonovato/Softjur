package com.logusit.softjur.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.TipoAudienciaDAO;
import com.logusit.softjur.domain.TipoAudiencia;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoAudienciaBean implements Serializable {
	private TipoAudiencia tipoAudiencia;
	private List<TipoAudiencia> tiposAudiencia;

	public TipoAudiencia getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	public List<TipoAudiencia> getTiposAudiencia() {
		return tiposAudiencia;
	}

	public void setTiposAudiencia(List<TipoAudiencia> tiposAudiencia) {
		this.tiposAudiencia = tiposAudiencia;
	}

	@PostConstruct
	public void listar() {
		try {
			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tiposAudiencia = tipoAudienciaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os tipos de audiência");
			erro.printStackTrace();
		}
	}

	public void novo() {
		tipoAudiencia = new TipoAudiencia();
	}

	public void salvar() {
		try {
			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tipoAudienciaDAO.merge(tipoAudiencia);

			tipoAudiencia = new TipoAudiencia();
			tiposAudiencia = tipoAudienciaDAO.listar();

			Messages.addGlobalInfo("Tipo de Audiência salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			tipoAudiencia = (TipoAudiencia) evento.getComponent().getAttributes().get("tipoSelecionado");

			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tipoAudienciaDAO.excluir(tipoAudiencia);

			tiposAudiencia = tipoAudienciaDAO.listar();

			Messages.addGlobalInfo("Tipo de Audiência removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o tipo de audiência.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		tipoAudiencia = (TipoAudiencia) evento.getComponent().getAttributes().get("tipoSelecionado");
	}
}
