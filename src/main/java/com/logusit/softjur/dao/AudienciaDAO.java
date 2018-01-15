package com.logusit.softjur.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.logusit.softjur.domain.Audiencia;
import com.logusit.softjur.domain.AudienciaHistorico;
import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.domain.Orgao;
import com.logusit.softjur.domain.TipoAudiencia;
import com.logusit.softjur.util.HibernateUtil;

public class AudienciaDAO extends GenericDAO<Audiencia> {

	@SuppressWarnings("unchecked")
	public List<Audiencia> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.createAlias("status", "s");
			consulta.add(Restrictions.eq("s.visaoGeral", true));
			List<Audiencia> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.createAlias("status", "s");
			consulta.add(Restrictions.eq("s.visaoGeral", true));
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Audiencia> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> listarCorrespondente() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.createAlias("correspondente.pessoa", "p");

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String cpfUsuarioLogado = (String) session.getAttribute("cpf");

			// String cpf = AutenticacaoBean.usuarioLogado.getPessoa().getCpf();

			consulta.add(Restrictions.eq("p.cpf", cpfUsuarioLogado));

			consulta.createAlias("status", "s");
			consulta.add(Restrictions.eq("s.visaoCorrespondente", true));

			List<Audiencia> resultado = consulta.list();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> buscarPorNome(String correspondente, String data, String hora, Long orgao, Long empresa,
			Long tipoAudiencia, Long escritorio) throws ParseException {

		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConv = formatoData.parse(data);

		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date horaConv = formatoHora.parse(hora);

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.createAlias("correspondente.pessoa", "p");
			consulta.add(Restrictions.ilike("p.nome", correspondente.trim()));
			consulta.add(Restrictions.sqlRestriction("datarealizar = ?", dataConv,
					org.hibernate.type.StandardBasicTypes.DATE));
			consulta.add(Restrictions.sqlRestriction("horarealizar = ?", horaConv,
					org.hibernate.type.StandardBasicTypes.TIME));
			consulta.add(
					Restrictions.sqlRestriction("orgao_codigo = ?", orgao, org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("empresa_codigo = ?", empresa,
					org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("tipoaudiencia_codigo = ?", tipoAudiencia,
					org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("escritorio_codigo = ?", escritorio,
					org.hibernate.type.StandardBasicTypes.LONG));
			ArrayList<Audiencia> resultado = (ArrayList<Audiencia>) consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> buscarPorNumeroProcesso(String numeroProcesso) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.add(Restrictions.ilike("numeroProcesso", numeroProcesso.trim()));
			ArrayList<Audiencia> resultado = (ArrayList<Audiencia>) consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Audiencia buscarInsertHistorico(String data, String hora, Orgao orgao, Empresa empresa,
			TipoAudiencia tipoAudiencia, Escritorio escritorio) throws ParseException {

		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConv = formatoData.parse(data);

		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date horaConv = formatoHora.parse(hora);

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Audiencia.class);
			consulta.add(Restrictions.sqlRestriction("datarealizar = ?", dataConv,
					org.hibernate.type.StandardBasicTypes.DATE));
			consulta.add(Restrictions.sqlRestriction("horarealizar = ?", horaConv,
					org.hibernate.type.StandardBasicTypes.TIME));
			consulta.add(Restrictions.sqlRestriction("orgao_codigo = ?", orgao.getCodigo(),
					org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("empresa_codigo = ?", empresa.getCodigo(),
					org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("tipoaudiencia_codigo = ?", tipoAudiencia.getCodigo(),
					org.hibernate.type.StandardBasicTypes.LONG));
			consulta.add(Restrictions.sqlRestriction("escritorio_codigo = ?", escritorio.getCodigo(),
					org.hibernate.type.StandardBasicTypes.LONG));
			Audiencia resultado = (Audiencia) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<AudienciaHistorico> buscarHistorico(Audiencia audiencia) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(AudienciaHistorico.class);
			consulta.add(Restrictions.sqlRestriction("audiencia_codigo = ?", audiencia.getCodigo(),
					org.hibernate.type.StandardBasicTypes.LONG));
			List<AudienciaHistorico> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
