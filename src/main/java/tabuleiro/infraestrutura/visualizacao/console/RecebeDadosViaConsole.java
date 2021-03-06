package tabuleiro.infraestrutura.visualizacao.console;

import tabuleiro.aplicacao.RecebeDados;
import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.infraestrutura.visualizacao.console.exception.SaindoDoSistemaException;

public class RecebeDadosViaConsole implements RecebeDados {

	private EntradaDoUsuario entradaDoUsuario = new EntradaDoUsuario();
	
	private final String SAIR = "sair";
	
	@Override
	public ConfiguracoesDoTabuleiro defineConfiguracoesDoTabuleiro() {
		try {
			System.out.println("BEM-VINDO!! Para encerrar o sistema, digite sair");
			int tamanhoDoTabuleiro = pegaTamanhoDoTabuleiro();
			int quantidadeDeRainhas = pegaQuantidadeDeRainhas();
			return new ConfiguracoesDoTabuleiro(tamanhoDoTabuleiro, quantidadeDeRainhas);			
		} catch (SaindoDoSistemaException e) {
			System.out.println("Voc? optou por sair, muito obrigado!");
			return new ConfiguracoesDoTabuleiro(0, 0);
		} 
		finally {
			this.entradaDoUsuario.fechaConexao();
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
			digitado = this.entradaDoUsuario.executa();
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

	
}
