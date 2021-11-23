package electronics;

import net.bytebuddy.utility.RandomString;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class ElectronicTest extends Utility {
    @Before
    public void setUp() {
        openBrowser();
    }

    @Test
    public void verifyShouldNavigateToCellPhonePageSuccessfully() {
        Actions actions = new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        WebElement cellPhone = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        actions.moveToElement(electronics).moveToElement(cellPhone).click().build().perform();
        verifyPage("Cell phones", By.xpath("//h1[text()='Cell phones']"), "Not in correct page");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
       Thread.sleep(3000);
        Actions actions=new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        WebElement cellPhone = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        actions.moveToElement(electronics).moveToElement(cellPhone).click().build().perform();
        verifyPage("Cell phones", By.xpath("//h1[text()='Cell phones']"), "Not in correct page");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[normalize-space()='List']"));
        Thread.sleep(3000);
        //clickOnElement(By.xpath("//a[@href='/nokia-lumia-1020']/parent::h2"));
        clickOnElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Nokia Lumia 1020')]"));
        Thread.sleep(3000);
        verifyPage("Nokia Lumia 1020", By.xpath("//h1[text()='Nokia Lumia 1020']"), "User is not in nokia page");
        verifyPage("$349.00", By.xpath("//span[@id='price-value-20']"), "prize is not same");
        Actions actions1 = new Actions(driver);
        Thread.sleep(3000);
        actions1.doubleClick(driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']"))).perform();
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).sendKeys("2");
        clickOnElement(By.xpath("//button[@id=\"add-to-cart-button-20\"]"));
        verifyPage("The product has been added to your shopping cart", By.xpath("//p[@class='content'] "), "User can not verify product is added or not");
        clickOnElement(By.xpath("//span[@class='close'] "));
        Thread.sleep(3000);
        Actions actions2 = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[@class='button-1 cart-button']"));
        Thread.sleep(3000);
        actions2.moveToElement(shoppingCart).moveToElement(goToCart).click().build().perform();
        Thread.sleep(3000);
        verifyPage("Shopping cart", By.xpath("//h1[text()='Shopping cart']"), "User is not in Shopping cart");
        String expQty = "2";
        String actualQty = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Thread.sleep(5000);
        Assert.assertEquals("quantity is not 2", expQty, actualQty);
        //verifyPage("2",By.xpath("//input[@id='itemquantity11223']"),"Quantity is not 2");
        verifyPage("$698.00", By.xpath("//span[@class='product-subtotal']"), "Amount is not matched ");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyPage("Welcome, Please Sign In!", By.xpath("//h1[text()='Welcome, Please Sign In!']"), "User is not in sign in page");
        clickOnElement(By.xpath("//button[@class=\"button-1 register-button\"]"));
        Thread.sleep(3000);
        sendText(By.xpath("//input[@id=\"FirstName\"]"), "Lauren");
        sendText(By.xpath("//input[@id=\"LastName\"]"), "preston");
        Thread.sleep(3000);
        RandomString randomString = new RandomString();
        String s = randomString.nextString();
        sendText(By.xpath("//input[@id=\"Email\"]"), s + "@gmail.com");
        sendText(By.xpath("//input[@id=\"Password\"]"), "abc123");
        Thread.sleep(3000);
        sendText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "abc123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        Thread.sleep(3000);
        verifyPage("Your registration completed", By.xpath("//div[text()='Your registration completed']"), "User is not in registration page");
        clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));
        verifyPage("Shopping cart", By.xpath("//h1[text()='Shopping cart']"), "User is not in Shopping cart page");
        //verifyPage("Your registration completed",By.xpath("//div[text()='Your registration completed']"),"User is not in registration page");
        //clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));
        // Thread.sleep(3000);
        //verifyPage("Shopping cart",By.xpath("//h1[text()='Shopping cart']"),"User is not in shopping cart page");
        clickOnElement(By.xpath("//input[@id=\"termsofservice\"]"));
        clickOnElement(By.xpath("//button[@id=\"checkout\"]"));
        sendText(By.xpath("//input[@id=\"BillingNewAddress_FirstName\"]"), "Dolly");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_LastName\"]"), "peterson");
        Thread.sleep(3000);
        RandomString randomString1 = new RandomString();
        String s1 = randomString1.nextString();
        driver.findElement(By.xpath("//input[@id=\"BillingNewAddress_Email\"]")).clear();
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Email\"]"), s1 + "@gmail.com");
        selectFromDropDown(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]"), "1");
        selectFromDropDown(By.xpath("//select[@id=\"BillingNewAddress_StateProvinceId\"]"), "1");
        Thread.sleep(3000);
        sendText(By.xpath("//input[@id=\"BillingNewAddress_City\"]"), "London");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_Address1\"]"), "Wadsworth");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_ZipPostalCode\"]"), "sw81234");
        sendText(By.xpath("//input[@id=\"BillingNewAddress_PhoneNumber\"]"), "1234567891");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick=\"Billing.save()\"]"));
        clickOnElement(By.xpath("//input[@id=\"shippingoption_2\"]"));
        clickOnElement(By.xpath("//button[@class=\"button-1 shipping-method-next-step-button\"]"));
        clickOnElement(By.xpath("//input[@id=\"paymentmethod_1\"]"));
        clickOnElement(By.xpath("//button[@class=\"button-1 payment-method-next-step-button\"]"));
        sendText(By.xpath("//input[@id=\"CardholderName\"]"), "Aavya");
        sendText(By.xpath("//input[@id=\"CardNumber\"]"), "4658 5987 2846 6012");
        selectFromDropDown(By.xpath("//select[@id=\"ExpireMonth\"]"), "3");
        selectFromDropDown(By.xpath("//select[@id=\"ExpireYear\"]"), "2027");
        sendText(By.xpath("//input[@id=\"CardCode\"]"), "307");
        clickOnElement(By.xpath("//button[@class=\"button-1 payment-info-next-step-button\"]"));
        Thread.sleep(3000);
        verifyPage("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is not credit card");
        verifyPage("2nd Day Air", By.xpath("//span[normalize-space()='2nd Day Air']"), "2nd Day Air is not shipping method");
        verifyPage("$698.00", By.xpath("//span[@class=\"product-subtotal\"]"), "$698.00 amount is not displayed");
        clickOnElement(By.xpath("//button[@class=\"button-1 confirm-order-next-step-button\"]"));
        verifyPage("Thank you",By.xpath("//h1[text()='Thank you']"),"User can not verify Thank you");
        verifyPage("Your order has been successfully processed!",By.xpath("//strong[text()='Your order has been successfully processed!']"),"nothing on display");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@class=\"button-1 order-completed-continue-button\"]"));
        verifyPage("Welcome to our store",By.xpath("//h2[text()='Welcome to our store']"),"User is not in correct page");
        clickOnElement(By.xpath("//a[@class=\"ico-logout\"]"));
        String url = driver.getCurrentUrl();
        Assert.assertEquals("User is unable to log out", url, "https://demo.nopcommerce.com/");


    }

}
