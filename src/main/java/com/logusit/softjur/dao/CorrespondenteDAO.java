package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Correspondente;
import com.logusit.softjur.util.HibernateUtil;

public class CorrespondenteDAO extends GenericDAO<Correspondente> {

	public Correspondente buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Correspondente resultado = null;
		try {
			Criteria consulta = sessao.createCriteria(Correspondente.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.ilike("p.nome", nome.trim()));
			resultado = (Correspondente) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}