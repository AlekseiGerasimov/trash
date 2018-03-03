package pageobj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseYandexPage {
    WebDriver driver;
    BaseYandexPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@data-id='market']")
    private WebElement marketButton;

    public MarketPage clickToMarket(){
        marketButton.click();
        return new MarketPage(driver);
    }
}
