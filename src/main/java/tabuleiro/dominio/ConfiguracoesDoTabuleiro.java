package tabuleiro.dominio;

public class ConfiguracoesDoTabuleiro {
	
	private int tamanhoDoTabuleiro;
	private int quantidadeDeRainhas;
	private boolean numeroDeRainhasExcedido;
	private int quantidadeDeRainhasSuportadoPeloTabuleiro;
	
	public ConfiguracoesDoTabuleiro(int tamanhoDoTabuleiro, int quantidadeDeRainhas) {
		this.tamanhoDoTabuleiro = tamanhoDoTabuleiro;
		this.quantidadeDeRainhas = quantidadeDeRainhas;
		this.numeroDeRainhasExcedido = false;
		this.quantidadeDeRainhasSuportadoPeloTabuleiro = quantidadeDeRainhas;
	}
	
	public int getTamanhoDoTabuleiro() {
		return tamanhoDoTabuleiro;
	}
	
	public int getQuantidadeDeRainhas() {
		return quantidadeDeRainhas;
	}
	
	public boolean isNumeroDeRainhasExcedido() {
		return numeroDeRainhasExcedido;
	}
	
	public int getQuantidadeDeRainhasSuportadoPeloTabuleiro() {
		return quantidadeDeRainhasSuportadoPeloTabuleiro;
	}

	public boolean foiConfigurado() {
		return this.tamanhoDoTabuleiro != 0 && this.quantidadeDeRainhas != 0;
	}
	
	public void redefineQuantidadeDeRainhas(int quantidadeDeRainhas) {
		this.quantidadeDeRainhas = quantidadeDeRainhas;
	}

	public void exibeMensagemDeNumeroDeRainhasExcedido(int numeroDeRainhasSuportadoPeloTabuleiro) {
		this.numeroDeRainhasExcedido = true;
		this.quantidadeDeRainhasSuportadoPeloTabuleiro = numeroDeRainhasSuportadoPeloTabuleiro;
	}

}
