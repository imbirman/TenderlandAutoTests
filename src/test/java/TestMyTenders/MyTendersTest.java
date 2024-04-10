package TestMyTenders;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTendersTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitMyTenders";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    MyTendersPage page = new MyTendersPage();

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
                .clickButton(page.buttonTabMenuMyTenders);
    }

    @Test
    @Description("Проверка появления окна подтверждения удаления тендера")
    public void checkWindowApproveDeleteTender(){
        assertTrue(page.clickButton(page.buttonDeleteAddedTenderInListTenders)
                .isCheckVisibleWindowApproveDelete());
    }

    @Test
    @Description("Проверка кликабельности кнопки удаления в списке тендеров")
    public void checkClickableButtonDeleteTenderInListTenders(){
        assertTrue(page.waitFor(500)
                .isCheckClickableButtonDeleteTenderInListTenders());
    }

    @Test
    @Description("Проверка кликабельности кнопки выгрузки документации в списке тендеров")
    public void checkClickableButtonLoadDocumentationTenderInListTenders(){
        assertTrue(page.waitFor(500)
                .isCheckClickableButtonLoadDocumentationTenderInListTenders());
    }

}
