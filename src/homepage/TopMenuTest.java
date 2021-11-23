/*
1. create class "TopMenuTest"
 	1.1 create method with name "selectMenu" it has one parameter name "menu" of  type string
	1.2 This method should click on the menu whatever name is passed as parameter.
	1.3. create the @Test method name verifyPageNavigation.use selectMenu method to select the Menu and click on it and verify the page navigation.
*/package homepage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.Scanner;

public class TopMenuTest extends Utility {

    @Before
    public void setUp(){

        openBrowser();

    }
    @Test
    public void verifyPageNavigation()
    {
         selectMenu("Digital downloads");
         //select menu method is used to take any element like Computers,Books,Jewelry,Gift Cards and so on
    }
    @After
    public void tearDown(){
        //closeBrowser();
    }



}
