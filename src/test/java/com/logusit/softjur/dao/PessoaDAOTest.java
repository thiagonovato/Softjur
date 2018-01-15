package com.logusit.softjur.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.logusit.softjur.dao.CidadeDAO;
import com.logusit.softjur.dao.EstadoDAO;
import com.logusit.softjur.dao.PessoaDAO;
import com.logusit.softjur.domain.Cidade;
import com.logusit.softjur.domain.Estado;
import com.logusit.softjur.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Thiago Novato");
		pessoa.setCpf("011.744.341-71");
		pessoa.setRg("3");
		pessoa.setRua("4");
		pessoa.setNumero("5");
		pessoa.setBairro("6");
		
		Long codigoCidade = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		pessoa.setCidade(cidade);
		
		pessoa.setCep("7");
		pessoa.setComplemento("8");
		pessoa.setTelefone("9");
		pessoa.setCelular("0");
		pessoa.setEmail("thiago@logusit.com");

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 3L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro editado - Antes:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
			
			estado.setNome("Tocantins");
			estado.setSigla("TO");
			estadoDAO.editar(estado);
			
			System.out.println("Registro editado - Depois:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
}
