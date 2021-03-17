package tabuleiro.aplicacao.regra;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public interface RegraDoTabuleiro {
	
	Tabuleiro cria(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro);

}
