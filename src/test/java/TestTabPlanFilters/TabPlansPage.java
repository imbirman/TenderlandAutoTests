package TestTabPlanFilters;

import TestTabContractFilters.TabContractsPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class TabPlansPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    @Step("Ожидание {number}")
    public TabPlansPage waitFor(long number){
        sleep(number);
        return new TabPlansPage();
    }

    @Step("Ввести логин для авторизации")
    public TabPlansPage typeLogin(String login){loginField.sendKeys(login); return new TabPlansPage();}

    @Step("Ввести пароль для авторизации")
    public TabPlansPage typePassword(String password){
        passwordField.sendKeys(password);
        return new TabPlansPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public TabPlansPage clickLogInButton(){
        logInButton.click();
        return new TabPlansPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public void clickConfirmLogInButton(){
        confirmLogInButton.click();
    }

    @Step("Нажать кнопку{button}")
    public TabPlansPage clickButton(SelenideElement button){
        button.click();
        return new TabPlansPage();
    }
}
