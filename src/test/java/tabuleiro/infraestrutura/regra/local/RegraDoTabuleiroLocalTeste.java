package tabuleiro.infraestrutura.regra.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import tabuleiro.IniciaAplicacao;
import tabuleiro.infraestrutura.visualizacao.console.EntradaDoUsuario;
import tabuleiro.infraestrutura.visualizacao.console.ViewConsole;

public class RegraDoTabuleiroLocalTeste {
	
	@InjectMocks
	private RegraDoTabuleiroLocal regraDoTabuleiroLocal = new RegraDoTabuleiroLocal();
	
	@InjectMocks
	private ViewConsole view = new ViewConsole();
	
	@InjectMocks
	private IniciaAplicacao iniciaAplicacao = new IniciaAplicacao();
	
	@Mock
	private EntradaDoUsuario entradaDoUsuario = new EntradaDoUsuario();
	
	private final String TAMANHO_DO_TABULEIRO_RECUSADO = "1";
	private final String TAMANHO_DO_TABULEIRO = "4";

	private final String QUANTIDADE_DE_RAINHA_RECUSADA = "1";
	private final String QUANTIDADE_DE_RAINHA = "2";
	private final String QUANTIDADE_DE_RAINHA_EXCEDIDA = TAMANHO_DO_TABULEIRO.concat("0");
	
	private final String TABULEIRO_NAO_CONFIGURADO = "0";
	
	private final String SAIR_DO_SISTEMA = "sair";
	
	private final String DIGITA_LETRA = "aaaa";	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void finish() {
		System.out.println("---------------------------------------------------------");
	}
	
	@Test
	public void fluxoComTudoValido_deveConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroInvalido_devePedirNovamenteEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO_RECUSADO, TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComQuantidadeDeRainhasInvalidas_devePedirNovamenteEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA_RECUSADA, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroInvalidoEQuantidadeDeRainhasInvalidas_devePedirNovamenteOsDoisItensEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO_RECUSADO, TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA_RECUSADA, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoDesejandoSairNaPerguntaDoTamanhoDoTabuleiro_deveEncerrarOSistema() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(SAIR_DO_SISTEMA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoDesejandoSairNaPerguntaDaQuantidadeDeRainhas_deveEncerrarOSistema() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO, SAIR_DO_SISTEMA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TABULEIRO_NAO_CONFIGURADO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroDigitandoSemSerNumero_devePedirNovamenteEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(DIGITA_LETRA, TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComQuantidadeDeRainhasDigitandoSemSerNumero_devePedirNovamenteEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO,
					DIGITA_LETRA, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComTamanhoDoTabuleiroDigitandoSemSerNumeroEQuantidadeDeRainhasDigitandoSemSerNumero_devePedirNovamenteOsDoisItensEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(DIGITA_LETRA, TAMANHO_DO_TABULEIRO,
					DIGITA_LETRA, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertEquals(QUANTIDADE_DE_RAINHA, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getQuantidadeDeRainhas()));
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
	@Test
	public void fluxoComQuantidadeDeRainhasAMaisQueOTabuleiroSuporta_deveInformarQueONumeroFoiExcedidoEConfigurarTabuleiro() {
		Mockito.when(this.entradaDoUsuario.executa())
			.thenReturn(TAMANHO_DO_TABULEIRO,
					QUANTIDADE_DE_RAINHA_EXCEDIDA, QUANTIDADE_DE_RAINHA);
		
		this.iniciaAplicacao.executa(this.view, this.regraDoTabuleiroLocal);
		
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().getTamanhoDoTabuleiro()));
		assertTrue(this.iniciaAplicacao.getConfiguracoesDoTabuleiro().isNumeroDeRainhasExcedido());
		assertEquals(TAMANHO_DO_TABULEIRO, String.valueOf(this.iniciaAplicacao.getTabuleiro().getTabuleiroPreenchido().length));
	}
	
}
