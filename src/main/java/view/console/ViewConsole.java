package view.console;

import java.util.Scanner;

import view.View;

public class ViewConsole implements View {

	boolean caracterInvalido = false;
	
	@Override
	public int defineTamanhoDoTabuleiro() {
		int tamanhoDoTabuleiro = 0;
		do {
			tamanhoDoTabuleiro = pegaTamanho();
		} while (this.caracterInvalido);
		System.out.print("");
		return tamanhoDoTabuleiro;
	}

	private int pegaTamanho() {
		int tamanhoDoTabuleiro = 0;
		boolean encerra = false;
		Scanner tamanhoEscolhido = null;
		try{
			System.out.print("Digite o tamanho do tabuleiro: ");
			tamanhoEscolhido = new Scanner(System.in);
			tamanhoDoTabuleiro = Integer.parseInt(tamanhoEscolhido.nextLine());
			this.caracterInvalido = false;
			encerra = true;
		} catch (Exception e) {
			System.out.println("ERRO! Numero valido");
			this.caracterInvalido = true;
		} finally {
			if(encerra) {
				tamanhoEscolhido.close();				
			}
		}
		return tamanhoDoTabuleiro;
	}

}
