package pageobj;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductPage extends BaseYandexPage {
    ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath="//div[@class='layout__col i-bem exp-filters-new-design layout__col_search-filters_visible']")
    WebElement parameters;

    @FindBy(xpath="//input[@id='header-search']")
    WebElement inputofsearch;
    public ProductPage setMinPrice(String price){
        parameters.findElement(By.xpath("//input[@id='glf-pricefrom-var']")).sendKeys(price);
        return this;
    }

    public ProductPage setModels(String... models){
        for(int i = 0; i < models.length ; ++i){
            String str = (".//label[contains(text(),'").concat(models[i]).concat("')]");
            parameters.findElement(By.xpath(str)).click();
        }
        return this;
    }

    public ProductPage clickButtonToBeginSearch(){
        parameters.findElement(By.xpath(".//button[@class='button button_action_n-filter-apply button_size_s button_pseudo_yes button_theme_pseudo i-bem button_js_inited']")).click();
        return this;
    }

    public ProductPage setNumberOfElementsEquals12(){
        driver.findElement(By.xpath("//*[@class='select__control']/..")).click();
        driver.findElement(By.xpath("//span[text()='Показывать по 12']")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        try{
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//div[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id]"),12));
        }
        catch(WebDriverException ex){
            wait.until(ExpectedConditions.numberOfElementsToBe(
            By.xpath("//div[@class='n-snippet-list n-snippet-list_type_grid snippet-list_size_3 metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id]"),12));
        };

        return this;
    }

    public ProductPage checkNumbersOfElements(String number){
        driver.findElement(By.xpath("//input[@class='radio-button__control']")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//div[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id]"),12));
        Assert.assertEquals(number,String.valueOf(driver.findElements(By.xpath("//div[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id]")).size()));
        return this;
    }

    public ResultPage selectNumberOfElementAndFillTheFieldOfSearch(String number){
        String str= ("//div[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id][").
                concat(number).concat("]//div[@class='n-snippet-card2__title']");
        inputofsearch.clear();
        String str1 = driver.findElement(By.xpath(str)).getText();
        inputofsearch.sendKeys(str1);
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ENTER).perform();
        return new ResultPage(driver,str1);
    }
}
