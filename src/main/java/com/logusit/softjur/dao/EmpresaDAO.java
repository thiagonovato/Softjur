package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.util.HibernateUtil;

public class EmpresaDAO extends GenericDAO<Empresa> {

	public Empresa buscarPorNome(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Empresa.class);
			consulta.add(Restrictions.ilike("nomeResumido", nome.trim()));
			Empresa resultado = (Empresa) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
