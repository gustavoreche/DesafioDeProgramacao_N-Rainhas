package tabuleiro.infraestrutura.regra.local;

import java.util.Random;

import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.aplicacao.visualizacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.EspacoVazio;

public class RegraDoTabuleiroLocal implements RegraDoTabuleiro {
	
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	private View view;
	private String [][] tabuleiro;
	private String [][] tabuleiroOcupado;
	private int reiniciaTabuleiro = 0;
	private int quantidadeMaximaVerificada = 0;
	private boolean encerra = false;
	private final int QUANTIDADE_DE_TENTATIVA = 50;

	@Override
	public String[][] cria(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro, View view) {
		if(configuracoesDoTabuleiro.foiConfigurado()) {
			this.configuracoesDoTabuleiro = configuracoesDoTabuleiro;
			this.view = view;
			iniciaPreenchimento();
			return this.tabuleiro;
		}
		return new String[0][0];
	}

	private void iniciaPreenchimento() {
		this.tabuleiro = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		this.tabuleiroOcupado = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		int colunaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		int linhaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		EspacoVazio espacoVazio = new EspacoVazio(colunaInicial, linhaInicial);
		int numeroDeRainhas = 0;
		do {
			preencheOsCamposComAsRainhas(espacoVazio.getColuna(), espacoVazio.getLinha());
			espacoVazio = verificaEspacosVazios();
			numeroDeRainhas++;
		} while (espacoVazio.getColuna() != -1 && espacoVazio.getLinha() != -1 && 
				this.configuracoesDoTabuleiro.getQuantidadeDeRainhas() > numeroDeRainhas);
		if(numeroDeRainhas < this.configuracoesDoTabuleiro.getQuantidadeDeRainhas()) {
			verificaQuantidadeMaximaDeRainhas(numeroDeRainhas);
		}
	}

	private void verificaQuantidadeMaximaDeRainhas(int numeroDeRainhas) {
		if(this.quantidadeMaximaVerificada < numeroDeRainhas) {
			this.quantidadeMaximaVerificada = numeroDeRainhas;
		}
		this.reiniciaTabuleiro++;
		if(this.reiniciaTabuleiro < QUANTIDADE_DE_TENTATIVA) {
			iniciaPreenchimento();				
		} else if(this.encerra) {
			this.view.exibeMensagemDeNumeroDeTentativas(this.quantidadeMaximaVerificada);
		} else {
			this.view.exibeMensagemDeNumeroDeTentativas(this.quantidadeMaximaVerificada);
			this.reiniciaTabuleiro = 0;
			this.configuracoesDoTabuleiro.setQuantidadeDeRainhas(this.quantidadeMaximaVerificada);
			this.encerra = true;
			iniciaPreenchimento();
		}
	}

	private void preencheOsCamposComAsRainhas(int colunaInicial, int linhaInicial) {
		this.tabuleiro[colunaInicial][linhaInicial] = "R";
		this.tabuleiroOcupado[colunaInicial][linhaInicial] = "R";
		for (int tamanho = 0; tamanho < this.tabuleiro.length; tamanho++) {
			this.tabuleiroOcupado[colunaInicial][tamanho] = "-";
			this.tabuleiroOcupado[tamanho][linhaInicial] = "-";
		}
		verificaLadoEsquerdoESuperior(colunaInicial, linhaInicial);
		verificaLadoEsquerdoEInferior(colunaInicial, linhaInicial);
		verificaLadoDireitoESuperior(colunaInicial, linhaInicial);
		verificaLadoDireitoEInferior(colunaInicial, linhaInicial);
	}

	private void verificaLadoEsquerdoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial -1] = "-";
			verificaLadoEsquerdoESuperior(colunaInicial -1, linhaInicial -1);
		}
	}
	
	private void verificaLadoEsquerdoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1 
				&& linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial -1] = "-";
			verificaLadoEsquerdoEInferior(colunaInicial +1, linhaInicial -1);
		}
	}
	
	private void verificaLadoDireitoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial +1] = "-";
			verificaLadoDireitoESuperior(colunaInicial -1, linhaInicial +1);
		}
	}
	
	private void verificaLadoDireitoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1 
				&& linhaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial +1] = "-";
			verificaLadoDireitoEInferior(colunaInicial +1, linhaInicial +1);
		}
	}
	
	private EspacoVazio verificaEspacosVazios() {
		for (int coluna = 0; coluna < this.tabuleiroOcupado.length; coluna++) {
			for (int linha = 0; linha < this.tabuleiroOcupado.length; linha++) {
				if(!"R".equalsIgnoreCase(tabuleiroOcupado[coluna][linha]) && 
						!"-".equalsIgnoreCase(tabuleiroOcupado[coluna][linha])) {
					return new EspacoVazio(coluna, linha);
				}
			}
		}
		return new EspacoVazio(-1, -1);
	}

}
