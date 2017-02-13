package br.com.caelum.argentum.aceitacao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class GeraGraficoTest {

	private static final String URL = "http://localhost:8081/fj22-argentum-web/index.xhtml";
	private WebDriver driver;
	private WebDriver driverHtml;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C://Users/vnogueira/git/fj22-argentum-web/lib-teste" + "/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		//driverHtml = new HtmlUnitDriver();
	}

	@After
	public void tearDown() {
		 driver.close();
		//driverHtml.close();
	}

	

	@Test
	public void testeChromeAoGerarGraficoSemTituloUmaMensagemEhApresentada() {
		driver.navigate().to(URL);
		WebElement titulo = driver.findElement(By.id("dadosGrafico:titulo"));

		titulo.sendKeys("");
		titulo.submit();

		boolean existeMensagem = driver.getPageSource().contains("Erro de validação");

		assertTrue(existeMensagem);
	}
	
	//@Test
		public void testeHTMLAoGerarGraficoSemTituloUmaMensagemEhApresentada() {
			driver.navigate().to(URL);
			WebElement titulo = driverHtml.findElement(By.id("dadosGrafico:titulo"));

			titulo.sendKeys("");
			titulo.submit();

			boolean existeMensagem = driver.getPageSource().contains("Erro de Validação");

			assertTrue(existeMensagem);
		}

}
