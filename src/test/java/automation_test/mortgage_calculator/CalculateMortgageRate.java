package automation_test.mortgage_calculator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalculateMortgageRate {
    private final By HomeValueInputField = By.id("homeval");
    private final By DownPaymentInputField = By.id("downpayment");
    private final By SelectDownPaymentInDollar = By.name("param[downpayment_type]");
    private final By LoanAmountInputField = By.id("loanamt");
    private final By InterestRateInputField = By.id("intrstsrate");
    private final By LoanTermInputField = By.id("loanterm");
    private final By StartMonthDropDown = By.name("param[start_month]");
    private final By StartYearInputField = By.id("start_year");
    private final By PropertyTaxInputField = By.id("pptytax");
    private final By PMIInputField = By.id("pmi");
    private final By HomeInsuranceInputField = By.id("hoi");
    private final By HOAInputField = By.id("hoa");
    private final By LoanTypeDropDown = By.name("param[milserve]");
    private final By RefiOrBuyDropDown = By.name("param[refiorbuy]");
    private final By CalculateButton = By.name("cal");

    WebDriver driver;
    Select select;

    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mortgagecalculator.org/");
        driver.manage().window().maximize();
    }
    private void enterData(){
        // Enter Home Value "300000"
        driver.findElement(HomeValueInputField).clear();
        driver.findElement(HomeValueInputField).sendKeys("300000");
        // Enter Down Payment "60000"
        driver.findElement(DownPaymentInputField).clear();
        driver.findElement(DownPaymentInputField).sendKeys("60000");
        // Click the $ button
        driver.findElement(SelectDownPaymentInDollar).click();
        // Enter loan amount "240000"
        driver.findElement(LoanAmountInputField).clear();
        driver.findElement(LoanAmountInputField).sendKeys("240000");
        // Enter Interest Rate "3"
        driver.findElement(InterestRateInputField).clear();
        driver.findElement(InterestRateInputField).sendKeys("3");
        //Enter Loan Term "30" Years
        driver.findElement(LoanTermInputField).clear();
        driver.findElement(LoanTermInputField).sendKeys("30");
        // Select the start date month mar
        select = new Select(driver.findElement(StartMonthDropDown));
        select.selectByVisibleText("Mar");
        // Enter the year 2022
        driver.findElement(StartYearInputField).clear();
        driver.findElement(StartYearInputField).sendKeys("2022");
        //Enter property tax "5000"
        driver.findElement(PropertyTaxInputField).clear();
        driver.findElement(PropertyTaxInputField).sendKeys("5000");
        // Enter PMI "0.5"
        driver.findElement(PMIInputField).clear();
        driver.findElement(PMIInputField).sendKeys("0.5");
        //Enter home insurance "1000"
        driver.findElement(HomeInsuranceInputField).clear();
        driver.findElement(HomeInsuranceInputField).sendKeys("1000");
        // Enter Monthly hoa "100"
        driver.findElement(HOAInputField).clear();
        driver.findElement(HOAInputField).sendKeys("100");
        // Select Loan type FHA
        select = new Select(driver.findElement(LoanTypeDropDown));
        select.selectByVisibleText("FHA");
        // Select Buy
        select = new Select(driver.findElement(RefiOrBuyDropDown));
        select.selectByVisibleText("Buy");
    }

    @Test
    public void calculateMonthlyPayment(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        enterData();

        // Click on the Calculate button
        driver.findElement(CalculateButton).click();

        String expectedTotalMonthlyPayment = "1,611.85";
        String formattedXpath = String.format("//h3[text()='$%s']",expectedTotalMonthlyPayment);
        // String formattedXpath = String.format("//h3[text()='$' + expectedTotalMonthlyPayment +'']");

        boolean present = driver.findElement(By.xpath(formattedXpath)).isDisplayed();

        // Validate that the monthly payment is $1,611.85
        Assert.assertTrue(present,"Total monthly payment is not presented");

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
