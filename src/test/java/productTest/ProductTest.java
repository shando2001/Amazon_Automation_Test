package productTest;
import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.productPage.ProductPage;
public class ProductTest extends BaseTest {
    @Test
    public void BrowseAndFilterProducts() {
        new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product1").toString())
                .clickSearchIconButton()
                .applyBrandFilter()
                .setMaxPrice(21100)
                .clickSortingLowToHigh()
                .verifyMatchingSelectedFilters()
                .verifySortingOrderCurrently();
    }
    @Test
    public void searchValidationInvalidInput(){
       ProductPage productPage= new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product2").toString())
                .clickSearchIconButton()
                .clickOnFirstProduct()
                .getFirstCategory();
        productPage.navigationBackButton();
        productPage.clickOnSecondProduct()
                .getSecondCategory();
        Assert.assertNotEquals(productPage.getFirstCategoryName(),productPage.getSecondCategoryName());
    }
    @Test
    public void productDetailsVerification(){
        SoftAssert softAssert = new SoftAssert();
        ProductPage productPage = new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product3").toString())
                .clickSearchIconButton()
                .clickOnFirstProductNotOutOfStock();
        softAssert.assertTrue(productPage.verifyProductTitleIsVisible());
        softAssert.assertTrue(productPage.verifyProductPriceIsVisible());
        softAssert.assertTrue(productPage.verifyProductAvailabilityStatus());
        softAssert.assertTrue(productPage.verifyProductImageIsVisible());
        softAssert.assertTrue(productPage.verifyProductRatingSection());
        softAssert.assertTrue(productPage.verifyAddToCartIsVisibleAndEnabled());
        softAssert.assertAll();
    }
    @Test
    public void AddMultipleProductsFreeShipping() {
        ProductPage productPage=new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product3").toString())
                .clickSearchIconButton()
                .clickFreeShippingButton()
                .setMaxPrice(Integer.parseInt(jsonFileManager.getValue("price1").toString()))
                .clickSortingHighToLow()
                .addNumOfProduct(3)
                .setMaxPrice(Integer.parseInt(jsonFileManager.getValue("price2").toString()))
                .addNumOfProduct(1);
        Assert.assertEquals(productPage.getCountOfItemOnCart(),4);
        Assert.assertTrue(productPage.getSubTotal() >6175 && productPage.getSubTotal() <=6500);
    }
}
