package tabuleiro.aplicacao;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public interface ForneceInformacoes {
	
	void exibeTabuleiro(Tabuleiro tabuleiro);
	void informaSeTeveNumeroDeRainhasEmExcesso(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro);
}
