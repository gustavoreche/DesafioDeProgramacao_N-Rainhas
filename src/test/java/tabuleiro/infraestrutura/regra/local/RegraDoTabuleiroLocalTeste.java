package tabuleiro.infraestrutura.regra.local;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.EspacoVazio;
import tabuleiro.dominio.Tabuleiro;

public class RegraDoTabuleiroLocalTeste {
	
	@InjectMocks
	private RegraDoTabuleiroLocal regraDoTabuleiroLocal = new RegraDoTabuleiroLocal();
	
	@Mock
	private EspacoVazio espacoVazio;

	@Mock
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	
	private final int TAMANHO_DO_TABULEIRO = 4;
	private final int QUANTIDADE_DE_RAINHA = 3;
	private final int ESPACO_VAZIO_ENCONTRADO = 1;
	private final int ESPACO_VAZIO_NAO_ENCONTRADO = -1;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void cria_tabuleiroNaoConfigurado() {
		Tabuleiro tabuleiro = this.regraDoTabuleiroLocal
				.cria(configuracoesDoTabuleiro);
		
		Assert.assertEquals(0, tabuleiro.getTabuleiroPreenchido().length);
	}
	
	@Test
	public void cria_tabuleiroConfigurado() {
		EspacoVazio espacoVazio = Mockito.mock(EspacoVazio.class);
		Mockito.when(this.espacoVazio.preencheEspacoVazio(Matchers.anyInt(), Matchers.anyInt())).thenReturn(espacoVazio);
		Mockito.when(this.espacoVazio.verificaEspacosVazios(Matchers.any(String[][].class), Matchers.anyString(), Matchers.anyString()))
			.thenReturn(espacoVazio);
		Mockito.when(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()).thenReturn(TAMANHO_DO_TABULEIRO);
		Mockito.when(this.configuracoesDoTabuleiro.getQuantidadeDeRainhas()).thenReturn(QUANTIDADE_DE_RAINHA);
		Mockito.when(this.configuracoesDoTabuleiro.foiConfigurado()).thenReturn(true);
		
		Tabuleiro tabuleiro = this.regraDoTabuleiroLocal
				.cria(configuracoesDoTabuleiro);
		
		Assert.assertEquals(TAMANHO_DO_TABULEIRO, (tabuleiro.getTabuleiroPreenchido().length));
	}
	
	@Test
	public void cria_tabuleiroConfigurado_numeroDeRainhasEhMenorQueOEscolhido() {
		EspacoVazio espacoVazioPrimeiroRetorno = Mockito.mock(EspacoVazio.class);
		EspacoVazio espacoVazioSegundoRetorno = Mockito.mock(EspacoVazio.class);
		
		Mockito.when(espacoVazioPrimeiroRetorno.getColuna()).thenReturn(ESPACO_VAZIO_ENCONTRADO);
		Mockito.when(espacoVazioPrimeiroRetorno.getLinha()).thenReturn(ESPACO_VAZIO_ENCONTRADO);
		Mockito.when(espacoVazioSegundoRetorno.getColuna()).thenReturn(ESPACO_VAZIO_NAO_ENCONTRADO);
		Mockito.when(espacoVazioSegundoRetorno.getLinha()).thenReturn(ESPACO_VAZIO_NAO_ENCONTRADO);
		
		Mockito.when(this.espacoVazio.preencheEspacoVazio(Matchers.anyInt(), Matchers.anyInt())).thenReturn(espacoVazioPrimeiroRetorno);
		Mockito.when(this.espacoVazio.verificaEspacosVazios(Matchers.any(String[][].class), Matchers.anyString(), Matchers.anyString()))
			.thenReturn(espacoVazioPrimeiroRetorno, espacoVazioSegundoRetorno, espacoVazioPrimeiroRetorno);
		Mockito.when(this.configuracoesDoTabuleiro.getTamanhoDoTabuleiro()).thenReturn(TAMANHO_DO_TABULEIRO);
		Mockito.when(this.configuracoesDoTabuleiro.getQuantidadeDeRainhas()).thenReturn(5);
		Mockito.when(this.configuracoesDoTabuleiro.foiConfigurado()).thenReturn(true);
		
		Tabuleiro tabuleiro = this.regraDoTabuleiroLocal
				.cria(configuracoesDoTabuleiro);
		
		Assert.assertEquals(TAMANHO_DO_TABULEIRO, (tabuleiro.getTabuleiroPreenchido().length));
	}

}
