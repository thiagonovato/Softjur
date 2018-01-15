package com.logusit.softjur.enumeracao;

public enum TipoUsuario {
	ADMINISTRADOR, USUARIO, CORRESPONDENTE;

	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRADOR:
			return "Administrador";
		case USUARIO:
			return "Usuário";
		case CORRESPONDENTE:
			return "Correspondente";
		default:
			return null;
		}
	}
}
