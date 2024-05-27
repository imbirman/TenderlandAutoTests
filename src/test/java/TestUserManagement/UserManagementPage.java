package TestUserManagement;

import TestMyTenders.MyTendersPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class UserManagementPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */




    @Step("Ожидание {number}")
    public MyTendersPage waitFor(long number){
        sleep(number);
        return new MyTendersPage();
    }

    @Step("Ввести логин для авторизации")
    public MyTendersPage typeLogin(String login){loginField.sendKeys(login); return new MyTendersPage();}

    @Step("Ввести пароль для авторизации")
    public MyTendersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MyTendersPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public MyTendersPage clickLogInButton(){
        logInButton.click();
        return new MyTendersPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MyTendersPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MyTendersPage();
    }

    @Step("Нажать кнопку {button}")
    public MyTendersPage clickButton(SelenideElement button){
        button.click();
        return new MyTendersPage();
    }
}
