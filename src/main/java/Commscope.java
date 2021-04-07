import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Commscope {

	static WebDriver driver;

	private static final By DISCOVER_MORE_BUTTON = By.xpath("//a[contains(text(),'Discover more')]");
	private static final By SEARCH_BAR = By.className("search-bar");
	private static final By SEARCH_BUTTON = By.xpath("//button[@class='search-submit-btn']");

	// Results
	private static final By PRODUCTS = By.xpath("//div[contains(text(),'Products')]");
	private static final By LINKS = By.xpath("//a[contains(text(),'ME-7000')]");
	private static final By SHOWING_RESULTS_LABEL = By.xpath("//h3[contains(text(),'Showing results')]");

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/resources/chromedriver");

	}

	@Test
	public void performSearch() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.commscope.com/");

		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DISCOVER_MORE_BUTTON));

		WebElement discoverMoreBtn = driver.findElement(DISCOVER_MORE_BUTTON);

		/*
		 * if (discoverMoreBtn.isDisplayed()) { discoverMoreBtn.click(); }
		 */

		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BAR)).sendKeys("ME-7000");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));

		// WebElement searchButton = driver.findElement(SEARCH_BUTTON);
		// searchButton.click();

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(SHOWING_RESULTS_LABEL));

		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS));

		List<WebElement> totalLinks = driver.findElements(LINKS);
		System.out.println("Links Found");

		for (WebElement link : totalLinks) {
			System.out.println("Link: " + link.getText());

		}

	}
	

	@AfterTest
	public void closeDriver() {

	}

}
