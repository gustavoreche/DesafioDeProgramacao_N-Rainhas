package tabuleiro;

import tabuleiro.infraestrutura.regra.local.RegraDoTabuleiroLocal;
import tabuleiro.infraestrutura.visualizacao.console.ViewConsole;

public class Main {

	public static void main(String[] args) {
		
		new IniciaAplicacao().executa(new ViewConsole(), new RegraDoTabuleiroLocal());		
	}

}
