/*
1. Create class “TestSuite”

Write the following Test:
1.Test name verifyProductArrangeInAlphaBaticalOrder()
	1.1 Click on Computer Menu.
	1.2 Click on Desktop
	1.3 Select Sort By position "Name: Z to A"
	1.4 Verify the Product will arrange in Descending order.

2. Test name verifyProductAddedToShoppingCartSuccessFully()
	2.1 Click on Computer Menu.
	2.2 Click on Desktop
	2.3 Select Sort By position "Name: A to Z"
	2.4 Click on "Add To Cart"
	2.5 Verify the Text "Build your own computer"
	2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
	2.7.Select "8GB [+$60.00]" using Select class.
	2.8 Select HDD radio "400 GB [+$100.00]"
	2.9 Select OS radio "Vista Premium [+$60.00]"
A          2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
	2.11 Verify the price "$1,475.00"
	2.12 Click on "ADD TO CARD" Button.
	2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
After that close the bar clicking on the cross button.
	2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
	2.15 Verify the message "Shopping cart"
	2.16 Change the Qty to "2" and Click on "Update shopping cart"
	2.17 Verify the Total"$2,950.00"
	2.18 click on checkbox “I agree with the terms of service”
	2.19 Click on “CHECKOUT”
	2.20 Verify the Text “Welcome, Please Sign In!”
	2.21Click on “CHECKOUT AS GUEST” Tab
	2.22 Fill the all mandatory field
	2.23 Click on “CONTINUE”
	2.24 Click on Radio Button “Next Day Air($0.00)”
2.25 Click on “CONTINUE”
2.26 Select Radio Button “Credit Card”
2.27 Select “Master card” From Select credit card dropdown
2.28 Fill all the details
2.29 Click on “CONTINUE”
2.30 Verify “Payment Method” is “Credit Card”
2.32 Verify “Shipping Method” is “Next Day Air”
	2.33 Verify Total is “$2,950.00”
	2.34 Click on “CONFIRM”
	2.35 Verify the Text “Thank You”
	2.36 Verify the message “Your order has been successfully processed!”
	2.37 Click on “CONTINUE”
2.37 Verify the text “Welcome to our store”

 */
package computer;

