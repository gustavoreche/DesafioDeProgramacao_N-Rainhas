package main;

import model.ConfiguracoesDoTabuleiro;
import view.View;
import view.console.ViewConsole;

public class Main {

	public static void main(String[] args) {
		
		View view = new ViewConsole();
		
		ConfiguracoesDoTabuleiro configuracoesDoTabuleiro = view.defineConfiguracoesDoTabuleiro();
		
		System.out.println(configuracoesDoTabuleiro.getTamanhoDoTabuleiro()
				+ " " + configuracoesDoTabuleiro.getQuantidadeDeRainhas());
	}

}
