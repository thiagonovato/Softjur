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
import com.logusit.softjur.dao.EscritorioDAO;
import com.logusit.softjur.dao.EstadoDAO;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EscritorioBean implements Serializable {
	private Escritorio escritorio;
	private List<Escritorio> escritorios;

	private Estado estado;

	private List<Estado> estados;
	private List<Cidade> cidades;

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	public List<Escritorio> getEscritorios() {
		return escritorios;
	}

	public void setEscritorios(List<Escritorio> escritorios) {
		this.escritorios = escritorios;
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
			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorios = escritorioDAO.listar("nomeCompleto");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os escritórios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			escritorio = new Escritorio();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo escritório");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			escritorio = (Escritorio) evento.getComponent().getAttributes().get("escritorioSelecionado");

			estado = escritorio.getCidade().getEstado();

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
			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorioDAO.merge(escritorio);

			escritorios = escritorioDAO.listar("nomeCompleto");

			novo();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

			Messages.addGlobalInfo("Escritório salva com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o escritório.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			escritorio = (Escritorio) evento.getComponent().getAttributes().get("escritorioSelecionado");

			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorioDAO.excluir(escritorio);

			escritorios = escritorioDAO.listar();

			Messages.addGlobalInfo("Escritório removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o escritório.");
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
