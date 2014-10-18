package stress.tests;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class WebStressTest {
	// Waiting 30 seconds for an element to be present on the page, checking
	// for its presence once every 5 seconds.
	
	@Rule
	public ContiPerfRule rule =new ContiPerfRule();

	@PerfTest(invocations=1000,threads=50,rampUp=10000) // each thread/user will run 10 times the test below
												    // but user is incremented after every 10 seconds
	@Test
	public void test() {
		System.out.println("current thread = " + Thread.currentThread().getName());
		WebDriver driver = null;
		File phantomJSExeFile = new File("D:/phantomjs.exe");
		try {
			DesiredCapabilities ghostCaps = DesiredCapabilities.internetExplorer();
			ghostCaps.setJavascriptEnabled(true);
			ghostCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSExeFile.getAbsolutePath());
			ghostCaps.setCapability("takeScreenshot", true);
			driver = new PhantomJSDriver(ghostCaps);

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			driver.get("http://localhost:8080/SpringPrimefacesWebApp/");
			findById("loginForm:username", wait).clear();
			findById("loginForm:username", wait).sendKeys("makky");

			// clikc login button
			findById("loginForm:loginButton", wait).click();
			findById("userDetails:insertUserDetail", wait).click();

			File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotAs, new File("D:/screenshots/" + UUID.randomUUID().toString() + ".jpg"));

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	private WebElement findById(final String id, Wait<WebDriver> wait) {
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(id));
			}
		});
		return foo;
	}

}
