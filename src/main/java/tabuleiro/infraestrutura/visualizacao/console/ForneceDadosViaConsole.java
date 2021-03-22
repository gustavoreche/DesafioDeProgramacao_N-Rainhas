package tabuleiro.infraestrutura.visualizacao.console;

import tabuleiro.aplicacao.ForneceInformacoes;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.Tabuleiro;

public class ForneceDadosViaConsole implements ForneceInformacoes {

	private final String DIVISAO_DO_TABULEIRO = "|";
	
	@Override
	public void exibeTabuleiro(Tabuleiro tabuleiro) {
		if(tabuleiro.getTabuleiroPreenchido().length > 1) {
			System.out.println("---------------------------------------");
			System.out.println("Tabuleiro:");
			for (int coluna = 0; coluna < tabuleiro.getTabuleiroPreenchido().length; coluna++) {
				for (int linha = 0; linha < tabuleiro.getTabuleiroPreenchido().length; linha++) {
					System.out.print(DIVISAO_DO_TABULEIRO.concat(tabuleiro.getTabuleiroPreenchido()[coluna][linha]));						
				}
				System.out.print(DIVISAO_DO_TABULEIRO);
				System.out.println();
			}
		}
	}

	@Override
	public void informaSeTeveNumeroDeRainhasEmExcesso(ConfiguracoesDoTabuleiro configuracoesDoTabuleiro) {
		if(configuracoesDoTabuleiro.isNumeroDeRainhasExcedido()) {
			System.out.println("Numero de tentativas excedido. O maximo de rainhas que cabe nesse tabuleiro eh: "
					+ configuracoesDoTabuleiro.getQuantidadeDeRainhasSuportadoPeloTabuleiro());			
		}
	}

}
