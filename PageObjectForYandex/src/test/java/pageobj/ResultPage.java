package pageobj;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage extends BaseYandexPage {
    private String str;
    ResultPage(WebDriver driver,String str){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.str = str;
    }


    @FindBy(xpath="//div[@class='n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited']/div[@data-id][1]//a[@class='link n-link_theme_blue']")
    WebElement result_list;
    @FindBy(xpath="//h1")
    WebElement result;

    public void asserted(){
        try {
            Assert.assertEquals(str, result_list.getAttribute("title"));
        }
        catch(WebDriverException ex){
            Assert.assertEquals(str, result.getText());
        }
    }

}
