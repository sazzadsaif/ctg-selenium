package automation_test.mortgage_calculator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalculateRealAprRate {
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By CalculatorTab = By.xpath("//label[text()='Calculator']");
    private final By HomePriceInputField = By.name("HomeValue");
    private final By DownPaymentInDollar = By.name("DownPaymentSel");
    private final By DownPaymentInputField = By.name("DownPayment");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateRateButton = By.name("calculate");
    private final By ActualAprRate = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td/strong[text()='Actual APR:']/../../td[2]/strong");

    WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mortgagecalculator.org/");
        driver.manage().window().maximize();
    }

    public void navigateToRealAprPage(){

        // Mouse Hover To Rates link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(RatesLink)).perform();

        // Click on the Real Apr Link
        driver.findElement(RealAprLink).click();

        //Wait for the page to wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CalculatorTab));
    }

    public void enterData(){
        driver.findElement(HomePriceInputField).clear();
        driver.findElement(HomePriceInputField).sendKeys("200000");

        driver.findElement(DownPaymentInDollar).click();

        driver.findElement(DownPaymentInputField).clear();
        driver.findElement(DownPaymentInputField).sendKeys("15000");

        driver.findElement(InterestRateInputField).clear();
        driver.findElement(InterestRateInputField).sendKeys("3");
    }

    @Test
    public void calculateRealApr(){
        navigateToRealAprPage();
        enterData();

        driver.findElement(CalculateRateButton).click();

        // Validate the real apr rate is 3.130%
        String actualRealAprRate = driver.findElement(ActualAprRate).getText();
        Assert.assertEquals(actualRealAprRate,"3.130%");
    }
    
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
