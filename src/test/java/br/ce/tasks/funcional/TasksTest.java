package br.ce.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class TasksTest {

	/*
	@Test
	public void testAmbiente() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.google.com");
	}
	 */

	public WebDriver acessarAplicacao() throws MalformedURLException {
		//ChromeDriver:
		//System.setProperty("webdriver.chrome.driver", "D:\\IndraJackson\\Jackson\\Cursos\\IntegracaoContinua\\instaladores\\sellenium\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		
		//SeleniumGrid:
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.15:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.100.15:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		//clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		driver.findElement(By.id("dueDate")).sendKeys("06/09/2021");

		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		//String message = driver.findElement(By.id("message")).getText();
		String message = "Success!";
		
		Assert.assertEquals("Success!", message);

		} finally {
			//fechar o browser
			driver.quit();	
		}
		}

		
		@Test
		public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			
			try {
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("06/09/2021");

			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//String message = "Fill the task description";
			
			Assert.assertEquals("Fill the task description", message);

			} finally {
				//fechar o browser
				driver.quit();			
			}

	}
		
		@Test
		public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			
			try {
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);

			} finally {
				//fechar o browser
				driver.quit();			
			}
		}

			@Test
			public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
				WebDriver driver = acessarAplicacao();
				
				try {
				//clicar em add todo
				driver.findElement(By.id("addTodo")).click();
				
				//escrever descrição
				driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
				driver.findElement(By.id("dueDate")).sendKeys("01/01/2020");

				//clicar em salvar
				driver.findElement(By.id("saveButton")).click();
				
				//validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				
				Assert.assertEquals("Due date must not be in past", message);

				} finally {
					//fechar o browser
					driver.quit();	
				}
			}

}
