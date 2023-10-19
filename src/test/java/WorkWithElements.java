import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkWithElements {
    WebDriver driver;
    private Logger logger = LogManager.getLogger(WorkWithElements.class);

    @BeforeAll
    public static void setup() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {

        driver = new ChromeDriver();

    }

    @Test
    public void courseTest() {

//        Открыть Chrome в headless режиме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
//        Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");

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
//                       Проверить что картинка открылась в модальном окне
            WebElement bird = driver.findElement(By.xpath("//*[@class=\"pp_hoverContainer\"]"));
        }

        @Test

        public void otus() {
//        Открыть Chrome в режиме полного экрана
            driver.manage().window().maximize();
//    Перейти на https://otus.ru
            driver.get("https://otus.ru");
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
            login();
//    Вывести в лог все cookie
        System.out.print(driver.manage().getCookies());

        logger.info(driver.manage().getCookies());
        }
        public void login(){
        WebElement loginButton = driver.findElement(By.xpath("//div[@class=\"sc-r03h0s-5 bYKNcH\"]"));
        loginButton.click();
        WebElement userName = driver.findElement(By.cssSelector("input[name=\"email\"]"));
        userName.sendKeys("ceyogo9446@bitvoo.com");
        WebElement userPassword = driver.findElement(By.cssSelector("input[type=\"password\"]"));
        userPassword.sendKeys("Ceyogo9446!");
        WebElement submitLogin = driver.findElement(By.xpath("//button[@class=\"sc-9a4spb-0 gYNtqF sc-11ptd2v-2-Component cElCrZ\"]"));
        submitLogin.click();
    }

        @AfterEach
        public void downDriver() {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
}






