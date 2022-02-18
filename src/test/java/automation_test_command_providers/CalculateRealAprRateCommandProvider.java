package automation_test_command_providers;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
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

public class CalculateRealAprRateCommandProvider {
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
        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    public void navigateToRealAprPage(){

        // Mouse Hover To Rates link

        ActOn.element(driver,RatesLink).mouseHover();

        // Click on the Real Apr Link

        ActOn.element(driver,RealAprLink).click();

        //Wait for the page to load

        ActOn.wait(driver,CalculatorTab).waitForElementToBeVisible();
    }

    public void enterData(){

        ActOn.element(driver,HomePriceInputField).setValue("200000");

        ActOn.element(driver,DownPaymentInDollar).click();

        ActOn.element(driver,DownPaymentInputField).setValue("15000");

        ActOn.element(driver,InterestRateInputField).setValue("3");
    }

    @Test
    public void calculateRealApr(){
        navigateToRealAprPage();
        enterData();


        ActOn.element(driver,CalculateRateButton).click();

        // Validate the real apr rate is 3.130%
        String actualRealAprRate = driver.findElement(ActualAprRate).getText();
        Assert.assertEquals(actualRealAprRate,"3.130%");
    }
    
    @AfterMethod
    public void closeBrowser(){

        ActOn.browser(driver).close();
    }

}
