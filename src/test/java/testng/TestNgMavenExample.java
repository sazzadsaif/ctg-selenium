package testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.DateUtils;
import utilities.ReadConfigFiles;

public class TestNgMavenExample {
    private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExample.class);
    //@Test
    public void run(){
        System.out.println("This is Test NG Maven test");
        LOGGER.debug("This is debug message");
        LOGGER.info("This is info message");
        LOGGER.warn("This is warn message");
        LOGGER.error("This is error message");
        LOGGER.fatal("This is fatal message");
        LOGGER.trace("This is trace message");
    }
    //@Test
    public void testPropertyFile(){
        String value = ReadConfigFiles.getPropertyValues("DbUser");
        LOGGER.info(value);
    }
    @Test
    public void runs(){
        System.out.println(DateUtils.returnNextMonth());

    }
}
