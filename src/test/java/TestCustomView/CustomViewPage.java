package TestCustomView;

import TestCardView.CardViewPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CustomViewPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Список элементов для выбора в блоке "Поля таблицы" */
    protected ElementsCollection elementUnitTableFieldsForSelection = $$x("//div[@id='search-view-multiview']//div[@class='dx-item dx-multiview-item dx-item-selected']//span");

    /** Кнопка открытия настроек пользовательского вида */
    protected SelenideElement buttonOpenWindowCustomView = $x("//i[@class='material-icons-outline icon-24px icon-grey icon-grey-hover md-settings']");
    /** Кнопка добавления нового пользовательского вида */
    protected SelenideElement buttonAddNewCustomView = $x("//div[@id='search-view-create-button']");

    @Step("Ожидание {number}")
    public CustomViewPage waitFor(long number){
        sleep(number);
        return new CustomViewPage();
    }

    @Step("Ввести логин для авторизации")
    public CustomViewPage typeLogin(String login){loginField.sendKeys(login); return new CustomViewPage();}

    @Step("Ввести пароль для авторизации")
    public CustomViewPage typePassword(String password){
        passwordField.sendKeys(password);
        return new CustomViewPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public CustomViewPage clickLogInButton(){
        logInButton.click();
        return new CustomViewPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public CustomViewPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new CustomViewPage();
    }

    @Step("Нажать кнопку {button}")
    public CustomViewPage clickButton(SelenideElement button){
        button.click();
        return new CustomViewPage();
    }

    @Step("Получение количества элементов для выбора в блоке \"Поля таблицы\"")
    public Integer getNumberElementsTableFieldsForSelection(){
        return elementUnitTableFieldsForSelection.size();
    }

}