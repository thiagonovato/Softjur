package com.logusit.softjur.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.util.HibernateUtil;

public class EmpresaDAOTest {

	private String nome = "tim";
	@Test
	@Ignore
	public void buscarPorNome() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Empresa.class);
//			consulta.add(Restrictions.ilike("nomeResumido", nome));
			consulta.add(Restrictions.ilike("nomeResumido", nome, MatchMode.ANYWHERE));
			Empresa resultado = (Empresa) consulta.uniqueResult();
			System.out.println("Resultado teste :" + resultado);
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
