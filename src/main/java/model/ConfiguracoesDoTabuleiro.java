package model;

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
	
}
