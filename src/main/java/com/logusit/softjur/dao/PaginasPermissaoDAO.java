package com.logusit.softjur.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.PaginasPermissao;
import com.logusit.softjur.util.HibernateUtil;

public class PaginasPermissaoDAO extends GenericDAO<PaginasPermissao> {

	public PaginasPermissao buscarPermissao(String nome, String perfilUsuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PaginasPermissao.class);
			consulta.add(Restrictions.eq("nome", nome));
			consulta.add(Restrictions.like("perfil", "%" + perfilUsuario + "%"));
			PaginasPermissao resultado = (PaginasPermissao) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PaginasPermissao> listarPaginasPermitidas(String perfilUsuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PaginasPermissao.class);
			consulta.add(Restrictions.like("perfil", "%" + perfilUsuario + "%"));
			ArrayList<PaginasPermissao> resultado = (ArrayList<PaginasPermissao>) consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
