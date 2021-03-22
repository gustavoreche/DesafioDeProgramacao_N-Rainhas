package tabuleiro;

import tabuleiro.infraestrutura.regra.local.RegraDoTabuleiroLocal;
import tabuleiro.infraestrutura.visualizacao.console.RecebeDadosViaConsole;
import tabuleiro.infraestrutura.visualizacao.console.ForneceDadosViaConsole;

public class Main {

	public static void main(String[] args) {
		
		new IniciaAplicacao().executa(new RecebeDadosViaConsole(), new RegraDoTabuleiroLocal(), new ForneceDadosViaConsole());		
	}

}
