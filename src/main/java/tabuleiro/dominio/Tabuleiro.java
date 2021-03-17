package tabuleiro.dominio;

public class Tabuleiro {
	
	private String[][] tabuleiroPreenchido;
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	
	public Tabuleiro(String[][] tabuleiroPreenchido, ConfiguracoesDoTabuleiro configuracoesDoTabuleiro) {
		super();
		this.tabuleiroPreenchido = retiraEspacosNullDoTabuleiro(tabuleiroPreenchido);
		this.configuracoesDoTabuleiro = configuracoesDoTabuleiro;
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
	
	public ConfiguracoesDoTabuleiro getConfiguracoesDoTabuleiro() {
		return configuracoesDoTabuleiro;
	}
	
}
