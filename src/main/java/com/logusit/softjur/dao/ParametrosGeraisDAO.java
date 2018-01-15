package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.logusit.softjur.domain.ParametrosGerais;
import com.logusit.softjur.util.HibernateUtil;

public class ParametrosGeraisDAO extends GenericDAO<ParametrosGerais> {
	
	public ParametrosGerais listarParametros() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ParametrosGerais.class);
			ParametrosGerais resultado = (ParametrosGerais) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
