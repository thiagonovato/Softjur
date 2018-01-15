package com.logusit.softjur.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Orgao;
import com.logusit.softjur.util.HibernateUtil;

public class OrgaoDAO extends GenericDAO<Orgao> {

	public Orgao buscarPorNome(String numero, String orgao, String cidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Orgao resultado = null;
		try {
			Criteria consulta = sessao.createCriteria(Orgao.class);

			consulta.createAlias("cidade", "c");
			consulta.add(Restrictions.ilike("numero", numero.trim()));
			consulta.add(Restrictions.ilike("nome", orgao.trim()));
			consulta.add(Restrictions.ilike("c.nome", cidade.trim()));

			resultado = (Orgao) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Orgao> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Orgao.class);
			consulta.createAlias("cidade", "c");
			consulta.addOrder(Order.asc("c." + campoOrdenacao));
			consulta.addOrder(Order.asc("numero"));
			List<Orgao> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
