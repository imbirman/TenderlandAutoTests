package TestGeneralChecks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class GeneralChecksPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Область подсказки */
    private final SelenideElement hintArea = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");

    /** Фильтр логики И/ИЛИ */
    protected SelenideElement filterAndOr = $x("//div[@id='tl-filter-root']//span");


    @Step("Ожидание {number}")
    public GeneralChecksPage waitFor(long number){
        sleep(number);
        return new GeneralChecksPage();
    }

    @Step("Прокрутить до элемента")
    public GeneralChecksPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
        return new GeneralChecksPage();
    }

    @Step("Ввести логин для авторизации")
    public GeneralChecksPage typeLogin(String login){loginField.sendKeys(login); return new GeneralChecksPage();}

    @Step("Ввести пароль для авторизации")
    public GeneralChecksPage typePassword(String password){
        passwordField.sendKeys(password);
        return new GeneralChecksPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public GeneralChecksPage clickLogInButton(){
        logInButton.click();
        return new GeneralChecksPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public GeneralChecksPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new GeneralChecksPage();
    }

    @Step("Нажать кнопку {button}")
    public GeneralChecksPage clickButton(SelenideElement button){
        button.click();
        return new GeneralChecksPage();
    }

    @Step("Проверка области подсказки для фильтра И")
    public boolean isCorrectHintAreaAnd(){
        return hintArea.getText().contains("Перенесите в область фильтры\n" +
                "которые должны работать\n" +
                "по логике \"И\"");
    }

    @Step("Проверка области подсказки для фильтра ИЛИ при смене с фильтра И")
    public boolean isCorrectHintAreaOr(){
        return hintArea.getText().contains("Перенесите в область фильтры\n" +
                "которые должны работать\n" +
                "по логике \"ИЛИ\"");
    }
}
