package br.com.alura.leilao.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowserFactory {

	public WebDriver createWebDriver() {
		// Por padrão, vamos usar o Chrome, que é mais estável para E2E
		String webdriver = System.getProperty("browser", "chrome");

		switch (webdriver) {
			case "firefox":
				return initFirefoxDriver();
			case "chrome":
				return initChromeDriver();
			default:
				return new HtmlUnitDriver();
		}
	}

	private WebDriver initChromeDriver() {
		// Não precisa mais de System.setProperty("webdriver.chrome.driver", ...)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		// Se o erro de "Unable to obtain: chromedriver" persistir,
		// tente rodar em modo headless (sem janela) descomentando a linha abaixo:
		// options.addArguments("--headless=new");

		return new ChromeDriver(options);
	}

	private WebDriver initFirefoxDriver() {
		return new FirefoxDriver();
	}
}