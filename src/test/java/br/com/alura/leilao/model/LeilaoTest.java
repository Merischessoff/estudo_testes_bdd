package br.com.alura.leilao.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

public class LeilaoTest {

	@Nested
    @DisplayName("Dado um lance válido")
	@Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        BigDecimal doisMil = new BigDecimal("2000.0");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
	
	@Test
    public void naoDeveAceitarUmLanceIgualAoAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        BigDecimal doisMil = new BigDecimal("2000.0");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
		leilao.propoe(new Lance(new Usuario("Bill Gates"), doisMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
	
	
	@Test
    public void naoDeveAceitarUmLanceMenorAoAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        BigDecimal doisMil = new BigDecimal("2000.0");
        BigDecimal quaseDoisMil = new BigDecimal("1999.9");

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
		leilao.propoe(new Lance(new Usuario("Bill Gates"), quaseDoisMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
	
    @Test
    public void deveReceberVariosLances() {
    	
    	BigDecimal doisMil = new BigDecimal("2000.0");
    	BigDecimal tresMil = new BigDecimal("3000.0");
    	
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), tresMil));

        assertEquals(2, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
        assertEquals(tresMil, leilao.getLances().get(1).getValor());
    }
    
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
    	BigDecimal doisMil = new BigDecimal("2000.0");
    	BigDecimal tresMil = new BigDecimal("3000.0");
    	
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steveJobs, doisMil));
        leilao.propoe(new Lance(steveJobs, tresMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
    
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, new BigDecimal("2000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("3000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("4000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("5000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("6000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("7000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("8000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("9000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("10000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("11000.0")));

        // deve ser ignorado
        leilao.propoe(new Lance(steveJobs, new BigDecimal("12000.0")));

        assertEquals(10, leilao.getLances().size());
        assertEquals(new BigDecimal("11000.0"), leilao.getLances().get(leilao.getLances().size()-1).getValor());
    }

    @Test
    public void deveRetornarTrueEAceitarPrimeiroLance() {
        Leilao leilao = new Leilao("iPhone 15");
        BigDecimal mil = new BigDecimal("1000.0");

        boolean aceito = leilao.propoe(new Lance(new Usuario("Alice"), mil));

        assertTrue(aceito);
        assertEquals(1, leilao.getLances().size());
        assertEquals(mil, leilao.getLances().get(0).getValor());
    }

    @Test
    public void deveRetornarFalseQuandoValorDoLanceNaoForMaiorQueAnterior() {
        Leilao leilao = new Leilao("PlayStation 5");
        BigDecimal doisMil = new BigDecimal("2000.0");
        BigDecimal igual = new BigDecimal("2000.0");
        BigDecimal menor = new BigDecimal("1999.9");

        assertTrue(leilao.propoe(new Lance(new Usuario("Bob"), doisMil)));
        assertFalse(leilao.propoe(new Lance(new Usuario("Carol"), igual))); // igual deve ser rejeitado
        assertFalse(leilao.propoe(new Lance(new Usuario("Dave"), menor))); // menor deve ser rejeitado

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }

    @Test
    public void deveRetornarFalseParaDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Nintendo Switch");
        Usuario eve = new Usuario("Eve");
        BigDecimal mil = new BigDecimal("1000.0");
        BigDecimal doisMil = new BigDecimal("2000.0");

        assertTrue(leilao.propoe(new Lance(eve, mil)));
        boolean aceito = leilao.propoe(new Lance(eve, doisMil));

        assertFalse(aceito);
        assertEquals(1, leilao.getLances().size());
        assertEquals(mil, leilao.getLances().get(0).getValor());
    }

    @Test
    public void devePermitirAteCincoLancesDoMesmoUsuarioMasRejeitarOSexto() {
        Leilao leilao = new Leilao("Macbook Air");
        Usuario ana = new Usuario("Ana");
        Usuario bruno = new Usuario("Bruno");

        // alterna lances para permitir que Ana alcance 5 lances válidos
        assertTrue(leilao.propoe(new Lance(ana, new BigDecimal("1000.0"))));
        assertTrue(leilao.propoe(new Lance(bruno, new BigDecimal("1100.0"))));
        assertTrue(leilao.propoe(new Lance(ana, new BigDecimal("1200.0"))));
        assertTrue(leilao.propoe(new Lance(bruno, new BigDecimal("1300.0"))));
        assertTrue(leilao.propoe(new Lance(ana, new BigDecimal("1400.0"))));
        assertTrue(leilao.propoe(new Lance(bruno, new BigDecimal("1500.0"))));
        assertTrue(leilao.propoe(new Lance(ana, new BigDecimal("1600.0"))));
        assertTrue(leilao.propoe(new Lance(bruno, new BigDecimal("1700.0"))));
        assertTrue(leilao.propoe(new Lance(ana, new BigDecimal("1800.0")))); // 5º lance da Ana
        assertTrue(leilao.propoe(new Lance(bruno, new BigDecimal("1900.0"))));

        // 6º lance da Ana deve ser rejeitado
        boolean aceito = leilao.propoe(new Lance(ana, new BigDecimal("2000.0")));
        assertFalse(aceito);
    }

    @Test
    public void listaDeLancesDeveSerImutavel() {
        Leilao leilao = new Leilao("Kindle");
        assertTrue(leilao.propoe(new Lance(new Usuario("Nina"), new BigDecimal("500.0"))));

        assertThrows(UnsupportedOperationException.class, () -> {
            leilao.getLances().add(new Lance(new Usuario("Otto"), new BigDecimal("600.0")));
        });
    }

}
