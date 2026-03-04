package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PropondoLanceSteps {
    private Leilao leilao;
    private Lance lance;
    private List<Lance> lista;

    @Before
    public void setup(){
        this.lista = new ArrayList<Lance>();
        leilao = new Leilao("leilao");

    }

    @After
    public void tearDown(){
        System.out.println("after");
    }

    @Given("Um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("Meridiane");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @When("Propoe o lance ao leilao")
    public void quando_propoe_o_lance() {
        leilao.propoe(lance);
    }
    @Then("O lance eh aceito")
    public void o_lance_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Given("Um lance de {double} reais do usuario {string}")
    public void e_um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
       Lance lance = new Lance(new Usuario(nomeUsuario), BigDecimal.valueOf(valor));
       lista.add(lance);
    }

    @When("Propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        this.lista.forEach(lance -> leilao.propoe(lance));
    }
    @Then("Os lances sao aceitos")
    public void entao_os_lances_sao_aceitos() {
        Assert.assertEquals(this.lista.size(), leilao.getLances().size());
        Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
        Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

}
