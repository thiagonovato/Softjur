package com.logusit.softjur.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import com.logusit.softjur.dao.UsuarioDAO;
import com.logusit.softjur.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SenhaBean implements Serializable {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void listar() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String cpfLogado = (String) session.getAttribute("cpf");

			usuario = new Usuario();
			UsuarioDAO udao = new UsuarioDAO();
			usuario = udao.buscarPorCpf(cpfLogado);

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao localizar o usuário.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO udao = new UsuarioDAO();

			String senhaAtual = usuario.getSenha();

			String senhaNovaDigitada = usuario.getSenhaSemCriptografia();
			SimpleHash senhaNovaDigitadaCript = new SimpleHash("md5", senhaNovaDigitada);

			System.out.println("Senha atual:" + senhaAtual);
			System.out.println("Senha atual digitada:" + senhaNovaDigitadaCript);

			String senhaNova1 = usuario.getSenhaSemCriptografia1();

			String senhaNova2 = usuario.getSenhaSemCriptografia2();

			if (senhaAtual.equals(senhaNovaDigitadaCript.toHex())) {
				if (!senhaNova1.equals(senhaNova2)) {
					Messages.addGlobalError("Senha nova não coincidem nos dois campos.");
				} else {
					SimpleHash hashSenha = new SimpleHash("md5", senhaNova2);
					usuario.setSenha(hashSenha.toHex());
					udao.merge(usuario);
					Messages.addGlobalInfo("Alteração salva com sucesso.");
					listar();
				}
			} else {
				Messages.addGlobalError("Senha atual não confere.");
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar.");
			erro.printStackTrace();
		}
	}

}
