package br.com.alura.leilao.e2e.pages;

import java.time.Duration; // Import necessário para o Selenium 4
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DetalhesDoLeilaoPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public DetalhesDoLeilaoPage(WebDriver driver) {
		this.driver = driver;
		// Ajuste: Agora passamos Duration.ofSeconds(5) em vez de apenas 5
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void darLance(String valor) {

		WebElement txtValor = driver.findElement(By.id("valor"));
		txtValor.sendKeys(valor);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btnDarLance")).submit();
	}

	public boolean existeLance(String valor) {
		By locator = By.xpath("//table[@id='lancesDados']//td[contains(.,'" + valor + "')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		WebElement td = driver.findElement(locator);
		String result = td.getText();

		return result != null;
	}

	public boolean temApenasUmLance() {
		// Dica: trs.size() == 2 geralmente conta o cabeçalho + 1 linha de dados
		List<WebElement> trs = driver.findElements(By.tagName("tr"));
		return trs.size() == 2;
	}
}