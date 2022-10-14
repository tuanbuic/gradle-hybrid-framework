package pageObjects.saucelab;


import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPageObject extends BasePage {
    WebDriver driver;

    public ProductPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDropdown(String option) {
        waitForElementVisible(driver, ProductPageUI.SELECT_DROPDOWN);
        selectItemInDefaultDropdownByVisibleText(driver, ProductPageUI.SELECT_DROPDOWN,option);
    }


    public boolean isSortByAscending() {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> listElement = getListElements(driver, ProductPageUI.LIST_PRODUCT);
        for(WebElement element : listElement){
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>(arrayList);
        Collections.sort(sortedList);
        return sortedList.equals(arrayList);
    }
    public boolean isSortByDecending() {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> listElement = getListElements(driver, ProductPageUI.LIST_PRODUCT);
        for(WebElement element : listElement){
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>(arrayList);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return sortedList.equals(arrayList);
    }
    public boolean isProductPriceSortedAscending() {
        ArrayList<Float> arrayList = new ArrayList<>();

        List<WebElement> listElement = getListElements(driver, ProductPageUI.LIST_PRODUCT_PRICE);
        for(WebElement element : listElement){
            arrayList.add(Float.parseFloat(element.getText().replace("$","")));
        }
        ArrayList<Float> sortedList = new ArrayList<>(arrayList);
        Collections.sort(sortedList);
        return sortedList.equals(arrayList);
    }

    public boolean isProductPriceSortedDescending() {
        ArrayList<Float> arrayList = new ArrayList<>();

        List<WebElement> listElement = getListElements(driver, ProductPageUI.LIST_PRODUCT_PRICE);
        for(WebElement element : listElement){
            arrayList.add(Float.parseFloat(element.getText().replace("$","")));
        }
        ArrayList<Float> sortedList = new ArrayList<>(arrayList);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return sortedList.equals(arrayList);
    }

}
