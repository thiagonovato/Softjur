package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Pessoa;
import com.logusit.softjur.util.HibernateUtil;

public class PessoaDAO extends GenericDAO<Pessoa> {
	
	public Pessoa buscarPorCpf(String cpf) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pessoa.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			Pessoa resultado = (Pessoa) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
