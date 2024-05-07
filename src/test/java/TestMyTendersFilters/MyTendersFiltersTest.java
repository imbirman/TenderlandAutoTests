package TestMyTendersFilters;

import Base.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTendersFiltersTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitMyTenders";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    MyTendersFiltersPage page = new MyTendersFiltersPage();

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
                .waitFor(500)
                .typePassword(BASE_PASSWORD)
                .waitFor(2000)
                .clickConfirmLogInButton()
                .waitFor(5000)
                .clickButton(page.openTabMenu.shouldBe(Condition.interactable))
                .waitFor(500)
                .clickButton(page.buttonTabMenuMyTenders);
    }

    @Test
    @Description("Проверка количества фильтров")
    public void checkNumberFilters(){
            assertEquals(page.waitFor(2000)
                    .clickButton(page.buttonOpenListFilters)
                    .getNumberFilters(), 11);
    }

    @Test
    @Description("Проверка поиска по реестровому номеру тендера")
    public void checkSearchByRegisterNumberTenderInTabCard(){
            assertTrue(page.waitFor(2000)
                    .typeSearchByTender("01")
                    .waitFor(1000)
                    .isCheckSearchByRegisterNumberTender());
    }

    @Test
    @Description("Проверка поиска по названию тендера")
    public void checkSearchByNameTenderInTabCard(){
        assertTrue(page.waitFor(2000)
                .typeSearchByTender("усл")
                .waitFor(1000)
                .isCheckSearchByNameTender());
    }
}
