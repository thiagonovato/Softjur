package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.TipoAudiencia;
import com.logusit.softjur.util.HibernateUtil;

public class TipoAudienciaDAO extends GenericDAO<TipoAudiencia> {

	public TipoAudiencia buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		TipoAudiencia resultado = null;
		try {
			Criteria consulta = sessao.createCriteria(TipoAudiencia.class);
			consulta.add(Restrictions.ilike("nome", nome.trim()));
			resultado = (TipoAudiencia) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
