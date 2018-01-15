package com.logusit.softjur.dao;

import org.junit.Ignore;
import org.junit.Test;

import com.logusit.softjur.dao.PaginasPermissaoDAO;
import com.logusit.softjur.domain.PaginasPermissao;

public class PaginasPermissaoDAOTest {
	
	@Test
	@Ignore
	public void buscar(){
		String nome = "paginaspermissao.xhtml";
		String perfilUsuario = "A";
		
		PaginasPermissaoDAO pdao = new PaginasPermissaoDAO();
		PaginasPermissao permissao = pdao.buscarPermissao(nome, perfilUsuario);
		
		if(permissao == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(permissao.getCodigo() + " - " + permissao.getPerfil() + " - " + permissao.getNome());
		}
	}
	
	@Test
	@Ignore
	public void salvar() {
		PaginasPermissao permissao = new PaginasPermissao();
		permissao.setNome("/pages/paginaspermissao.xhtml");
		permissao.setPerfil("A");
		permissao.setAtivo(true);

		PaginasPermissaoDAO pdao = new PaginasPermissaoDAO();
		pdao.salvar(permissao);
	}
}
