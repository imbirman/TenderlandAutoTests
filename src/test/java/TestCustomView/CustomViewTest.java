package TestCustomView;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomViewTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    CustomViewPage page = new CustomViewPage();

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
    @Description("Проверка количества элементов для выбора в блоке 'Поля таблицы' на вкладке 'Тендеры' по умолчанию")
    public void checkNumberElementsTableFieldsInTabTenderDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .getNumberElementsTableFieldsForSelection(), 11);
    }
}