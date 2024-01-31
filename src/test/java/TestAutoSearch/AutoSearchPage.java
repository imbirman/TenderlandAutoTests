package TestAutoSearch;

import TestAuditor.AuditorPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import javax.annotation.Nonnull;

import static com.codeborne.selenide.Selenide.*;

public class AutoSearchPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");


    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Поле поиска внутри фильтра "Название тендера" */
    private final SelenideElement fieldSearchByNameTender = $x("//textarea[@class='dx-texteditor-input dx-texteditor-input-auto-resize']");
    /** Текст ошибки при сохранении автопоиска с пустым названием */
    private final SelenideElement errorMessageEmptyNameFieldAutoSearch = $x("//div[@id='tl-autosearch-name']//div[@class='dx-overlay-content dx-invalid-message-content']");
    /** Поле ввода названия автопоиска */
    private final SelenideElement fieldNameAutoSearch = $x("//div[@id='tl-autosearch-name']//input");

    /** Фильтр "Название тендера" в блоке фильтров */
    protected SelenideElement filterNameTender = $x("//span[text()='Название тендера']");
    /** Кнопка "Сохранить автопоиск" */
    protected SelenideElement buttonSaveAutoSearch = $x("//div[@id='search-filters-save-autosearch-button']");
    /** Кнопка "Применить" для сохранения автопоиска */
    protected SelenideElement buttonAcceptSaveAutoSearch = $x("//div[@id='save-autosearch-apply']");



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

    /** Ввести название автопоиска */
    public AutoSearchPage typeNameAutoSearch(String name){
        fieldNameAutoSearch.sendKeys(name);
        return new AutoSearchPage();
    }

    @Step("Перетаскиваем фильтр в поле построения")
    public AutoSearchPage dragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new AutoSearchPage();
    }

    @Step("Нажать кнопку {button}")
    public AutoSearchPage clickButton(SelenideElement button){
        button.click();
        return new AutoSearchPage();
    }

    @Step("Ввести значение в поле название тендера {search}")
    public AutoSearchPage typeSearchInsideFilterNameTender(String search){
        fieldSearchByNameTender.sendKeys(search);
        return new AutoSearchPage();
    }

    @Step("Проверка сообщения об ошибке при отсутствии названия автопоиска")
    public boolean isErrorMessageEmptyNameFieldAutoSearch(){
        return errorMessageEmptyNameFieldAutoSearch.getText().contains("Заполните название автопоиска") && errorMessageEmptyNameFieldAutoSearch.isDisplayed();
    }

    @Step("Проверка сообщения об ошибке при дублировании названия автопоиска")
    public boolean isErrorMessageDuplicateNameFieldAutoSearch(){
        return errorMessageEmptyNameFieldAutoSearch.getText().contains("Название должно быть уникальным.") && errorMessageEmptyNameFieldAutoSearch.isDisplayed();
    }

}
