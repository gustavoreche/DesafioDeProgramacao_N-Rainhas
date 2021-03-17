package tabuleiro;


import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.aplicacao.visualizacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;
import tabuleiro.infraestrutura.regra.local.RegraDoTabuleiroLocal;
import tabuleiro.infraestrutura.visualizacao.console.ViewConsole;

public class Main {

	public static void main(String[] args) {
		
		View view = new ViewConsole();
		RegraDoTabuleiro regraDoTabuleiro = new RegraDoTabuleiroLocal();
		
		ConfiguracoesDoTabuleiro configuracoesDoTabuleiro = view.defineConfiguracoesDoTabuleiro();
		Tabuleiro tabuleiro = regraDoTabuleiro.cria(configuracoesDoTabuleiro);
		view.informaSeTeveNumeroDeRainhasEmExcesso(configuracoesDoTabuleiro);
		view.exibeTabuleiro(tabuleiro);
	}

}
