package pages.cartPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.basePage.BasePage;
import pages.productPage.ProductPage;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;


public class CartPage extends BasePage {


    int totalPrice= 0;
    int firstItemNumber=0;
    int secondItemNumber=0;
    public int subTotalPrice;
    private final By totalSubtotal  = By.xpath("//div[@data-name='Subtotals']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    private final By firstProductLocator = By.xpath("(//div[@data-cy='asin-faceout-container'])[1]");
    private final By addToCartLocator = By.xpath("//input[@id='add-to-cart-button']");
    private final By cartLocator = By.id("nav-cart-text-container");
    private final By secondWirelessMouseLocator = By.xpath("(//div[@data-cy='asin-faceout-container'])[2]");
    private final By allProductsInCart = By.id("activeCartViewForm");
    private final By cartQuantityLocator = By.xpath("//span[@id='nav-cart-count' and contains(.,2)]");
    private final By incrementIconLocator = By.cssSelector("[data-a-selector='increment-icon']");
    private final By cartCounterLocator = By.id("nav-cart-count");
    private final By priceWholeLocator = By.xpath("//div[@class='sc-item-price-block']//span[@class='a-price-whole']");
    private final By priceFractionLocator = By.xpath("//div[@class='sc-item-price-block']//span[@class='a-price-fraction']");
    private final By totalSubtotalLocator  = By.xpath("//div[@data-name='Subtotals']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    private final By decrementIconLocator = By.cssSelector("[data-a-selector='decrement-icon']");



    public CartPage(WebDriver driver) {
        super(driver);
    }
    public CartPage clickOnFirstProduct() {
        explicitWaitElementLocated(firstProductLocator);
        findElement(firstProductLocator).click();
        return this;
    }
    public CartPage clickAddToCart(){
        explicitWaitElementLocated(addToCartLocator);
        findElement(addToCartLocator).click();

        return this;
    }
    public CartPage clickOnCart(){
        explicitWaitElementLocated(cartLocator);
        findElement(cartLocator).click();
        return this;
    }
    public CartPage navigateBackTwice(){
        driver.navigate().back();
        driver.navigate().back();
        return this;
    }
    public CartPage openSecondProduct(){
        explicitWaitElementLocated(secondWirelessMouseLocator);
        findElement(secondWirelessMouseLocator).click();
        return this;
    }
    public String  getQuantityOfCart() {return findElement(cartQuantityLocator).getText();
    }
    public CartPage verifyChosenProductsInCart(){
        List <WebElement> allProductsCartList = findElements(allProductsInCart);
        int listSize = allProductsCartList.size();
        for(int i=0;i<listSize-1;i++){
            if(!(allProductsCartList.get(i).getText().contains("Color")||allProductsCartList.get(i).getText().contains("Color")))
            {
                System.out.println(allProductsCartList.get(i).getText());
            }

        }


        for (WebElement product : allProductsCartList) {
            String price = product.findElement(
                            By.cssSelector(".sc-item-price-block .a-price-whole")).getText()
                    .replace(",", "");

            Assert.assertEquals(getQuantityOfCart(), "2");

            System.out.println("Verified product price: " + price);
        }
        return this;
    }
    public CartPage incrementProduct(int value){
        while (value > 0) {
            int cartCounter = checkCartCounter();
            int expectedQuantity = cartCounter + 1;

            findElement(incrementIconLocator).click();

            wait.until(ExpectedConditions.textToBe(
                    cartCounterLocator,
                    String.valueOf(expectedQuantity)
            ));
            value--;
        }
        return this;
    }
    public int checkCartCounter() {return Integer.parseInt(findElement(cartCounterLocator).getText());
    }
    public CartPage decrementProduct(int value){
        while (value>0){
            int cartCounter;
            cartCounter = checkCartCounter();
            int expectedQuantity = cartCounter - 1;
            findElement(decrementIconLocator).click();
            wait.until(ExpectedConditions.textToBe(cartCounterLocator,String.valueOf(expectedQuantity)));
            value--;
        }
        return this;
    }
    public String getCartCounter(){

        return findElement(cartCounterLocator).getText();
    }
    public int getProductPrice(int multiply) {
        String whole = findElement(priceWholeLocator)
                .getText()
                .replaceAll("[^0-9]", "");

        String fraction = findElement(priceFractionLocator)
                .getText();
        BigDecimal price = new BigDecimal(whole + "." + fraction);

        return price
                .multiply(BigDecimal.valueOf(multiply))
                .movePointRight(2)
                .intValueExact();
    }
    public int getProductPrice() {
        String whole = findElement(priceWholeLocator)
                .getText()
                .replaceAll("[^0-9]", "");

        String fraction = findElement(priceFractionLocator)
                .getText();
        BigDecimal price = new BigDecimal(whole + "." + fraction);

        return price
                .movePointRight(2)
                .intValueExact();
    }
    public int getSubTotal() {
        int temp =Integer.parseInt(findElement(totalSubtotalLocator).getText().replaceAll("[^0-9]", ""))/100;
        System.out.println(temp);
        return temp;
    }
    public int getSubTotalPrice() {
        String temp = findElement(totalSubtotal)
                .getText()
                .replaceAll("[^0-9]", "");
        subTotalPrice = Integer.parseInt(temp);
        return subTotalPrice;
    }











}
