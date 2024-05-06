package TestMyTendersFilters;

import TestMyTenders.MyTendersPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class MyTendersFiltersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");

    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");



    @Step("Ожидание {number}")
    public MyTendersFiltersPage waitFor(long number){
        sleep(number);
        return new MyTendersFiltersPage();
    }

    @Step("Ввести логин для авторизации")
    public MyTendersFiltersPage typeLogin(String login){loginField.sendKeys(login); return new MyTendersFiltersPage();}

    @Step("Ввести пароль для авторизации")
    public MyTendersFiltersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MyTendersFiltersPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public MyTendersFiltersPage clickLogInButton(){
        logInButton.click();
        return new MyTendersFiltersPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MyTendersFiltersPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MyTendersFiltersPage();
    }

    @Step("Нажать кнопку {button}")
    public MyTendersFiltersPage clickButton(SelenideElement button){
        button.click();
        return new MyTendersFiltersPage();
    }
}
