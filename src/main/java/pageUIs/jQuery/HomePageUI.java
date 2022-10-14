package pageUIs.jQuery;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String ACTIVED_PAGING = "xpath=//a[@class='qgrd-pagination-page-link active' and contains(text(),'%s')]";
    public static final String TOTAL_PAGE = "xpath=//ul[@class='qgrd-pagination-ul']//li[@class='qgrd-pagination-page']";
    public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']//li[@class='qgrd-pagination-page'][%s]/a";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";
    public static final String FEMALES_COLLUMN = "xpath=//td[@data-key='females']";
    public static final String COUNTRY_COLLUMN = "xpath=//td[@data-key='country']";
    public static final String MALES_COLLUMN = "xpath=//td[@data-key='males']";
    public static final String TOTAL_COLLUMN = "xpath=//td[@data-key='total']";
    public static final String HEADER_TEXTBOX = "xpath=//div[contains(text(),'%s')]/parent::div/following-sibling::input";
    public static final String COLLUMN_INDEX_HEADER = "xpath=//tr//td[text()='%s']/preceding-sibling::td";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input";
    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input[@type='checkbox']";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/select";

    public static final String BUTTON_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]//button[contains(@title,'%s')]";
    // UPLOADFILE
    public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
    public static final String FILE_LOADED = "xpath=//p[@class='name' and text()='%s']";
}

