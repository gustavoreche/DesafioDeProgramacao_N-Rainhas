package main;

import view.View;
import view.console.ViewConsole;

public class Main {

	public static void main(String[] args) {
		
		View view = new ViewConsole();
		
		int tamanhoDoTabuleiro = view.defineTamanhoDoTabuleiro();
		
		System.out.println();
	}

}
