package com.logusit.softjur.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.ServicoDAO;
import com.logusit.softjur.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable {
	private Servico servico;
	private List<Servico> servicos;

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@PostConstruct
	public void listar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os serviços.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			servico = new Servico();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo serviço.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um serviço.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.merge(servico);

			servicos = servicoDAO.listar("nome");

			novo();

			Messages.addGlobalInfo("Serviço salvo com sucesso.");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o serviço.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");

			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.excluir(servico);

			servicos = servicoDAO.listar();

			Messages.addGlobalInfo("Servicço removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o serviço.");
			erro.printStackTrace();
		}
	}
}
