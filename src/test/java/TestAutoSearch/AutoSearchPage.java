package TestAutoSearch;

import TestAuditor.AuditorPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class AutoSearchPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");



    @Step("Ожидание {number}")
    public AutoSearchPage waitFor(long number){
        sleep(number);
        return new AutoSearchPage();
    }
    @Step("Нажать кнопку для открытия окна авторизации")
    public AutoSearchPage clickLogInButton(){
        logInButton.click();
        return new AutoSearchPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public AutoSearchPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new AutoSearchPage();
    }

    @Step("Ввести логин для авторизации")
    public AutoSearchPage typeLogin(String login){loginField.sendKeys(login); return new AutoSearchPage();}

    @Step("Ввести пароль для авторизации")
    public AutoSearchPage typePassword(String password){
        passwordField.sendKeys(password);
        return new AutoSearchPage();
    }
}
