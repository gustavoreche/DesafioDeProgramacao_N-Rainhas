package view.console;

import java.util.Scanner;

import exception.SaindoDoSistemaException;
import model.ConfiguracoesDoTabuleiro;
import view.View;

public class ViewConsole implements View {

	private Scanner digitacaoDoUsuario;
	
	private final String SAIR = "sair";
	
	@Override
	public ConfiguracoesDoTabuleiro defineConfiguracoesDoTabuleiro() {
		try {
			System.out.println("BEM-VINDO!! Para sair, digite sair");
			this.digitacaoDoUsuario = new Scanner(System.in);
			int tamanhoDoTabuleiro = pegaTamanhoDoTabuleiro();
			int quantidadeDeRainhas = pegaQuantidadeDeRainhas();
			return new ConfiguracoesDoTabuleiro(tamanhoDoTabuleiro, quantidadeDeRainhas);			
		} catch (SaindoDoSistemaException e) {
			System.out.println("Você optou por sair, muito obrigado!");
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
		} while (tamanhoDoTabuleiro == 0);
		return tamanhoDoTabuleiro;
	}

	private int pegaValorInteiro(String mensagem) {
		int valor = 0;
		String digitado = "";
		try{
			System.out.print(mensagem);
			digitado = this.digitacaoDoUsuario.nextLine();
			valor = Integer.parseInt(digitado);
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
		} while (quantidadeDeRainha == 0);
		return quantidadeDeRainha;
	}

}
