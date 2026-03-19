package br.com.alura.leilao.e2e.pages;

import java.time.Duration; // Import necessário para o Selenium 4

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;

	private static String URL_LOGIN_PAGE = "http://localhost:8080/login";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LeiloesPage realizaLoginComo(String nome, String senha) {
		driver.get(URL_LOGIN_PAGE);

		// No Spring Security 6, o padrão dos nomes dos campos continua sendo username e password
		WebElement txtNome = driver.findElement(By.id("username"));
		WebElement txtSenha = driver.findElement(By.id("password")); // Renomeado de txtEmail para txtSenha para clareza

		txtNome.sendKeys(nome);
		txtSenha.sendKeys(senha);

		txtNome.submit();

		return new LeiloesPage(driver);
	}

	public LeiloesPage realizaLoginComoFulano() {
		return realizaLoginComo("fulano", "pass");
	}

	public boolean estaNaPaginaDeLeiloes() {
		this.esperaCarregarPaginaDeLeiloes();
		return this.driver.getCurrentUrl().endsWith("/leiloes");
	}

	public void esperaCarregarPaginaDeLeiloes() {
		// AJUSTE AQUI: No Selenium 4 usamos Duration.ofSeconds(10)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Todos leilões')]")));
	}

	public boolean estaNaPaginaDeLoginComErro() {
		return this.driver.getCurrentUrl().endsWith("/login?error");
	}

}