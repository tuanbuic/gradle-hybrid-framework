package pageObjects.jQuery;


import common.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.HomePageUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void openPagingByPageNumber(String pageNumber){
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
    }

    public void enterHeaderTextBoxByLabel(String title, String text) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX,title);
        sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX,text,title);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX, Keys.ENTER,title);
    }
    public boolean verifyPagingActive(String page) {
        waitForElementVisible(driver, HomePageUI.ACTIVED_PAGING,page);
        return isElementDisplay(driver, HomePageUI.ACTIVED_PAGING,page);
    }

    public  List<Map<String,String>> getValueEachRowAtAllPage() {
        int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGE);
        List<Map<String,String>> allValue = new ArrayList<>();
        for(int i = 1 ;i<= totalPage;i++){
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX,String.valueOf(i));
            List<WebElement> allRowElementEachPage = getListElements(driver, HomePageUI.ALL_ROW_EACH_PAGE);
            for(WebElement eachRow : allRowElementEachPage){
                Map<String, String> map = new HashMap<String, String>();
                String females  = getTextChildElement(driver, eachRow, HomePageUI.FEMALES_COLLUMN);
                String country  = getTextChildElement(driver, eachRow, HomePageUI.COUNTRY_COLLUMN);
                String males  = getTextChildElement(driver, eachRow, HomePageUI.MALES_COLLUMN);
                String total  = getTextChildElement(driver, eachRow, HomePageUI.TOTAL_COLLUMN);
                map.put("females",females);
                map.put("country",country);
                map.put("males",males);
                map.put("total",total);
                allValue.add(map);
            }
        }
        for(Map<String, String> value : allValue){
            System.out.println("-----------------");
            System.out.println(value);
        }

        return allValue;
    }

    public void enterToTextBoxByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
        int columnIndex = getElementSize(driver, HomePageUI.COLLUMN_INDEX_HEADER,columnName)+1;
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,value, rowNumber, String.valueOf(columnIndex));
    }

    public void selectDropDownByColumnNameInRowNumber(String columnName, String rowNumber, String value) {
        int columnIndex = getElementSize(driver, HomePageUI.COLLUMN_INDEX_HEADER,columnName)+1;
        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        selectItemInDefaultDropdownByVisibleText(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX,value, rowNumber, String.valueOf(columnIndex));
    }

    public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        int columnIndex = getElementSize(driver, HomePageUI.COLLUMN_INDEX_HEADER,columnName)+1;
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        checkDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
    }

    public void ClickToIconByRowNumber(String rowNumber, String action) {
        waitForElementClickable(driver, HomePageUI.BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,action);
        clickToElement(driver, HomePageUI.BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,action);

    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOADED,fileName);
        return isElementDisplay(driver, HomePageUI.FILE_LOADED,fileName);
    }
}
