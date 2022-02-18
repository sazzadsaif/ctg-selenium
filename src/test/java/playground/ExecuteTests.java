package playground;

import org.testng.annotations.Test;
import parameters.DataProviderClass;

public class ExecuteTests {
    @Test(dataProvider = "MultipleValues",dataProviderClass = DataProviderClass.class)
    public void run(String name,String state,int zipCode){
        System.out.println("[Multiple Column Values] Name is: " + name );
        System.out.println("[Multiple Column Values] State is: " + state );
        System.out.println("[Multiple Column Values] Zip Code is: " + zipCode );
    }
}
