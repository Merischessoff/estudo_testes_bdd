package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;

public class PropondoLanceSteps {
    private Leilao leilao;
    private Lance lance;
    private Lance lance15;
    private Lance lance10;

    @Given("Dado um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("Meridiane");
        lance = new Lance(usuario, BigDecimal.TEN);
        leilao = new Leilao("leilao");
    }

    @When("Quando propoe o lance ao leilao")
    public void quando_propoe_o_lance() {
        leilao.propoe(lance);
    }
    @Then("Entao o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Given("Dado varios lances validos")
    public void dado_varios_lances_validos() {
        Usuario usuario0 = new Usuario("Meridiane");
        lance10 = new Lance(usuario0, BigDecimal.TEN);

        Usuario usuario1 = new Usuario("Fulano 1");
        lance15 = new Lance(usuario1, BigDecimal.valueOf(15));
        leilao = new Leilao("leilao");
    }

    @When("Quando propoe varios lances ao leilao")
    public void quando_propoe_varios_lances_ao_leilao() {
        leilao.propoe(lance10);
        leilao.propoe(lance15);
    }
    @Then("Entao os lances sao aceitos")
    public void entao_os_lances_sao_aceitos() {
        Assert.assertEquals(2, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
        Assert.assertEquals(BigDecimal.valueOf(15), leilao.getLances().get(1).getValor());
    }

}
