package tabuleiro.aplicacao.regra;

import tabuleiro.dominio.EspacoVazio;

public class RegraDaRainha {
	
	private int tamanhoDoTabuleiro;
	private String [][] tabuleiroComRainhas;
	private String [][] tabuleiroOcupado;
	
	public final String SIMBOLO_RAINHA = "R";
	public final String SIMBOLO_LOCAL_PROIBIDO = "-";
	
	public void executa(int tamanhoDoTabuleiro, EspacoVazio espacoVazio, 
			String [][] tabuleiroComRainhas, String [][] tabuleiroOcupado) {
		this.tamanhoDoTabuleiro = tamanhoDoTabuleiro;
		this.tabuleiroComRainhas = tabuleiroComRainhas;
		this.tabuleiroOcupado = tabuleiroOcupado;
		
		this.tabuleiroComRainhas[espacoVazio.getColuna()][espacoVazio.getLinha()] = SIMBOLO_RAINHA;
		this.tabuleiroOcupado[espacoVazio.getColuna()][espacoVazio.getLinha()] = SIMBOLO_RAINHA;
		for (int tamanho = 0; tamanho < this.tabuleiroComRainhas.length; tamanho++) {
			this.tabuleiroOcupado[espacoVazio.getColuna()][tamanho] = SIMBOLO_LOCAL_PROIBIDO;
			this.tabuleiroOcupado[tamanho][espacoVazio.getLinha()] = SIMBOLO_LOCAL_PROIBIDO;
		}
		verificaLadoEsquerdoESuperior(espacoVazio.getColuna(), espacoVazio.getLinha());
		verificaLadoEsquerdoEInferior(espacoVazio.getColuna(), espacoVazio.getLinha());
		verificaLadoDireitoESuperior(espacoVazio.getColuna(), espacoVazio.getLinha());
		verificaLadoDireitoEInferior(espacoVazio.getColuna(), espacoVazio.getLinha());
	}
	
	private void verificaLadoEsquerdoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial -1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoEsquerdoESuperior(colunaInicial -1, linhaInicial -1);
		}
	}
	
	private void verificaLadoEsquerdoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= this.tamanhoDoTabuleiro -1 && linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial -1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoEsquerdoEInferior(colunaInicial +1, linhaInicial -1);
		}
	}
	
	private void verificaLadoDireitoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial +1 <= this.tamanhoDoTabuleiro -1) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial +1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoDireitoESuperior(colunaInicial -1, linhaInicial +1);
		}
	}
	
	private void verificaLadoDireitoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= this.tamanhoDoTabuleiro -1 && linhaInicial +1 <= this.tamanhoDoTabuleiro -1) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial +1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoDireitoEInferior(colunaInicial +1, linhaInicial +1);
		}
	}
	
}
