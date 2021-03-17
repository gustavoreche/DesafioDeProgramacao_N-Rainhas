package tabuleiro.dominio;

public class EspacoVazio {
	
	private int coluna;
	private int linha;
	
	public EspacoVazio(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public EspacoVazio() {
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	public EspacoVazio preencheEspacoVazio(int colunaInicial, int linhaInicial) {
		return new EspacoVazio(colunaInicial, linhaInicial);
	}
	
	public EspacoVazio verificaEspacosVazios(String [][] tabuleiroOcupado, String simboloRainha, String simboloLocalProibido) {
		for (int coluna = 0; coluna < tabuleiroOcupado.length; coluna++) {
			for (int linha = 0; linha < tabuleiroOcupado.length; linha++) {
				if(!simboloRainha.equalsIgnoreCase(tabuleiroOcupado[coluna][linha]) && 
						!simboloLocalProibido.equalsIgnoreCase(tabuleiroOcupado[coluna][linha])) {
					return this.preencheEspacoVazio(coluna, linha);
				}
			}
		}
		return this.preencheEspacoVazio(-1, -1);
	}
	
}
