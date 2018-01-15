package com.logusit.softjur.bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.PaginasPermissaoDAO;
import com.logusit.softjur.dao.UsuarioDAO;
import com.logusit.softjur.domain.PaginasPermissao;
import com.logusit.softjur.domain.Pessoa;
import com.logusit.softjur.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private ArrayList<PaginasPermissao> paginaspermitidas;
	public static Usuario usuarioLogado;

	public static String login;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		AutenticacaoBean.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getLogin(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Login e/ou senha incorreto.");
				return;
			} else {
				Boolean usuarioAtivo = AutenticacaoBean.usuarioLogado.getAtivo();
				if (usuarioAtivo == false) {
					Messages.addGlobalError("Usuário inativo.");
					return;
				} else {
					String nomeUsuarioLogado = AutenticacaoBean.usuarioLogado.getPessoa().getNome();
					String loginUsuarioLogado = AutenticacaoBean.usuarioLogado.getLogin();
					String cpfUsuarioLogado = AutenticacaoBean.usuarioLogado.getPessoa().getCpf();
					String perfilUsuarioLogado = AutenticacaoBean.usuarioLogado.getPerfil();

					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
							.getSession(false);
					session.setAttribute("usuario", usuarioLogado);
					session.setAttribute("login", loginUsuarioLogado);
					session.setAttribute("nome", nomeUsuarioLogado);
					session.setAttribute("cpf", cpfUsuarioLogado);
					session.setAttribute("perfil", perfilUsuarioLogado);

					PaginasPermissaoDAO pdao = new PaginasPermissaoDAO();
					paginaspermitidas = pdao.listarPaginasPermitidas(perfilUsuarioLogado);

					session.setAttribute("paginasPermitidas", paginaspermitidas);

					Faces.redirect("./pages/principal.xhtml");
				}
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public void deslogar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		HttpSession autentica = Faces.getSession();
		autentica.invalidate();
		Faces.navigate("index.xhtml");

	}

	public boolean temPermissoes(String permissoes) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ArrayList<PaginasPermissao> resultadoPaginas = (ArrayList<PaginasPermissao>) session.getAttribute("paginasPermitidas");

		for(PaginasPermissao paginas : resultadoPaginas) {
			if (permissoes.equals(paginas.getNome())) {
				return true;
			}
		}
		
//		
//		String perfilUsuario = (String) session.getAttribute("perfil");
//
//		System.out.println("Perfil do usuario: " + perfilUsuario);
//		for (String permissao : permissoes) {
//			if (permissao.toString().contains(perfilUsuario)) {
//				return true;
//			}
//		}

		return false;
	}

}
