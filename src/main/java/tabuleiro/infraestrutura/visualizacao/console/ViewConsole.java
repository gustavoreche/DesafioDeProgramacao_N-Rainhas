package tabuleiro.infraestrutura.visualizacao.console;

import java.util.Scanner;

import tabuleiro.aplicacao.visualizacao.View;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.infraestrutura.visualizacao.console.exception.SaindoDoSistemaException;

public class ViewConsole implements View {

	private Scanner digitacaoDoUsuario;
	
	private final String SAIR = "sair";
	private final String RAINHA = "R";
	private final String COM_RAINHA = "|R";
	private final String PROIBIDO = "|-";
	private final String SEM_RAINHA = "| ";
	
	@Override
	public ConfiguracoesDoTabuleiro defineConfiguracoesDoTabuleiro() {
		try {
			System.out.println("BEM-VINDO!! Para encerrar o sistema, digite sair");
			this.digitacaoDoUsuario = new Scanner(System.in);
			int tamanhoDoTabuleiro = pegaTamanhoDoTabuleiro();
			int quantidadeDeRainhas = pegaQuantidadeDeRainhas();
			return new ConfiguracoesDoTabuleiro(tamanhoDoTabuleiro, quantidadeDeRainhas);			
		} catch (SaindoDoSistemaException e) {
			System.out.println("Voc� optou por sair, muito obrigado!");
			return new ConfiguracoesDoTabuleiro(0, 0);
		} 
		finally {
			this.digitacaoDoUsuario.close();
		}
	}

	private int pegaTamanhoDoTabuleiro() {
		int tamanhoDoTabuleiro = 0;
		do {
			tamanhoDoTabuleiro = pegaValorInteiro("Digite o tamanho do tabuleiro: ");
		} while (tamanhoDoTabuleiro <= 1);
		return tamanhoDoTabuleiro;
	}

	private int pegaValorInteiro(String mensagem) {
		int valor = 0;
		String digitado = "";
		try{
			System.out.print(mensagem);
			digitado = this.digitacaoDoUsuario.nextLine();
			valor = Integer.parseInt(digitado);
			if(valor <= 1) {
				System.out.println("ERRO! Digite um numero MAIOR que 1");
			}
		} catch (Exception e) {
			saiDoSistema(digitado);
			System.out.println("ERRO! Numero valido");
		} 
		return valor;
	}

	private void saiDoSistema(String digitado) {
		if(SAIR.equalsIgnoreCase(digitado)) {
			throw new SaindoDoSistemaException();
		}
	}
	
	private int pegaQuantidadeDeRainhas() {
		int quantidadeDeRainha = 0;
		do {
			quantidadeDeRainha = pegaValorInteiro("Digite a quantidade de rainhas: ");
		} while (quantidadeDeRainha <= 1);
		return quantidadeDeRainha;
	}

	@Override
	public void exibeTabuleiro(String[][] tabuleiro) {
		if(tabuleiro.length > 1) {
			System.out.println("---------------------------------------");
			System.out.println("Tabuleiro:");
			for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
				for (int linha = 0; linha < tabuleiro.length; linha++) {
					if(RAINHA.equalsIgnoreCase(tabuleiro[coluna][linha])) {
						System.out.print(COM_RAINHA);
					} else {
						System.out.print(PROIBIDO);						
					}
				}
				System.out.println(SEM_RAINHA);
			}
		}
	}

	@Override
	public void exibeMensagemDeNumeroDeTentativas(int tentativasMaximas) {
		System.out.println("Numero de tentativas excedido. O maximo de rainhas que cabe nesse tabuleiro eh: "
				+ tentativasMaximas);
	}

}