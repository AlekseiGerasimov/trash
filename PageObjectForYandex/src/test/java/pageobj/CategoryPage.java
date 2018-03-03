package pageobj;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class CategoryPage extends BaseYandexPage {
    CategoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath="//div[@class='layout-grid__col layout-grid__col_width_2']")
    WebElement goods;

    public ProductPage productSearch(String product_name){
        String str = (".//a[contains(text(),'").concat(product_name).concat("')]");
        try {
            goods.findElement(By.xpath(str)).click();
        }
        catch(WebDriverException e){
            driver.findElement(By.xpath("//input[@id='header-search']")).clear();
            driver.findElement(By.xpath("//input[@id='header-search']")).click();
            driver.findElement(By.xpath("//input[@id='header-search']")).sendKeys(product_name);
            Actions builder = new Actions(driver);
            builder.sendKeys(Keys.ENTER).perform();
        }
        return new ProductPage(driver);
    }
}
