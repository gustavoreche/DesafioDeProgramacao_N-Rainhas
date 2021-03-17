package tabuleiro.aplicacao.visualizacao;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public interface View {
	
	ConfiguracoesDoTabuleiro defineConfiguracoesDoTabuleiro();
	void exibeTabuleiro(Tabuleiro tabuleiro);
	void informaSeTeveNumeroDeRainhasEmExcesso(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro);
}
