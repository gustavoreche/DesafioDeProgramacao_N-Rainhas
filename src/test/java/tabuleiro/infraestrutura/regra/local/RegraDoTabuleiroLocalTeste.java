package tabuleiro.infraestrutura.regra.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import tabuleiro.dominio.ConfiguracoesDoTabuleiro;
import tabuleiro.dominio.EspacoVazio;
import tabuleiro.dominio.Tabuleiro;
import tabuleiro.infraestrutura.visualizacao.console.EntradaDoUsuario;
import tabuleiro.infraestrutura.visualizacao.console.ViewConsole;

public class RegraDoTabuleiroLocalTeste {
	
	@InjectMocks
	private RegraDoTabuleiroLocal regraDoTabuleiroLocal = new RegraDoTabuleiroLocal();
	
	@InjectMocks
	private ViewConsole view = new ViewConsole();
	
	@Mock
	private EntradaDoUsuario entradaDoUsuario = new EntradaDoUsuario();
	
	@Mock
	private EspacoVazio espacoVazio;

	@Mock
	private ConfiguracoesDoTabuleiro configuracoesDoTabuleiro;
	
	@Mock
	private Tabuleiro tabuleiro;
	
	private final String TAMANHO_DO_TABULEIRO_RECUSADO = "1";
	private final String TAMANHO_DO_TABULEIRO = "4";

	private final String QUANTIDADE_DE_RAINHA_RECUSADA = "1";
	private final String QUANTIDADE_DE_RAINHA = "2";
	
	private final String SAIR_DO_SISTEMA = "sair";
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void finish() {
		System.out.println("---------------------------------------------------------");
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroInvalido_devePedirNovamenteEConfigurar() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO_RECUSADO, TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA);
		fluxoDoSistema();
	}

	private void fluxoDoSistema() {
		ConfiguracoesDoTabuleiro configuracoesDoTabuleiro = view.defineConfiguracoesDoTabuleiro();
		Tabuleiro tabuleiro = new RegraDoTabuleiroLocal().cria(configuracoesDoTabuleiro);
		view.informaSeTeveNumeroDeRainhasEmExcesso(configuracoesDoTabuleiro);
		view.exibeTabuleiro(tabuleiro);
	}
	
	@Test
	public void fluxoComQuantidadeDeRainhasInvalidas_devePedirNovamenteEConfigurar() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA_RECUSADA, QUANTIDADE_DE_RAINHA);
		fluxoDoSistema();
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroInvalidoEQuantidadeDeRainhasInvalidas_devePedirNovamenteOsDoisItensEConfigurar() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO_RECUSADO, TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA_RECUSADA, QUANTIDADE_DE_RAINHA);
		fluxoDoSistema();
	}
	
	@Test
	public void fluxoDesejandoSairNaPerguntaDoTamanhoDoTabuleiro_deveEncerrarOSistema() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(SAIR_DO_SISTEMA);
		fluxoDoSistema();
	}
	
	@Test
	public void fluxoDesejandoSairNaPerguntaDaQuantidadeDeRainhas_deveEncerrarOSistema() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO, SAIR_DO_SISTEMA);
		fluxoDoSistema();
	}
	
}
