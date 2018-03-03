package pageobj;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketPage extends BaseYandexPage {
    MarketPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement searchfield;
    @FindBy(xpath = "//div[@class='topmenu i-bem topmenu_js_inited']")
    WebElement allcategories;

    public CategoryPage selectCategory(String name_category){
        String str = (".//a[contains(text(),'").concat(name_category).concat("')]");
        try{
            allcategories.findElement(By.xpath(str)).click();
        }
        catch(WebDriverException e){
            searchfield.sendKeys(name_category);
            Actions builder = new Actions(driver);
            builder.sendKeys(Keys.ENTER).perform();
        }
        return new CategoryPage(driver);
    }

}
