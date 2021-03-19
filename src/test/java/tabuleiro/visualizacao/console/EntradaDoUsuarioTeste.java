package tabuleiro.visualizacao.console;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import tabuleiro.infraestrutura.visualizacao.console.EntradaDoUsuario;

public class EntradaDoUsuarioTeste {
	
	private final String DIGITACAO_DO_USUARIO = "Teste";
	
	@Test
	public void fluxoDeDigitacaoDoUsuarioEFechaConexaoDaDigitacao() {
		InputStream entrada = new ByteArrayInputStream(DIGITACAO_DO_USUARIO.getBytes());
		System.setIn(entrada);
		EntradaDoUsuario entradaDoUsuario = new EntradaDoUsuario();
	    String digitacao = entradaDoUsuario.executa();
	    entradaDoUsuario.fechaConexao();
	    assertEquals(DIGITACAO_DO_USUARIO, digitacao);
	}

}
