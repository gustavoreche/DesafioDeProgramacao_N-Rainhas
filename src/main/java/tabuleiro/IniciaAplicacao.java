package tabuleiro;

import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.aplicacao.visualizacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public class IniciaAplicacao {
	
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	private Tabuleiro tabuleiro;
	
	public void executa(View view, RegraDoTabuleiro regraDoTabuleiro) {
		this.configuracoesDoTabuleiro = view.defineConfiguracoesDoTabuleiro();
		this.tabuleiro = regraDoTabuleiro.cria(this.configuracoesDoTabuleiro);
		view.informaSeTeveNumeroDeRainhasEmExcesso(this.configuracoesDoTabuleiro);
		view.exibeTabuleiro(this.tabuleiro);
	}
	
	public ConfiguracoesDoTabuleiro getConfiguracoesDoTabuleiro() {
		return configuracoesDoTabuleiro;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

}
