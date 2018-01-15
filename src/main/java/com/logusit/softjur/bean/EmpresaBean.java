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
import com.logusit.softjur.dao.EmpresaDAO;
import com.logusit.softjur.dao.EstadoDAO;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {
	private Empresa empresa;
	private List<Empresa> empresas;

	private Estado estado;

	private List<Estado> estados;
	private List<Cidade> cidades;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
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
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar("nomeCompleto");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as empresas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			empresa = new Empresa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova empresa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

			estado = empresa.getCidade().getEstado();

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
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresas = empresaDAO.listar("nomeCompleto");

			novo();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

			Messages.addGlobalInfo("Empresa salva com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar empresa.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.excluir(empresa);

			empresas = empresaDAO.listar();

			Messages.addGlobalInfo("Empresa removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a empresa.");
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
