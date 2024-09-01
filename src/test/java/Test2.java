import org.example.Main;
import org.example.PageElements;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Scenario two:
//        ▪ Using navigation menu, find women pants section.
//        ▪ Filter section to show the cheapest products available.
//        ▪ Select the cheapest pants and add them to the cart.
//        ▪ Add 2 more products to the cart. Check that cart icon is updated with each
//          product.
//        ▪ Remove product from the cart.
//        ▪ Go to cart page and add product to the cart from suggested products.
//        ▪ Checkout
//        ▪ Complete the order.

public class Test2 {
    @BeforeClass
    public static void setup(){
        Main.setup(Main.URL);
        //Navigating to "Women pants" section
        Main.hoverOverElement(PageElements.WOMEN_SECTION);
        Main.hoverOverElement(PageElements.WOMEN_BOTTOMS_SECTION);
        Main.actionClickOnElement(PageElements.WOMEN_PANTS_SECTION);

        //Filter pants by price from cheapest to most expensive
        Main.selectDropDownByValue(PageElements.SORT_BY, "price");
    }

    @AfterClass
    public static void closeBrowser(){
        Main.closeBrowser();
    }

    @DataProvider(name = "addProductToCart")
    public Object[][] dataProvidedMethod() { return new Object[][]{
            {PageElements.getProductPathByNumber("1"), "1"}, //getting first cheapest pants
            {PageElements.getProductPathByNumber("2"), "2"}, //second pants
            {PageElements.getProductPathByNumber("3"), "3"}  //third pants
    };}

    @Test(priority = 1, dataProvider = "addProductToCart")
    public static void addProductToCart(String productPath, String expectedQuantity) {
        //Scroll to product
        Main.scrollTillElementIsFound(By.xpath(productPath));
        //Hover over selected product
        Main.hoverOverElement(By.xpath(productPath));
        //Choose pants size
        Main.clickOnElement(By.xpath(productPath + PageElements.SELECT_SIZE_28_PATH));
        //Choose color
        Main.clickOnElement(By.xpath(productPath + PageElements.SELECT_COLOR_PATH));
        //Add to cart
        Main.clickOnElement(By.xpath(productPath + PageElements.HOVER_ADD_TO_CART_PATH));
        //Checking if cart number updated
        Main.scrollTillElementIsFound(PageElements.CART);
        Assert.assertEquals(Main.getText(PageElements.CART_PRODUCT_QUANTITY), expectedQuantity);
    }

    @Test(priority = 2)
    public static void deleteProductFromCart(){
        String removeConfirmationText = "Are you sure you would like to remove this item from the shopping cart?";
        //Opening cart
        Main.clickOnElement(PageElements.CART);
        String deleteProductName = Main.getText(By.xpath(PageElements.TOP_CART_ITEM_NAME_PATH));
        //Delete product from top of the list
        Main.clickOnElement(PageElements.REMOVE_ITEM);
        //Checking confirmation message
        Assert.assertEquals(Main.getText(PageElements.REMOVE_CONFIRMATION_TEXT), removeConfirmationText);
        //Checking buttons names cancel, ok
        Assert.assertEquals(Main.getText(By.xpath(PageElements.REMOVE_CANCEL_BUTTON_PATH + "/span")), "Cancel");
        Assert.assertEquals(Main.getText(By.xpath(PageElements.REMOVE_OK_BUTTON_PATH + "/span")), "OK");
        //Clicking OK to confirm remove, refresh page, open cart and check if item was deleted
        Main.clickOnElement(By.xpath(PageElements.REMOVE_OK_BUTTON_PATH));
        //Going to cart page
        Main.clickOnElement(PageElements.CART_VIEW_AND_EDIT);
        //Checking if product got deleted
        Assert.assertNotEquals(Main.getText(PageElements.PRODUCT_NAME), deleteProductName);
    }

    @Test(priority = 3)
    public static void addSuggestedProductToCart(){
        //Adding to the cart first suggested product
        Main.scrollTillElementIsFound(PageElements.FIRST_SUGGESTED_PRODUCT);
        String suggestedProductName = Main.getText(PageElements.FIRST_SUGGESTED_PRODUCT_NAME);
        String expectedMessage = "You added " + suggestedProductName + " to your shopping cart.";
        Main.clickOnElement(PageElements.FIRST_SUGGESTED_PRODUCT);

        //Checking confirmation message and if it appeared in list
        Main.scrollToPageTop();
        try {
            String conformationMessage = Main.getText(PageElements.CART_MESSAGE);
            Assert.assertEquals(conformationMessage, expectedMessage);
        }catch (Exception ex){
            System.out.println("Item added to the cart, confirmation message doesn't appear" + ex);
        }
        Main.browser.navigate().refresh();
        Main.scrollTillElementIsFound(PageElements.LAST_PRODUCT_NAME);
        Assert.assertEquals(Main.getText(PageElements.LAST_PRODUCT_NAME), suggestedProductName);
    }

    @Test(priority = 4)
    public static void completeOrder(){
        Main.scrollTillElementIsFound(PageElements.CHECKOUT);
        Main.actionClickOnElement(PageElements.CHECKOUT);
        Test1.completeOrder();
    }
}
