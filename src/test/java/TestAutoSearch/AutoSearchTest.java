package TestAutoSearch;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoSearchTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitAutoSearch";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";
    AutoSearchPage page = new AutoSearchPage();

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
    @Description("Проверка ошибки сохранения автопоиска при пустом названии автопоиска")
    public void checkSaveEmptyNameAutoSearch(){
        assertTrue(page.dragAndDropFilter(page.filterNameTender)
                .typeSearchInsideFilterNameTender("мусор")
                .waitFor(1000)
                .clickButton(page.buttonSaveAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonAcceptSaveAutoSearch)
                .isErrorMessageEmptyNameFieldAutoSearch());
    }

    @Test
    @Description("Проверка ошибки сохранения автопоиска при дублирующем названии автопоиска")
    public void checkSaveDuplicationNameAutoSearch(){
        assertTrue(page.dragAndDropFilter(page.filterNameTender)
                .typeSearchInsideFilterNameTender("мусор")
                .waitFor(1000)
                .clickButton(page.buttonSaveAutoSearch)
                .waitFor(500)
                .typeNameAutoSearch("Проверка поиска по дате подписания")
                .clickButton(page.buttonAcceptSaveAutoSearch)
                .waitFor(1000)
                .isErrorMessageDuplicateNameFieldAutoSearch());
    }


}
