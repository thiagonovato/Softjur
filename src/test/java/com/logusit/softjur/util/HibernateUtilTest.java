package com.logusit.softjur.util;

import org.hibernate.Session;
import org.junit.Test;

import com.logusit.softjur.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
