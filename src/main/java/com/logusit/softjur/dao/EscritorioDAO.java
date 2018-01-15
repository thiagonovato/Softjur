package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.util.HibernateUtil;

public class EscritorioDAO extends GenericDAO<Escritorio> {

	public Escritorio buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Escritorio resultado = null;
		try {
			Criteria consulta = sessao.createCriteria(Escritorio.class);
			consulta.add(Restrictions.ilike("nomeResumido", nome.trim()));
			resultado = (Escritorio) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
