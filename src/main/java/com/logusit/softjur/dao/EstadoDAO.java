package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Estado;
import com.logusit.softjur.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {

	public Estado buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Estado resultado = null;
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.ilike("sigla", nome.trim()));
			resultado = (Estado) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
