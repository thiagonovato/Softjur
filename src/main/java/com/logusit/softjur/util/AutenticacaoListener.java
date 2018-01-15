package com.logusit.softjur.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.PaginasPermissaoDAO;
import com.logusit.softjur.domain.PaginasPermissao;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	private PaginasPermissao paginaspermissao;

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();

		boolean ehPaginaDeAutenticacao = paginaAtual.contains("index.xhtml");

		if (!ehPaginaDeAutenticacao) {
			// AutenticacaoBean autenticacaoBean =
			// Faces.getSessionAttribute("autenticacaoBean");
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Object nomeUsuarioLogado = session.getAttribute("usuario");

			String perfilUsuario = (String) session.getAttribute("perfil");
			if (nomeUsuarioLogado == null) {
				Faces.navigate("index.xhtml");
				return;
			} else {
				PaginasPermissaoDAO pdao = new PaginasPermissaoDAO();
				PaginasPermissao permissao = pdao.buscarPermissao(paginaAtual, perfilUsuario);

				if (permissao == null) {
					System.out.println("Nenhum registro encontrado. Acesso não autorizado.");
					Messages.addGlobalFatal("Acesso não autorizado.");
					Faces.navigate("acessoproibido.xhtml");
				} else {
					System.out.println("Acesso concedido.");
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
