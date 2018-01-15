package com.logusit.softjur.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.CidadeDAO;
import com.logusit.softjur.dao.EstadoDAO;
import com.logusit.softjur.dao.OrgaoDAO;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Estado;
import com.logusit.softjur.domain.Orgao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrgaoBean implements Serializable {
	private Orgao orgao;
	private List<Orgao> orgaos;

	private Estado estado;

	private List<Estado> estados;
	private List<Cidade> cidades;

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public List<Orgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os órgãos.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			orgao = new Orgao();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as cidades.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			orgao = (Orgao) evento.getComponent().getAttributes().get("orgaoSelecionado");

			estado = orgao.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaoDAO.merge(orgao);

			orgaos = orgaoDAO.listar("nome");

			novo();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

			Messages.addGlobalInfo("Órgão salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o órgão.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			orgao = (Orgao) evento.getComponent().getAttributes().get("orgaoSelecionado");

			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaoDAO.excluir(orgao);

			orgaos = orgaoDAO.listar();

			Messages.addGlobalInfo("Órgão removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o órgão.");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}
}
