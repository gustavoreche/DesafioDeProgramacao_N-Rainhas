package tabuleiro.aplicacao.regra;

import tabuleiro.aplicacao.visualizacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;

public interface RegraDoTabuleiro {
	
	String[][] cria(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro, View view);

}
