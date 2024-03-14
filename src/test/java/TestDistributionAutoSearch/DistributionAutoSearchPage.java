package TestDistributionAutoSearch;

import TestCustomView.CustomViewPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class DistributionAutoSearchPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    @Step("Ожидание {number}")
    public DistributionAutoSearchPage waitFor(long number){
        sleep(number);
        return new DistributionAutoSearchPage();
    }

    @Step("Прокрутить до элемента")
    public DistributionAutoSearchPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new DistributionAutoSearchPage();
    }

    @Step("Ввести логин для авторизации")
    public DistributionAutoSearchPage typeLogin(String login){loginField.sendKeys(login); return new DistributionAutoSearchPage();}

    @Step("Ввести пароль для авторизации")
    public DistributionAutoSearchPage typePassword(String password){
        passwordField.sendKeys(password);
        return new DistributionAutoSearchPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public DistributionAutoSearchPage clickLogInButton(){
        logInButton.click();
        return new DistributionAutoSearchPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public DistributionAutoSearchPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new DistributionAutoSearchPage();
    }

    @Step("Нажать кнопку {button}")
    public DistributionAutoSearchPage clickButton(SelenideElement button){
        button.click();
        return new DistributionAutoSearchPage();
    }
}