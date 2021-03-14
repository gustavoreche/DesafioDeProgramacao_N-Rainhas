package tabuleiro.aplicacao.visualizacao;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;

public interface View {
	
	ConfiguracoesDoTabuleiro defineConfiguracoesDoTabuleiro();
	void exibeTabuleiro(String[][] tabuleiro);
	void exibeMensagemDeNumeroDeTentativas(int tentativasMaximas);
}
