package TestAuditor;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuditorTest extends BaseTest {
    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitAuditor";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";
    AuditorPage page = new AuditorPage();

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
                .clickConfirmLogInButton()
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonTabMenuAuditor);
    }

    @Test
    @Description("Проверка результатов поиска по реквизитам организации")
    public void checkResultSearchByOrganizationDetails(){
        assertTrue(page.DragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("234703774440")
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .isContainOrganizationDetail());
    }

}
