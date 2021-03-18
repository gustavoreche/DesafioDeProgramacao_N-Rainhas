package tabuleiro.infraestrutura.regra.local;

import java.util.Random;

import tabuleiro.aplicacao.regra.RegraDaRainha;
import tabuleiro.aplicacao.regra.RegraDoTabuleiro;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.EspacoVazio;
import tabuleiro.dominio.Tabuleiro;

public class RegraDoTabuleiroLocal implements RegraDoTabuleiro {
	
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	private String [][] tabuleiroComRainhas;
	private String [][] tabuleiroOcupado;
	private int vezesQueTabuleiroFoiReiniciado = 0;
	private int quantidadeMaximaDeRainhasVerificada = 0;
	private EspacoVazio espacoVazio = new EspacoVazio();
	private RegraDaRainha regraDaRainha = new RegraDaRainha();
	
	private final int QUANTIDADE_DE_TENTATIVA = 50;

	@Override
	public Tabuleiro cria(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro) {
		if(configuracoesDoTabuleiro.foiConfigurado()) {
			this.configuracoesDoTabuleiro = configuracoesDoTabuleiro;
			iniciaPreenchimento();
			return new Tabuleiro(this.tabuleiroComRainhas);
		}
		return new Tabuleiro(new String[0][0]);
	}

	private void iniciaPreenchimento() {
		this.tabuleiroComRainhas = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		this.tabuleiroOcupado = new String[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()]
				[this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()];
		int colunaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		int linhaInicial = new Random().nextInt(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro() - 1);
		EspacoVazio espacoVazio = this.espacoVazio.preencheEspacoVazio(colunaInicial, linhaInicial);
		int numeroDeRainhas = 0;
		do {
			this.regraDaRainha.executa(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro(), espacoVazio, 
					this.tabuleiroComRainhas, this.tabuleiroOcupado);
			espacoVazio = this.espacoVazio.verificaEspacosVazios(this.tabuleiroOcupado, 
					this.regraDaRainha.SIMBOLO_RAINHA, this.regraDaRainha.SIMBOLO_LOCAL_PROIBIDO);
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

}
