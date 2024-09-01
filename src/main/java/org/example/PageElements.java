package org.example;

import org.openqa.selenium.By;


public class PageElements {
    //Main page
    public static final By MEN_SECTION = By.xpath("//*[@id=\"ui-id-5\"]");
    public static final By MEN_TOPS_SECTION = By.xpath("//*[@id=\"ui-id-17\"]");
    public static final By MEN_HOODIES_SECTION = By.xpath("//*[@id=\"ui-id-20\"]");

    public static final By WOMEN_SECTION = By.xpath("//*[@id=\"ui-id-4\"]");
    public static final By WOMEN_BOTTOMS_SECTION = By.xpath("//*[@id=\"ui-id-10\"]");
    public static final By WOMEN_PANTS_SECTION = By.xpath("//*[@id=\"ui-id-15\"]");

    //Section page
    public static final By SORT_BY = By.xpath("//*[@id=\"sorter\"]");
    public static final By DISPLAYED_ITEMS_NUMBER = By.xpath("//*[@id=\"toolbar-amount\"]/span[2]"); //used when items number are higher than items number shown per page. Example "Items 1-12 of 13"
    public static final By DISPLAYED_ITEMS_NUMBER_LESS = By.xpath("//*[@id=\"toolbar-amount\"]/span"); //used when items number per page are less than items number shown per page. Example "13 Items"
    public static final By SELECTED_ITEMS_DISPLAY_NUMBER = By.xpath("//*[@id=\"limiter\"]/option[@selected='selected']");
    public static final By REMOVE_ITEM = By.xpath("//a[@title='Remove item']");
    public static final By REMOVE_CONFIRMATION_TEXT = By.xpath("//*[@id=\"modal-content-150\"]/div");

    public static final String TOP_CART_ITEM_NAME_PATH = "//*[@id=\"mini-cart\"]/li[1]/div/div/strong/a";
    public static final String REMOVE_OK_BUTTON_PATH = "/html/body/div[4]/aside[2]/div[2]/footer/button[2]";
    public static final String REMOVE_CANCEL_BUTTON_PATH = "/html/body/div[4]/aside[2]/div[2]/footer/button[1]";
    public static final String HOVER_ADD_TO_CART_PATH = "//button[@title='Add to Cart']";
    public static final String SELECT_SIZE_28_PATH = "//*[@id=\"option-label-size-143-item-171\"]";
    public static final String SELECT_COLOR_PATH = "//div[@class='swatch-option color']";


    public static String getProductPathByNumber(String number){
        return "//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[" + number + "]";
    }

    //Product page
    public static final By XS_SIZE = By.xpath("//*[@id=\"option-label-size-143-item-166\"]");
    public static final By WHITE_COLOR = By.xpath("//*[@id=\"option-label-color-93-item-59\"]");
    public static final By QUANTITY_FIELD = By.xpath("//*[@id=\"qty\"]");
    public static final By ADD_TO_CART = By.xpath("//*[@id=\"product-addtocart-button\"]");
    public static final By ADD_TO_CART_MESSAGE = By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div");
    public static final By CART_PRODUCT_QUANTITY = By.xpath("//span[contains(@class, 'counter qty') and not(contains(@class, '_block-content-loading'))]/span[@class='counter-number']");
    public static final By CART = By.cssSelector("span.counter.qty");
    public static final By CART_VIEW_AND_EDIT = By.xpath("//*[@id=\"minicart-content-wrapper\"]/div[2]/div[5]/div/a");

    //Sopping cart page
    public static final By PRODUCT_NAME = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[1]/div/strong/a");
    public static final By PRODUCT_SIZE = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[1]/div/dl/dd[1]");
    public static final By PRODUCT_COLOR = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[1]/div/dl/dd[2]");
    public static final By PRODUCT_QUANTITY = By.xpath("/html/body/div[2]/main/div[3]/div/div[2]/form/div[1]/table/tbody/tr[1]/td[3]/div/div/label/input");
    public static final By PRODUCT_PRICE = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[2]/span/span/span");
    public static final By PRODUCT_SUBTOTAL = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[4]/span/span/span");
    public static final By CHECKOUT = By.cssSelector("div.cart-container > div.cart-summary > ul > li:nth-child(1) > button");
    public static final By FIRST_SUGGESTED_PRODUCT = By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[4]/div[2]/div/ol/li[1]/div/div/div[3]/div[1]/form/button");
    public static final By LAST_PRODUCT_NAME = By.cssSelector("#shopping-cart-table > tbody:last-child > tr.item-info > td.col.item > div > strong > a");
    public static final By FIRST_SUGGESTED_PRODUCT_NAME = By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[4]/div[2]/div/ol/li[1]/div/div/strong/a");
    public static final By CART_MESSAGE = By.xpath("//*[@data-ui-id='checkout-cart-validationmessages-message-success']");

    //Shipping page
    public static final By EMAIL = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[1]/fieldset/div/div/input");
    public static final By FIRST_NAME = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[1]/div/input");
    public static final By LAST_NAME = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[2]/div/input");
    public static final By STREET_ADDRESS = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/fieldset/div/div[1]/div/input");
    public static final By CITY = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[4]/div/input");
    public static final By STATE = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[5]/div/select");
    public static final By POSTAL_CODE = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[7]/div/input");
    public static final By PHONE_NUMBER = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[9]/div/input");
    public static final By SHIPPING_TABLE_RATE = By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input");
    public static final By SHIPPING_NEXT = By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button");

    //Review and payments page
    public static final By PLACE_ORDER_INFORMATION = By.cssSelector("div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > div.billing-address-details");
    public static final By PLACE_ORDER = By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button");

    //Purchase completion page
    public static final By COMPLETION_TEXT = By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
}
