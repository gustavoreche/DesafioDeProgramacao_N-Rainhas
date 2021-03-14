package tabuleiro;


import tabuleiro.aplicacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.infraestrutura.console.ViewConsole;

public class Main {

	public static void main(String[] args) {
		
		View view = new ViewConsole();
		
		ConfiguracoesDoTabuleiro configuracoesDoTabuleiro = view.defineConfiguracoesDoTabuleiro();
		
		System.out.println(configuracoesDoTabuleiro.getTamanhoDoTabuleiro()
				+ " " + configuracoesDoTabuleiro.getQuantidadeDeRainhas());
	}

}
