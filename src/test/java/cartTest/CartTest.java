package cartTest;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.cartPage.CartPage;
import pages.productPage.ProductPage;

public class CartTest extends BaseTest {


    @Test
    public void AddMultipleProductsToCart(){
        new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product4").toString())
                .clickSearchIconButton();
        new CartPage(driver)
                .clickOnFirstProduct()
                .clickAddToCart()
                .navigateBackTwice()
                .openSecondProduct()
                .clickAddToCart()
                .clickOnCart()
                .verifyChosenProductsInCart();


    }
    @Test
     public void UpdateCartQuantity(){
        new ProductPage(driver)
                .searchBarInsertion(jsonFileManager.getValue("product1").toString())
                .clickSearchIconButton();
        CartPage cartPage=new CartPage(driver)
                .clickOnFirstProduct()
                .clickAddToCart()
                .clickOnCart()
                .incrementProduct(2);
        Assert.assertEquals(cartPage.getCartCounter(),"3");
        Assert.assertEquals(cartPage.getSubTotalPrice(),cartPage.getProductPrice(3));
        cartPage.decrementProduct(2);
        Assert.assertEquals(cartPage.getSubTotalPrice(),cartPage.getProductPrice());
    }


}