import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    @Before
    public void setup() {
        openBrowser();
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        clickOnElement(By.linkText("Desktops"));
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        dropDown.click();
        Select select = new Select(dropDown);
        select.selectByValue("6");
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='products-wrapper']//descendant::div[@class='item-box']"));
        List<String> names = new ArrayList<String>();
        for (WebElement e : products) {
            names.add(e.getText());
        }
        List<String> sortedNames = new ArrayList<String>(names);
        Collections.sort(sortedNames);
        System.out.println(sortedNames.equals(names));
        Assert.assertEquals("Sorting product names from Z to A is not working", names, sortedNames);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.linkText("Computers"));
        clickOnElement(By.linkText("Desktops"));
        Thread.sleep(2000);
        selectFromDropDown(By.xpath("//select[@id=\"products-orderby\"]"), "5");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[contains(@onclick,'return AjaxCart.addproducttocart_catalog(\"/addproducttocart/catalog/1/1/1\"),!1')]"));
        String expectedResult = "Build your own computer";
        verifyPage(expectedResult, By.xpath("//h1[text()='Build your own computer']"), "User is not in correct page");
        selectFromDropDown(By.id("product_attribute_1"), "1");
        Thread.sleep(3000);
        selectFromDropDown(By.xpath("//select[@id=\"product_attribute_2\"]"), "5");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id=\"product_attribute_5_12\"]"));
        Thread.sleep(3000);
        String expectedResult1 = "$1,475.00";
        Thread.sleep(3000);
        verifyPage(expectedResult1,By.xpath("//span[@id='price-value-1']"),"data not matched");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        String expectedResult2 = "The product has been added to your shopping cart";
        verifyPage(expectedResult2, By.xpath("//p[@class='content']"), "User is not able to see product is add notification");
        clickOnElement(By.xpath("//span[@class=\"close\"]"));
        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class=\"cart-label\"]"))).perform();
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"))).click().perform();
        Thread.sleep(5000);
        String expectedResult3 = "Shopping cart";
        verifyPage(expectedResult3, By.xpath("//h1[text()='Shopping cart']"), "user is not in shopping cart page");
        Actions actions2 = new Actions(driver);
        Thread.sleep(3000);
        actions.doubleClick(driver.findElement(By.xpath("//input[@class=\"qty-input\"]"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class=\"qty-input\"]")).sendKeys("2");
        clickOnElement(By.xpath("//button[@class=\"button-2 update-cart-button\"]"));
        Thread.sleep(3000);
        verifyPage("$2,950.00", By.xpath("//span[@class=\"product-subtotal\"]"), "amount not match");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id=\"termsofservice\"]"));
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@id=\"checkout\"]"));
        Thread.sleep(3000);
        verifyPage("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not navigated correctly");
        clickOnElement(By.xpath("//button[@class=\"button-1 checkout-as-guest-button\"]"));
        sendText(By.xpath("//input[@id=\"BillingNewAddress_FirstName\"]"), "Aavya");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_LastName\"]"), "Rathod");
        RandomString randomString = new RandomString();
        String s = randomString.nextString();
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Email\"]"), s + "@gmail.com");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Company\"]"), "RenewDryCleaners");
        Thread.sleep(3000);
        selectFromDropDown(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]"), "133");
        clickOnElement(By.xpath("//select[@id=\"BillingNewAddress_StateProvinceId\"]"));
        sendText(By.xpath("//input[@id=\"BillingNewAddress_City\"]"), "Raigarh");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Address1\"]"), "Darogapara");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Address2\"]"), "Gujrati gali");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_ZipPostalCode\"]"), "496001");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_PhoneNumber\"]"), "1234567891");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_FaxNumber\"]"), "1234567891123456789");
        clickOnElement(By.xpath("//button[@name=\"save\"]/parent::div[@id=\"billing-buttons-container\"]"));
        clickOnElement(By.xpath("//input[@id=\"shippingoption_1\"]"));
        clickOnElement(By.xpath("//button[@class=\"button-1 shipping-method-next-step-button\"]"));
        clickOnElement(By.xpath("//input[@id=\"paymentmethod_1\"]"));
        clickOnElement(By.xpath("//button[text()='Continue' and @name='save' and @onclick='PaymentMethod.save()']"));
        selectFromDropDown(By.id("CreditCardType"), "MasterCard");
        Thread.sleep(3000);
        sendText((By.xpath("//input[@id='CardholderName']")), "Avya");
        sendText(By.id("CardNumber"), "5412 5412 5434 5436");
        selectFromDropDown(By.id("ExpireMonth"), "7");
        Thread.sleep(3000);
        selectFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendText(By.xpath("//input[@id='CardCode']"),"303");
        Thread.sleep(3000);
        //click on continue button on page where we fill all card details
        clickOnElement(By.xpath("//button[@onclick=\"PaymentInfo.save()\"]"));
        verifyPage("Credit Card",By.xpath("//span[contains(text(),'Credit Card')]"),"Payment method is not credit card");
        verifyPage("",By.xpath("//span[contains(text(),'\\n\" +\n" +
                "                \"                                Next Day Air\\n\" +\n" +
                "                \"                            ')]"),"Shipping method is not correct");
        verifyPage("$2,950.00",By.xpath("//span[contains(text(),'$2,950.00')]"),"Amount not matched");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        Thread.sleep(3000);
        verifyPage("",By.xpath("//h1[contains(text(),'Thank you')]"),"User can not see thank you message");
        verifyPage("Your order has been successfully processed!",By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"),"User is not able to see any text about order conformation");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyPage("Welcome to our store",By.xpath("//h2[contains(text(),'Welcome to our store')]"),"User is not able to see well come note");
    }

}
