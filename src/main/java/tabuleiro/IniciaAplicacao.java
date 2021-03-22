package tabuleiro;

import tabuleiro.aplicacao.ForneceInformacoes;
import tabuleiro.aplicacao.RecebeDados;
import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public class IniciaAplicacao {
	
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	private Tabuleiro tabuleiro;
	
	public void executa(RecebeDados recebeDados, RegraDoTabuleiro regraDoTabuleiro, ForneceInformacoes forneceInformacoes) {
		this.configuracoesDoTabuleiro = recebeDados.defineConfiguracoesDoTabuleiro();
		this.tabuleiro = regraDoTabuleiro.cria(this.configuracoesDoTabuleiro);
		forneceInformacoes.informaSeTeveNumeroDeRainhasEmExcesso(this.configuracoesDoTabuleiro);
		forneceInformacoes.exibeTabuleiro(this.tabuleiro);
	}
	
	public ConfiguracoesDoTabuleiro getConfiguracoesDoTabuleiro() {
		return configuracoesDoTabuleiro;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

}
