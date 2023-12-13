import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract public class BaseTest {

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        //Configuration.driverManagerEnabled = false;
        Configuration.browserSize = "1900x1080";
        Configuration.headless = false;
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    public void init(){
        setUp();
    }

//    @AfterEach
//    public void tearDown(){
//        Selenide.closeWebDriver();
//    }
}
