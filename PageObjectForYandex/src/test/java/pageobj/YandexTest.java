package pageobj;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class YandexTest {
    BaseYandexPage page;

    @Before
    public void before(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru");
        page = new BaseYandexPage(driver);
    }


    @Test
    public void test_tv(){
        page.clickToMarket().selectCategory("Электроника").productSearch("Телевизоры").setMinPrice("20000").
                setModels("LG", "Samsung").clickButtonToBeginSearch().setNumberOfElementsEquals12().checkNumbersOfElements("12").selectNumberOfElementAndFillTheFieldOfSearch("1").asserted();

    }

    @Test
    public void test_tv1(){
        page.clickToMarket().selectCategory("Электроника").productSearch("Наушники и Bluetooth-гарнитуры").setMinPrice("5000").
                setModels("Beats","Apple").clickButtonToBeginSearch().setNumberOfElementsEquals12().checkNumbersOfElements("12").selectNumberOfElementAndFillTheFieldOfSearch("1").asserted();

    }

}
