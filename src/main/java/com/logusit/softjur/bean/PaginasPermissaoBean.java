package com.logusit.softjur.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.PaginasPermissaoDAO;
import com.logusit.softjur.domain.PaginasPermissao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PaginasPermissaoBean implements Serializable {
	private PaginasPermissao paginaspermissao;
	private List<PaginasPermissao> paginaspermissaos;

	public PaginasPermissao getPaginaspermissao() {
		return paginaspermissao;
	}

	public void setPaginaspermissao(PaginasPermissao paginaspermissao) {
		this.paginaspermissao = paginaspermissao;
	}

	public List<PaginasPermissao> getPaginaspermissaos() {
		return paginaspermissaos;
	}

	public void setPaginaspermissaos(List<PaginasPermissao> paginaspermissaos) {
		this.paginaspermissaos = paginaspermissaos;
	}

	@PostConstruct
	public void listar() {
		try {
			PaginasPermissaoDAO paginaspermissaoDAO = new PaginasPermissaoDAO();
			paginaspermissaos = paginaspermissaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as páginas.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		paginaspermissao = new PaginasPermissao();
	}

	public void salvar() {
		try {
			PaginasPermissaoDAO paginaspermissaoDAO = new PaginasPermissaoDAO();
			paginaspermissaoDAO.merge(paginaspermissao);

			paginaspermissao = new PaginasPermissao();
			paginaspermissaos = paginaspermissaoDAO.listar();

			Messages.addGlobalInfo("Permissão salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a permissão.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			paginaspermissao = (PaginasPermissao) evento.getComponent().getAttributes().get("permissaoSelecionada");

			PaginasPermissaoDAO paginaspermissaoDAO = new PaginasPermissaoDAO();
			paginaspermissaoDAO.excluir(paginaspermissao);

			paginaspermissaos = paginaspermissaoDAO.listar();

			Messages.addGlobalInfo("Permissão removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a permissão.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		paginaspermissao = (PaginasPermissao) evento.getComponent().getAttributes().get("permissaoSelecionada");
	}
}
