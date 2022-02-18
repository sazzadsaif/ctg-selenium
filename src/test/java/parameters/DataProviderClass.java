package parameters;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name = "SingleValue")
    public Object[][] storeSingleValue(){
        return new Object[][]{
                {"Sazzad"},
                {"Tanvir"},
                {"Mostafa"}
        };
    }

    @DataProvider(name = "MultipleValues")
    public Object[][] storeMultipleValues(){
        return new Object[][]{
                {"Sazzad","New York",11417},
                {"Tanvir","Dhaka",4000},
                {"Mostafa","Chittagong",2001}
        };
    }

    @DataProvider(name= "RealAprRates")
    public Object[][] storeRealAprRatesData(){
            return new Object[][]{
                    {"200000","15000","3","3.130%"}
            };
    }

    //@Test(dataProvider = "SingleValue")
    public void readSingleValue(String name){
        System.out.println("[Single Column Value] Name is: " + name);
    }

    @Test(dataProvider = "MultipleValues")
    public void readMultipleValues(String name,String state,int zipCode){
        System.out.println("[Multiple Column Values] Name is: " + name );
        System.out.println("[Multiple Column Values] State is: " + state );
        System.out.println("[Multiple Column Values] Zip Code is: " + zipCode );

    }
}
