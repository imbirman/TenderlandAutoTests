package TestMarkSetting;

import TestGeneralChecks.GeneralChecksPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class MarkSettingPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Кнопка автопоиска "Проверка по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");


    /** Кнопка контекстного меню для строки результата поиска */
    protected SelenideElement contextMenuResultSearch = $x("//table[@class='dx-datagrid-table dx-pointer-events-none dx-datagrid-table-fixed']//a[@class='dx-link dx-icon-overflow dx-link-icon']");
    /** Пункт контекстного меню "Назначить метку" */
    protected SelenideElement markContextMenu = $x("//div[text()='Назначить метку']");
    /** Метка "Красный" в контекстном меню */
    protected SelenideElement redMarkContextMenu = $x("//div[text()='Красный']");


    /** Метка тендера */
    private final SelenideElement markTender = $x("//div[@class='tl-tag-tender']");


    @Step("Ожидание {number}")
    public MarkSettingPage waitFor(long number){
        sleep(number);
        return new MarkSettingPage();
    }

    @Step("Прокрутить до элемента")
    public MarkSettingPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
        return new MarkSettingPage();
    }

    @Step("Ввести логин для авторизации")
    public MarkSettingPage typeLogin(String login){loginField.sendKeys(login); return new MarkSettingPage();}

    @Step("Ввести пароль для авторизации")
    public MarkSettingPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MarkSettingPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public MarkSettingPage clickLogInButton(){
        logInButton.click();
        return new MarkSettingPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MarkSettingPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MarkSettingPage();
    }

    @Step("Нажать кнопку {button}")
    public MarkSettingPage clickButton(SelenideElement button){
        button.click();
        return new MarkSettingPage();
    }

    @Step("Проверка, что метка красная")
    public boolean isMarkOfTender(){
        return Objects.requireNonNull(markTender.getAttribute("style")).contains("background: rgb(235, 9, 16);");
    }
}
