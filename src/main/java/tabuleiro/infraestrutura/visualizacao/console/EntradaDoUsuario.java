package tabuleiro.infraestrutura.visualizacao.console;

import java.util.Scanner;

public class EntradaDoUsuario {
	
	private Scanner digitacaoDoUsuario = new Scanner(System.in);
	
	public String executa() {
		return this.digitacaoDoUsuario.nextLine();
	}
	
	public void fechaConexao() {
		this.digitacaoDoUsuario.close();
	}

}
