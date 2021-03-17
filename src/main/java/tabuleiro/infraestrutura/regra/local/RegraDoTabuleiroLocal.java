package tabuleiro.infraestrutura.regra.local;

import java.util.Random;

import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.EspacoVazio;
import tabuleiro.dominio.Tabuleiro;

public class RegraDoTabuleiroLocal implements RegraDoTabuleiro {
	
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	private String [][] tabuleiro;
	private String [][] tabuleiroOcupado;
	private int vezesQueTabuleiroFoiReiniciado = 0;
	private int quantidadeMaximaDeRainhasVerificada = 0;
	private EspacoVazio espacoVazio = new EspacoVazio();
	
	private final int QUANTIDADE_DE_TENTATIVA = 50;
	private final String SIMBOLO_RAINHA = "R";
	private final String SIMBOLO_LOCAL_PROIBIDO = "-";

	@Override
	public Tabuleiro cria(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro) {
		if(configuracoesDoTabuleiro.foiConfigurado()) {
			this.configuracoesDoTabuleiro = configuracoesDoTabuleiro;
			iniciaPreenchimento();
			return new Tabuleiro(this.tabuleiro, configuracoesDoTabuleiro);
		}
		return new Tabuleiro(new String[0][0], configuracoesDoTabuleiro);
	}

	private void iniciaPreenchimento() {
		this.tabuleiro = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		this.tabuleiroOcupado = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		int colunaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		int linhaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		EspacoVazio espacoVazio = this.espacoVazio.preencheEspacoVazio(colunaInicial, linhaInicial);
		int numeroDeRainhas = 0;
		do {
			preencheOsCamposComAsRainhas(espacoVazio.getColuna(), espacoVazio.getLinha());
			espacoVazio = this.espacoVazio.verificaEspacosVazios(this.tabuleiroOcupado, SIMBOLO_RAINHA, SIMBOLO_LOCAL_PROIBIDO);
			numeroDeRainhas++;
		} while (espacoVazio.getColuna() != -1 && espacoVazio.getLinha() != -1 && 
				this.configuracoesDoTabuleiro.getQuantidadeDeRainhas() > numeroDeRainhas);
		if(numeroDeRainhas < this.configuracoesDoTabuleiro.getQuantidadeDeRainhas()) {
			verificaQuantidadeMaximaDeRainhas(numeroDeRainhas);
		}
	}

	private void verificaQuantidadeMaximaDeRainhas(int numeroDeRainhas) {
		if(this.quantidadeMaximaDeRainhasVerificada < numeroDeRainhas) {
			this.quantidadeMaximaDeRainhasVerificada = numeroDeRainhas;
		}
		this.vezesQueTabuleiroFoiReiniciado++;
		if(this.vezesQueTabuleiroFoiReiniciado >= QUANTIDADE_DE_TENTATIVA) {
			this.configuracoesDoTabuleiro.exibeMensagemDeNumeroDeRainhasExcedido(this.quantidadeMaximaDeRainhasVerificada);
			this.vezesQueTabuleiroFoiReiniciado = 0;
			this.configuracoesDoTabuleiro.redefineQuantidadeDeRainhas(this.quantidadeMaximaDeRainhasVerificada);
		}
		iniciaPreenchimento();
	}

	private void preencheOsCamposComAsRainhas(int colunaInicial, int linhaInicial) {
		this.tabuleiro[colunaInicial][linhaInicial] = SIMBOLO_RAINHA;
		this.tabuleiroOcupado[colunaInicial][linhaInicial] = SIMBOLO_RAINHA;
		for (int tamanho = 0; tamanho < this.tabuleiro.length; tamanho++) {
			this.tabuleiroOcupado[colunaInicial][tamanho] = SIMBOLO_LOCAL_PROIBIDO;
			this.tabuleiroOcupado[tamanho][linhaInicial] = SIMBOLO_LOCAL_PROIBIDO;
		}
		verificaLadoEsquerdoESuperior(colunaInicial, linhaInicial);
		verificaLadoEsquerdoEInferior(colunaInicial, linhaInicial);
		verificaLadoDireitoESuperior(colunaInicial, linhaInicial);
		verificaLadoDireitoEInferior(colunaInicial, linhaInicial);
	}

	private void verificaLadoEsquerdoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial -1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoEsquerdoESuperior(colunaInicial -1, linhaInicial -1);
		}
	}
	
	private void verificaLadoEsquerdoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1 
				&& linhaInicial -1 >= 0) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial -1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoEsquerdoEInferior(colunaInicial +1, linhaInicial -1);
		}
	}
	
	private void verificaLadoDireitoESuperior(int colunaInicial, int linhaInicial) {
		if(colunaInicial -1 >= 0 && linhaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1) {
			this.tabuleiroOcupado[colunaInicial -1][linhaInicial +1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoDireitoESuperior(colunaInicial -1, linhaInicial +1);
		}
	}
	
	private void verificaLadoDireitoEInferior(int colunaInicial, int linhaInicial) {
		if(colunaInicial +1 <= configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1 
				&& linhaInicial +1 <= this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() -1) {
			this.tabuleiroOcupado[colunaInicial +1][linhaInicial +1] = SIMBOLO_LOCAL_PROIBIDO;
			verificaLadoDireitoEInferior(colunaInicial +1, linhaInicial +1);
		}
	}
	
}
