package mortgage_calculator_parametrized;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import parameters.DataProviderClass;
import utilities.ReadConfigFiles;

import java.sql.DatabaseMetaData;

public class CalculateRealAprRate_parameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRealAprRate_parameterized.class);

    WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        LOGGER.info("------------Test Name: Calculate Real Apr Rate------------");

        String browserUrl = ReadConfigFiles.getPropertyValues("Url");
        ActOn.browser(driver).openBrowser(browserUrl);
    }

    @Test(dataProvider = "RealAprRates",dataProviderClass = DataProviderClass.class)
    public void calculateRealApr(String homePrice,String downPayment,String interestRate, String actualApr){
        new NavigationBar(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice(homePrice)
                .clickDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickOnCalculateButton()
                .validateRealApr(actualApr);
    }
    
    @AfterMethod
    public void closeBrowser(){
        LOGGER.info("-----End Test Case: Calculate Real apr Rate------");
        ActOn.browser(driver).close();
    }

}
