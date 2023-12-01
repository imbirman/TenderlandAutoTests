import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

abstract public class BaseTest {

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        //Configuration.driverManagerEnabled = false;
        Configuration.browserSize = "1900x1080";
        Configuration.headless = false;
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Before
    public void init(){
        setUp();
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
