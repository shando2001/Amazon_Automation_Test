package pages.productPage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.basePage.BasePage;
import java.util.List;

public class ProductPage extends BasePage {
    String firstCategoryName;
    String secondCategoryName;
    private final By searchBarLocator = By.id("twotabsearchtextbox");
    private final By searchIconButtonLocator = By.id("nav-search-submit-button");
    private final By brandFilterLocator = By.cssSelector("[aria-label='Apply the filter Dell to narrow results']");
    private final By maxPriceSliderLocator = By.xpath("//input[@id='p_36/range-slider_slider-item_upper-bound-slider']");
    private final By targetPriceLocator = By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']//span");
    private final By numOfItemLocator = By.xpath("//span[contains(text(),' results for')]");
    private final By sortByFilterPriceLocator = By.cssSelector("[data-action='a-dropdown-button']");
    private final By sortingLowToHighLocator = By.xpath("//a[@class='a-dropdown-link'  and contains(.,'Low')]");
    private final By resultTextLocator = By.xpath("//h2[contains(.,'Results')]");
    private final By allProductsTitleListLocator = By.xpath("//a[@class='a-link-normal s-line-clamp-3 s-link-style a-text-normal']//span");
    private final By priceForAllProduct = By.className("a-price-whole");
    private final By firstCategoryLocator =By.xpath("//ul[@class='a-unordered-list a-horizontal a-size-small']//span[@class='a-list-item']");
    private final By secondItemLocator = By.cssSelector("[data-cel-widget='search_result_2']");
    private final By firstProductLocator = By.xpath("(//div[@data-cy='asin-faceout-container'])[1]");
    private final By productWithoutOutOfStockLocator =By.xpath("//div[@data-component-type='s-search-result' and not(.//span[contains(text(),'left in stock')])]");
    private final By productTitleLocator = By.id("productTitle");
    private final By ProductPriceWholeLocator = By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
    private final By availabilityStatusLocator= By.id("availability");
    private final By productImageLocator = By.id("main-image-container");
    private final By ratingSectionLocator = By.id("averageCustomerReviews_feature_div");
    private final By addToCartVerificationLocator = By.id("add-to-cart-button");
    private final By sortingHighToLowLocator = By.xpath("//a[@class='a-dropdown-link' and contains (.,'High to')]");
    private final By subTotalLocator = By.xpath("//h2[@class='a-size-mini a-color-price a-text-bold']");
    private final By addToCartButtonLocator = By.xpath("//input[@aria-label='Add to cart']");
    private final By freeShippingButtonLocator = By.xpath("//li[@id='p_n_free_shipping_eligible/21909080031']//div[@class='a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left']");
    private final By addToCartButtonOutSide = By.xpath("//div[@role='listitem' and not(.//div[@class='a-stepper-controls'])]//input[@aria-label='Add to cart']");
    private final By rightSideCartItems =By.xpath("//div[@data-cart-type='Retail_Cart']//li");
    private final By filterMenu =By.id("s-refinements");

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public ProductPage searchBarInsertion(String searchValue){
        findElement(searchBarLocator).sendKeys(searchValue);
           return this;
    }
    public ProductPage clickSearchIconButton(){
        findElement(searchIconButtonLocator).click();
        explicitWaitElementLocated(filterMenu);
        return this;
    }
    public ProductPage applyBrandFilter(){
        findElement(brandFilterLocator).click();
        return this;
    }
    public ProductPage setMaxPrice(int targetPrice) {
        WebElement maxSlider = findElement(maxPriceSliderLocator);
        String temp = maxSlider.getAttribute("aria-valuetext");
        assert temp != null;
        int currentPrice = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
        int steps = (currentPrice - targetPrice) / 10;
        for (int i = 0; i < steps; i++) {
            int tempPrice = Integer.parseInt(findElement(targetPriceLocator).getText().replaceAll("[^0-9]", ""));
            if (tempPrice == targetPrice) {
                break;
            }
            maxSlider.sendKeys(Keys.ARROW_LEFT);
        }
        String firstNumberOfItem = findElement(numOfItemLocator).getText().split(" results")[0];
        By afterSlider = By.xpath( "//span[contains(text(),'"
                + firstNumberOfItem + " results for')]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(afterSlider));

        return this;
    }
    public ProductPage clickSortingLowToHigh (){
        findElement(sortByFilterPriceLocator).click();
        findElement(sortingLowToHighLocator).click();
        explicitWaitElementLocated(resultTextLocator);
        return this;}
    public ProductPage verifyMatchingSelectedFilters(){
        List <WebElement> allProductsList = findElements(allProductsTitleListLocator);
        int listSize =allProductsList.size();
        for(int i=0; i<listSize-1;i++){
            if(!(allProductsList.get(i).getText().contains("HP")||allProductsList.get(i).getText().contains("HP")))
            {
                System.out.println(allProductsList.get(i).getText());
            }
        }return this;
    }
    public void verifySortingOrderCurrently() {
        List<WebElement> allPrice = findElements(priceForAllProduct);
        int listSize = allPrice.size();

        for (int i = 0; i < listSize - 1; i++) {
            int currentProductPrice = Integer.parseInt(allPrice.get(i).getText().replace(",", ""));
            int nextProductPrice = Integer.parseInt(allPrice.get(i + 1).getText().replace(",", ""));
            if (!(currentProductPrice <= nextProductPrice)) {
                System.out.println(allPrice.get(i - 1) + " [i] = " + currentProductPrice);
                break;
            }
        }
    }
    public ProductPage getFirstCategory() {
        firstCategoryName = findElements(firstCategoryLocator).getFirst().getText();
        return this;
    }
    public ProductPage clickOnSecondProduct() {
        findElement(secondItemLocator).click();
        return this;
    }
    public void getSecondCategory(){secondCategoryName= findElements(firstCategoryLocator).getFirst().getText();
    }
    public String getFirstCategoryName() {return firstCategoryName;
    }
    public String getSecondCategoryName(){return secondCategoryName;
    }
    public ProductPage clickOnFirstProduct() {
        explicitWaitElementLocated(firstProductLocator);
        findElement(firstProductLocator).click();
        return this;
    }
    public ProductPage clickOnFirstProductNotOutOfStock(){

        findElements(productWithoutOutOfStockLocator).getFirst().click();
        return this;
    }
    public boolean verifyProductTitleIsVisible(){return findElement(productTitleLocator).isDisplayed();
    }
    public boolean verifyProductPriceIsVisible(){return findElement(ProductPriceWholeLocator).isDisplayed();
    }
    public boolean verifyProductAvailabilityStatus(){return findElement(availabilityStatusLocator).isDisplayed();
    }
    public boolean verifyProductImageIsVisible(){return findElement(productImageLocator).isDisplayed();
    }
    public boolean verifyProductRatingSection(){return findElement(ratingSectionLocator).isDisplayed();
    }
    public boolean verifyAddToCartIsVisibleAndEnabled(){return findElement(addToCartVerificationLocator).isDisplayed();
    }
    public ProductPage clickSortingHighToLow (){
        findElement(sortByFilterPriceLocator).click();
        findElement(sortingHighToLowLocator).click();
        explicitWaitElementLocated(resultTextLocator);
        return this;}
    public ProductPage addNumOfProduct(int num) {
        while (num > 0) {
            String oldPrice;
            if (driver.findElements(subTotalLocator).isEmpty()) {
                oldPrice = "";
            } else {
                oldPrice = findElement(subTotalLocator).getText();
            }
            findElements(addToCartButtonOutSide).getFirst().click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(rightSideCartItems));

            if (!oldPrice.isEmpty()) {
                wait.until(ExpectedConditions.not(
                        ExpectedConditions.textToBe(subTotalLocator, oldPrice)));
            }

            num--;
        }
        return this;
    }
    public int getSubTotal() {int temp =Integer.parseInt(findElement(subTotalLocator).getText().replaceAll("[^0-9]", ""))/100;
        System.out.println(temp);
        return temp;
    }
    public ProductPage clickFreeShippingButton(){
        findElement(freeShippingButtonLocator).click();
        return this;
    }
    public int getCountOfItemOnCart() {return findElements(rightSideCartItems).size();
    }















}
