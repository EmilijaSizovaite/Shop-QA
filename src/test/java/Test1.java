import org.example.Main;
import org.example.PageElements;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Objects;

//Scenario one:
//        ▪ Using navigation menu, find mens Hoodies & Sweatshirts section.
//        ▪ Check/Assert that the displayed number of jackets matches the selected
//          number of jackets displayed per page.
//        ▪ Select “Frankie Sweatshirt” and open its details.
//        ▪ Select size, colour and quantity.
//        ▪ Add product to cart and check that cart icon is updated with product quantity.
//        ▪ Open cart and check if product match the one You added to the cart.
//        ▪ Proceed to checkout
//        ▪ Complete the order.

public class Test1 {
    static String productName = "Frankie Sweatshirt";
    static String productQuantity = "5";
    static String productSize = "XS";
    static String productColor = "White";
    static String productPrice = "$60.00";
    static String productSubtotal = "$300.00";
    static String addToCartMessage = "You added Frankie Sweatshirt to your shopping cart.";

    @BeforeClass
    public static void setup(){
        Main.setup(Main.URL);
    }

    @AfterClass
    public static void closeBrowser(){
        Main.closeBrowser();
    }

    @Test(priority = 1)
    public static void displayedNumberOfHoodies(){
        //Navigating to "Hoodies & Sweatshirts" section
        Main.hoverOverElement(PageElements.MEN_SECTION);
        Main.hoverOverElement(PageElements.MEN_TOPS_SECTION);
        Main.actionClickOnElement(PageElements.MEN_HOODIES_SECTION);

        //Checking if displayed number of hoodies matches the selected number of jackets displayed per page.
        int displayedItemsNumber;
        try {
            displayedItemsNumber = Integer.parseInt(Main.findElement(PageElements.DISPLAYED_ITEMS_NUMBER).getText());
        }catch (Exception ex) {
            displayedItemsNumber = Integer.parseInt(Main.findElement(PageElements.DISPLAYED_ITEMS_NUMBER_LESS).getText());
        }
        Main.scrollToPageEnd();
        int selectedItemsDisplayNumber = Integer.parseInt(Objects.requireNonNull(Main.findElement(PageElements.SELECTED_ITEMS_DISPLAY_NUMBER).getAttribute("value")));
        Assert.assertTrue(selectedItemsDisplayNumber >= displayedItemsNumber,
                "displayed hoodies number have to be equal" +
                " or less than selected display number per page\n" +
                " selectedItemsDisplayNumber: " + selectedItemsDisplayNumber +
                "\n displayedItemsNumber: " + displayedItemsNumber);
    }

    @Test(priority = 2)
    public static void addProductToCart(){
        //Clicking on “Frankie Sweatshirt”
        Main.actionClickOnElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[10]/div/a/span/span/img"));
        //Choosing shirt size
        Main.clickOnElement(PageElements.XS_SIZE);
        //Choosing shirt color
        Main.clickOnElement(PageElements.WHITE_COLOR);
        //Choosing quantity
        Main.sendKeys(PageElements.QUANTITY_FIELD, productQuantity);
        //Adding to cart
        Main.actionClickOnElement(PageElements.ADD_TO_CART);
        //checking if we got correct message
        Assert.assertEquals(Main.getText(PageElements.ADD_TO_CART_MESSAGE), addToCartMessage, "confirmation message after adding product to cart");
        //checking if cart icon is updated with new added quantity
        Assert.assertEquals(Main.getText(PageElements.CART_PRODUCT_QUANTITY), productQuantity, "cart icon has to be updated with product quantity");
    }

    @Test(priority = 3)
    public static void checkCart(){
        //Going to shopping cart page
        Main.actionClickOnElement(PageElements.CART);
        Main.clickOnElement(PageElements.CART_VIEW_AND_EDIT);
        //checking product name, size, color, price, quantity, subtotal
        Assert.assertEquals(Main.getText(PageElements.PRODUCT_NAME), productName);
        Assert.assertEquals(Main.getText(PageElements.PRODUCT_SIZE), productSize);
        Assert.assertEquals(Main.getText(PageElements.PRODUCT_COLOR), productColor);
        Assert.assertEquals(Main.getText(PageElements.PRODUCT_PRICE), productPrice);
        Assert.assertEquals(Main.getValue(PageElements.PRODUCT_QUANTITY), productQuantity);
        Assert.assertEquals(Main.getText(PageElements.PRODUCT_SUBTOTAL), productSubtotal);
        //checkout
        Main.actionClickOnElement(PageElements.CHECKOUT);
    }

    @Test(priority = 4)
    public static void completeOrder(){
        String billingDetails =
                "Cat The Cat\n" +
                "CatStreet\n" +
                "CatLand, Alabama 12345-6789\n" +
                "United States\n" +
                "+1234565";

       //Filling all required fields
        Main.sendKeys(PageElements.EMAIL, "random@gmail.com");
        Main.sendKeys(PageElements.FIRST_NAME, "Cat");
        Main.sendKeys(PageElements.LAST_NAME, "The Cat");
        Main.sendKeys(PageElements.STREET_ADDRESS, "CatStreet");
        Main.sendKeys(PageElements.CITY, "CatLand");
        Main.scrollToPageEnd();
        Main.selectDropDownByIndex(PageElements.STATE, 1);
        Main.sendKeys(PageElements.POSTAL_CODE, "12345-6789");
        Main.sendKeys(PageElements.PHONE_NUMBER, "+1234565");
        Main.clickOnElement(PageElements.SHIPPING_TABLE_RATE);
        //Clicking button NEXT
        Main.clickOnElement(PageElements.SHIPPING_NEXT);
        //Checking if filled in information matches billing address information
        Assert.assertEquals(Main.getText(PageElements.PLACE_ORDER_INFORMATION), billingDetails);
        //Place order, check url and success message
        Main.clickOnElement(PageElements.PLACE_ORDER);
        Main.waitForUrlChange("https://magento.softwaretestingboard.com/checkout/#payment");
        Assert.assertEquals(Main.browser.getCurrentUrl(),"https://magento.softwaretestingboard.com/checkout/onepage/success/");
        Assert.assertEquals(Main.getText(PageElements.COMPLETION_TEXT), "Thank you for your purchase!");
    }

}
