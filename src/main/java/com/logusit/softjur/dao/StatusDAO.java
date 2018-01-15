package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Status;
import com.logusit.softjur.util.HibernateUtil;

public class StatusDAO extends GenericDAO<Status> {
	
	public Status buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Status.class);
			consulta.add(Restrictions.ilike("nome", nome.trim()));
			Status resultado = (Status) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
