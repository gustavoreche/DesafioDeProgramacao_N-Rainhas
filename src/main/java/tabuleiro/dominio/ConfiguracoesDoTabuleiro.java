package tabuleiro.dominio;

public class ConfiguracoesDoTabuleiro {
	
	private int tamanhoDoTabuleiro;
	private int quantidadeDeRainhas;
	
	public ConfiguracoesDoTabuleiro(int tamanhoDoTabuleiro, int quantidadeDeRainhas) {
		this.tamanhoDoTabuleiro = tamanhoDoTabuleiro;
		this.quantidadeDeRainhas = quantidadeDeRainhas;
	}
	
	public int getTamanhoDoTabuleiro() {
		return tamanhoDoTabuleiro;
	}
	
	public int getQuantidadeDeRainhas() {
		return quantidadeDeRainhas;
	}

	public boolean foiConfigurado() {
		return this.tamanhoDoTabuleiro != 0 && this.quantidadeDeRainhas != 0;
	}
	
	public void setQuantidadeDeRainhas(int quantidadeDeRainhas) {
		this.quantidadeDeRainhas = quantidadeDeRainhas;
	}

}
