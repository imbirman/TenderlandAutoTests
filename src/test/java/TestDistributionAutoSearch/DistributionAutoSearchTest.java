package TestDistributionAutoSearch;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistributionAutoSearchTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitAutoSearch";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    DistributionAutoSearchPage page = new DistributionAutoSearchPage();

    @Description("Открытие сайта для входа")
    private void openURL(String url){
        Selenide.open(url);
    }

    @BeforeEach
    @Description("Ввод логина/пароля и вход на сайт")
    public void beforeMethod(){
        openURL(BASE_URL);
        page.clickLogInButton()
                .waitFor(500)
                .typeLogin(BASE_LOGIN)
                .waitFor(400)
                .typePassword(BASE_PASSWORD)
                .clickConfirmLogInButton();
    }

    @Test
    @Description("Проверка отсутствия даты включения рассылки автопоиска")
    public void checkDisabledDateDistribution(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .getLabelDistribution(), "Включить рассылку");
    }

    @Test
    @Description("Проверка отображения даты включения рассылки автопоиска")
    public void checkVisibleDateDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .checkLabelEnableDistribution());
    }
}
