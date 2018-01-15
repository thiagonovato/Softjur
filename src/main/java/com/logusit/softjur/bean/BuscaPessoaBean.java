package com.logusit.softjur.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.PessoaDAO;
import com.logusit.softjur.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BuscaPessoaBean implements Serializable {

	private Pessoa pessoa;
	private Long codigoPessoa;
	private Boolean exibePainelDados;

	private List<Pessoa> pessoas;

	private PessoaDAO pessoaDAO;

	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Long codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

	@PostConstruct
	public void iniciar() {
		pessoa = new Pessoa();
		exibePainelDados = false;
	}

	public void buscar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa resultado = pessoaDAO.buscar(pessoa.getCodigo());

			if (resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Cliente não encontrado.");
			} else {
				pessoa = resultado;
				exibePainelDados = true;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas.");
			erro.printStackTrace();
		}
	}
}
