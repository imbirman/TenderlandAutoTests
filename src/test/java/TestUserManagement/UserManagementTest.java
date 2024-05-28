package TestUserManagement;

import Base.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserManagementTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    UserManagementPage page = new UserManagementPage();

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
                .clickConfirmLogInButton();
    }

    @Test
    @Description("Проверка подписи о времени пользователя в кабинете по умолчанию")
    public void checkDefaultLabelUserTime(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openWindowUserManagement)
                .waitFor(500)
                .clickButton(page.buttonOpenManagementProfile)
                .waitFor(500)
                .isDefaultLabelTimeUser());
    }

    @Test
    @Description("Проверка подписи о времени пользователя в кабинете после выбора чекбокса 'Приводить время к выбранному'")
    public void checkLabelTimeUserAfterSelectedCheckbox(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openWindowUserManagement)
                .waitFor(500)
                .clickButton(page.buttonOpenManagementProfile)
                .waitFor(500)
                .clickButton(page.checkboxCastTimeToUser)
                .waitFor(500)
                .isLabelTimeUserAfterSelectedCheckbox());
    }

    @Test
    @Description("Проверка наличия сообщения об ошибке добавления почты при пустом поле для ввода почты")
    public void checkVisibleMessageErrorEmptyFieldAddedMail(){
        assertTrue(page.waitFor(1000)
                .clickButton(page.openWindowUserManagement)
                .waitFor(500)
                .clickButton(page.buttonOpenManagementMailing)
                .waitFor(500)
                .clickButton(page.buttonAddedMail)
                .waitFor(500)
                .isVisibleMessageErrorEmptyFieldAddedMail());
    }

    @Test
    @Description("Проверка сообщения об ошибке добавления почты при пустом поле для ввода почты")
    public void checkMessageErrorEmptyFieldAddedMail(){
        assertEquals(page.waitFor(1500)
                .clickButton(page.openWindowUserManagement)
                .waitFor(500)
                .clickButton(page.buttonOpenManagementMailing)
                .waitFor(500)
                .clickButton(page.buttonAddedMail)
                .waitFor(500)
                .getMessageErrorEmptyFieldAddedMail(), "Поле не заполнено");
    }
}
