package tabuleiro.dominio;

public class EspacoVazio {
	
	private int coluna;
	private int linha;
	
	public EspacoVazio(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
}
