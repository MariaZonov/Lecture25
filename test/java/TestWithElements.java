import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWithElements {
    WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    private Logger logger = LogManager.getLogger(TestWithElements.class);

    private final String LOGIN = System.getProperty("login");
    private final String PASSWORD = System.getProperty("password");

    @BeforeAll
    public static void setup() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {

        driver = new ChromeDriver();

    }
    @AfterEach
    public void downDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void courseTest() {

//        Открыть Chrome в headless режиме


//        Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
        options.addArguments("headless");

//        В поисковую строку ввести ОТУС
        WebElement search = driver.findElement(By.cssSelector("#searchbox_input"));
        search.sendKeys("ОТУС");

//        Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение современным ...
        WebElement searchButton = driver.findElement(By.xpath("//button[@aria-label = 'Search']"));
        searchButton.click();

        String searchExpected = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        search = driver.findElement(By.cssSelector("#r1-0 >div.ikg2IXiCD14iVX7AdZo1 > h2 > a > span"));
        String searchActual = search.getText();
        assertEquals(searchExpected, searchActual);
//    }
    }
    @Test
    public void window() {
        driver.manage().window().fullscreen();
//        Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
//        Нажать на любую картинку
        WebElement picture = driver.findElement(By.cssSelector("li[data-id=\"id-1\"]"));
        picture.click();
//                      Проверить что картинка открылась в модальном окне
        WebElement bird = driver.findElement(By.xpath("//*[@class=\"pp_hoverContainer\"]"));
    }

    @Test

    public void otus() {
//    Открыть Chrome в режиме полного экрана
//    Перейти на https://otus.ru
        driver.get("https://otus.ru");
        driver.manage().window().maximize();
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        login();
//    Вывести в лог все cookie
        System.out.print(driver.manage().getCookies());

        logger.info(driver.manage().getCookies());
    }
    public void login(){
        driver.findElement(By.className("sc-mrx253-0")).click();
        Actions action = new Actions(driver);
        WebElement eButton = driver.findElement(By.cssSelector(".liHMCp"));
        action.moveToElement(eButton).click().build().perform();
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        action.moveToElement(email).sendKeys(LOGIN).perform();
        WebElement pButton= driver.findElement(By.cssSelector(".sc-177u1yy-0"));
        action.moveToElement(pButton).click().build().perform();
        WebElement userPassword = driver.findElement(By.cssSelector("input[type=\"password\"]"));
        action.moveToElement(userPassword).sendKeys(PASSWORD).perform();
        WebElement lButton = driver.findElement(By.cssSelector(".gYNtqF"));
        action.moveToElement(lButton).click().build().perform();
    }


}