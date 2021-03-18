package tabuleiro.dominio;

public class Tabuleiro {
	
	private String[][] tabuleiroPreenchido;
	
	public Tabuleiro(String[][] tabuleiroPreenchido) {
		super();
		this.tabuleiroPreenchido = retiraEspacosNullDoTabuleiro(tabuleiroPreenchido);
	}
	
	private String[][] retiraEspacosNullDoTabuleiro(String[][] tabuleiroPreenchido) {
		if(tabuleiroPreenchido.length > 1) {
			for (int coluna = 0; coluna < tabuleiroPreenchido.length; coluna++) {
				for (int linha = 0; linha < tabuleiroPreenchido.length; linha++) {
					tabuleiroPreenchido[coluna][linha] = tabuleiroPreenchido[coluna][linha] == null 
							? "-" : "R"; 
				}
			}
		}
		return tabuleiroPreenchido;
	}

	public String[][] getTabuleiroPreenchido() {
		return tabuleiroPreenchido;
	}
	
}
