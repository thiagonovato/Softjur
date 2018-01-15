package com.logusit.softjur.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.AudienciaDAO;
import com.logusit.softjur.dao.AudienciaHistoricoDAO;
import com.logusit.softjur.dao.CorrespondenteDAO;
import com.logusit.softjur.dao.EmpresaDAO;
import com.logusit.softjur.dao.EscritorioDAO;
import com.logusit.softjur.dao.OrgaoDAO;
import com.logusit.softjur.dao.ParametrosGeraisDAO;
import com.logusit.softjur.dao.StatusDAO;
import com.logusit.softjur.dao.TipoAudienciaDAO;
import com.logusit.softjur.domain.Audiencia;
import com.logusit.softjur.domain.AudienciaHistorico;
import com.logusit.softjur.domain.Correspondente;
import com.logusit.softjur.domain.Empresa;
import com.logusit.softjur.domain.Escritorio;
import com.logusit.softjur.domain.Orgao;
import com.logusit.softjur.domain.Status;
import com.logusit.softjur.domain.TipoAudiencia;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AudienciaBean implements Serializable {
	private Audiencia audiencia;
	private AudienciaHistorico audienciaHistorico;
	private List<Audiencia> audiencias;
	private List<AudienciaHistorico> audienciasHistorico;

	private String pathArquivo = "";

	private Orgao orgao;
	private List<Orgao> orgaos;

	private Correspondente correspondente;
	private List<Correspondente> correspondentes;

	private Escritorio escritorio;
	private List<Escritorio> escritorios;

	private Empresa empresa;
	private List<Empresa> empresas;

	private TipoAudiencia tipoAudiencia;
	private List<TipoAudiencia> tiposAudiencia;

	private Status status;
	private List<Status> statuss;

	private String numeroProcesso;

	private String numeroInterno;

	private String numeroConsulta;

	private String autor;

	private Boolean ativo;

	private String orientacoes;

	private String situacao;

	private Boolean ativaTelaArquivo = false;
	private Boolean ativaTelaObs = false;

	public String getPathArquivo() {
		return pathArquivo;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}

	public Audiencia getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public List<Audiencia> getAudiencias() {
		return audiencias;
	}

	public void setAudiencias(List<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public List<Orgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}

	public List<AudienciaHistorico> getAudienciasHistorico() {
		return audienciasHistorico;
	}

	public void setAudienciasHistorico(List<AudienciaHistorico> audienciasHistorico) {
		this.audienciasHistorico = audienciasHistorico;
	}

	public Correspondente getCorrespondente() {
		return correspondente;
	}

	public void setCorrespondente(Correspondente correspondente) {
		this.correspondente = correspondente;
	}

	public List<Correspondente> getCorrespondentes() {
		return correspondentes;
	}

	public void setCorrespondentes(List<Correspondente> correspondentes) {
		this.correspondentes = correspondentes;
	}

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	public List<Escritorio> getEscritorios() {
		return escritorios;
	}

	public void setEscritorios(List<Escritorio> escritorios) {
		this.escritorios = escritorios;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public TipoAudiencia getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	public List<TipoAudiencia> getTiposAudiencia() {
		return tiposAudiencia;
	}

	public void setTiposAudiencia(List<TipoAudiencia> tiposAudiencia) {
		this.tiposAudiencia = tiposAudiencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public void setStatuss(List<Status> statuss) {
		this.statuss = statuss;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public String getNumeroConsulta() {
		return numeroConsulta;
	}

	public void setNumeroConsulta(String numeroConsulta) {
		this.numeroConsulta = numeroConsulta;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Boolean getAtivatelaArquivo() {
		return ativaTelaArquivo;
	}

	public void setAtivatelaArquivo(Boolean ativatelaArquivo) {
		this.ativaTelaArquivo = ativatelaArquivo;
	}

	public Boolean getAtivaTelaObs() {
		return ativaTelaObs;
	}

	public void setAtivaTelaObs(Boolean ativaTelaObs) {
		this.ativaTelaObs = ativaTelaObs;
	}

	@PostConstruct
	public void listar() {
		try {
			AudienciaDAO audienciaDAO = new AudienciaDAO();
			audiencias = audienciaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as audências.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			audiencia = new Audiencia();

			audienciaHistorico = new AudienciaHistorico();

			audiencia.setDataCadastro(new Date());

			orgao = new Orgao();
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar("nome");

			correspondente = new Correspondente();
			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondentes = correspondenteDAO.listar();

			empresa = new Empresa();
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

			escritorio = new Escritorio();
			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorios = escritorioDAO.listar();

			tipoAudiencia = new TipoAudiencia();
			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tiposAudiencia = tipoAudienciaDAO.listar();

			status = new Status();
			StatusDAO statusDAO = new StatusDAO();
			statuss = statusDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova audiência.");
			erro.printStackTrace();
		}
	}

	public void listarCorrespondente() {
		CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
		correspondentes = correspondenteDAO.listar();
	}

	public void listarEscritorios() {
		EscritorioDAO escritorioDAO = new EscritorioDAO();
		escritorios = escritorioDAO.listar();
	}

	public void editar(ActionEvent evento) {
		try {
			situacao = "";
			ativaTelaArquivo = false;
			ativaTelaObs = false;

			audiencia = (Audiencia) evento.getComponent().getAttributes().get("audienciaSelecionada");

			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar("nome");

			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondentes = correspondenteDAO.listar();

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorios = escritorioDAO.listar();

			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tiposAudiencia = tipoAudienciaDAO.listar();

			StatusDAO statusDAO = new StatusDAO();
			statuss = statusDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar algum campo.");
			erro.printStackTrace();
		}
	}

	public void salvar() throws ParseException {
		try {

			// Merge da Audiência (Salva no Novo ou no Editar)
			AudienciaDAO audienciaDAO = new AudienciaDAO();
			audienciaDAO.merge(audiencia);

			inserirHistorico();

			audiencias = audienciaDAO.listar();

			novo();

			orgao = new Orgao();
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar();

			correspondente = new Correspondente();
			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondentes = correspondenteDAO.listar();

			empresa = new Empresa();
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

			escritorio = new Escritorio();
			EscritorioDAO escritorioDAO = new EscritorioDAO();
			escritorios = escritorioDAO.listar();

			tipoAudiencia = new TipoAudiencia();
			TipoAudienciaDAO tipoAudienciaDAO = new TipoAudienciaDAO();
			tiposAudiencia = tipoAudienciaDAO.listar();

			status = new Status();
			StatusDAO statusDAO = new StatusDAO();
			statuss = statusDAO.listar();

			Messages.addGlobalInfo("Audiência salva com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar a audiência.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			audiencia = (Audiencia) evento.getComponent().getAttributes().get("audienciaSelecionada");

			AudienciaDAO audienciaDAO = new AudienciaDAO();
			audienciaDAO.excluir(audiencia);

			audiencias = audienciaDAO.listar();

			Messages.addGlobalInfo("Audiência removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a audiência");
			erro.printStackTrace();
		}
	}

	public void importarAudiencia(String data, String hora, Orgao orgao, TipoAudiencia tipoAudiencia, Empresa empresa,
			Escritorio escritorio, Correspondente correspondente, String numeroProcesso, String numeroInterno,
			String numeroConsulta, String autor) throws ParseException {

		try {

			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataConv = formatoData.parse(data);

			DateFormat formatoHora = new SimpleDateFormat("HH:mm");
			Date horaConv = formatoHora.parse(hora);

			audiencia = new Audiencia();
			audiencia.setDataCadastro(new Date());

			audiencia.setDataRealizar(dataConv);
			audiencia.setHoraRealizar(horaConv);
			audiencia.setOrgao(orgao);
			audiencia.setTipoAudiencia(tipoAudiencia);
			audiencia.setEmpresa(empresa);
			audiencia.setEscritorio(escritorio);
			audiencia.setCorrespondente(correspondente);
			audiencia.setNumeroProcesso(numeroProcesso);
			audiencia.setNumeroInterno(numeroInterno);
			audiencia.setNumeroConsulta(numeroConsulta);
			audiencia.setAutor(autor);

			Status status = new Status();
			StatusDAO sDAO = new StatusDAO();
			status = sDAO.buscarPorNome("Aguardando realização");
			audiencia.setStatus(status);

			AudienciaDAO audienciaDAO = new AudienciaDAO();
			audienciaDAO.merge(audiencia);

			inserirHistorico();

			System.out.println("Audiência salva.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a audiência");
			erro.printStackTrace();
		}

	}

	public void novaSituacao() {

		situacao = audiencia.getStatus().getNome();

		// Status status = new Status();
		// StatusDAO sDAO = new StatusDAO();
		// status = sDAO.buscarPorNome(situacao);
		//
		// audiencia.setStatus(status);

		System.out.println(situacao);

		if (situacao.equals("Realizada")) {
			ativaTelaArquivo = true;
			ativaTelaObs = true;
		} else if (situacao == null) {
			ativaTelaArquivo = false;
			ativaTelaObs = false;
		} else {
			ativaTelaArquivo = false;
			ativaTelaObs = true;
		}
	}

	public void listarHistorico(ActionEvent evento) {
		try {
			audiencia = (Audiencia) evento.getComponent().getAttributes().get("audienciaSelecionada");

			AudienciaDAO audienciaDAO = new AudienciaDAO();
			audienciasHistorico = audienciaDAO.buscarHistorico(audiencia);

			ParametrosGeraisDAO pgDAO = new ParametrosGeraisDAO();
			pathArquivo = pgDAO.listarParametros().getPathArquivo();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar o histórico");
			erro.printStackTrace();
		}
	}

	public void inserirHistorico() throws ParseException {
		// Buscando informações da audiência inserida para gravar no histórico.
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String dataConv = formatoData.format(audiencia.getDataRealizar());

		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		String horaConv = formatoHora.format(audiencia.getHoraRealizar());

		Audiencia resAudienciaHistorico = new Audiencia();

		AudienciaDAO audienciaDAO = new AudienciaDAO();
		resAudienciaHistorico = audienciaDAO.buscarInsertHistorico(dataConv, horaConv, audiencia.getOrgao(),
				audiencia.getEmpresa(), audiencia.getTipoAudiencia(), audiencia.getEscritorio());

		audienciaHistorico = new AudienciaHistorico();

		// SETANDO OS DADOS DO OBJETIVO AUDIENCIAHISTIRICO BASEADO NO OBJETO AUDIENCIA
		audienciaHistorico.setAtivo(resAudienciaHistorico.getAtivo());
		audienciaHistorico.setAutor(resAudienciaHistorico.getAutor());
		audienciaHistorico.setCorrespondente(resAudienciaHistorico.getCorrespondente());
		audienciaHistorico.setDataCadastro(new Date());
		audienciaHistorico.setDataRealizar(resAudienciaHistorico.getDataRealizar());
		audienciaHistorico.setEmpresa(resAudienciaHistorico.getEmpresa());
		audienciaHistorico.setEscritorio(resAudienciaHistorico.getEscritorio());
		audienciaHistorico.setHoraRealizar(resAudienciaHistorico.getHoraRealizar());
		audienciaHistorico.setNumeroConsulta(resAudienciaHistorico.getNumeroConsulta());
		audienciaHistorico.setNumeroInterno(resAudienciaHistorico.getNumeroInterno());
		audienciaHistorico.setNumeroProcesso(resAudienciaHistorico.getNumeroProcesso());
		audienciaHistorico.setOrgao(resAudienciaHistorico.getOrgao());
		audienciaHistorico.setStatus(resAudienciaHistorico.getStatus());
		audienciaHistorico.setTipoAudiencia(resAudienciaHistorico.getTipoAudiencia());
		audienciaHistorico.setAudiencia(resAudienciaHistorico);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		audienciaHistorico.setLogin(session.getAttribute("login").toString());

		AudienciaHistoricoDAO ahDAO = new AudienciaHistoricoDAO();
		ahDAO.merge(audienciaHistorico);
	}

	// public void popular() {
	// try {
	// if (estado != null) {
	// CidadeDAO cidadeDAO = new CidadeDAO();
	// cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
	// } else {
	// cidades = new ArrayList<>();
	// }
	// } catch (RuntimeException erro) {
	// Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
	// erro.printStackTrace();
	// }
	// }
}
