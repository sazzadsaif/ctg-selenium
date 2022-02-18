package automation_test_command_providers;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DateUtils;


public class CalculateMortgageRateCommandProvider {
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


    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
    }
    private void enterData(){
        // Enter Home Value "300000"
        ActOn.element(driver,HomeValueInputField).setValue("300000");
        // Enter Down Payment "60000"
        ActOn.element(driver,DownPaymentInputField).setValue("60000");
        // Click the $ button
        ActOn.element(driver,SelectDownPaymentInDollar).click();
        // Enter loan amount "240000"
        ActOn.element(driver,LoanAmountInputField).setValue("240000");
        // Enter Interest Rate "3"
        ActOn.element(driver,InterestRateInputField).setValue("3");
        //Enter Loan Term "30" Years
        ActOn.element(driver,LoanTermInputField).setValue("30");

        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        // Select the start date month mar
        ActOn.element(driver,StartMonthDropDown).selectValue(month);
        // Enter the year 2022
        ActOn.element(driver,StartYearInputField).setValue(year);
        //Enter property tax "5000"
        ActOn.element(driver,PropertyTaxInputField).setValue("5000");
        // Enter PMI "0.5"
        ActOn.element(driver,PMIInputField).setValue("0.5");
        //Enter home insurance "1000"
        ActOn.element(driver,HomeInsuranceInputField).setValue("1000");
        // Enter Monthly hoa "100"
        ActOn.element(driver,HOAInputField).setValue("100");
        // Select Loan type FHA
        ActOn.element(driver,LoanTypeDropDown).selectValue("FHA");
        // Select Buy
        ActOn.element(driver,RefiOrBuyDropDown).selectValue("Buy");
    }

    @Test
    public void calculateMonthlyPayment(){

        enterData();

        // Click on the Calculate button
        driver.findElement(CalculateButton).click();
        ActOn.element(driver,CalculateButton).click();

        String expectedTotalMonthlyPayment = "1,611.85";
        String formattedXpath = String.format("//h3[text()='$%s']",expectedTotalMonthlyPayment);
        // String formattedXpath = String.format("//h3[text()='$' + expectedTotalMonthlyPayment +'']");
        By monthlyPayment = By.xpath(formattedXpath);

        // Validate that the monthly payment is $1,611.85

        AssertThat.elementAssertions(driver,monthlyPayment).elementIsDisplayed();

    }
    @AfterMethod
    public void quitBrowser(){
        ActOn.browser(driver).close();
    }
}
