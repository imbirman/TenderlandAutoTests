package TestDistributionAutoSearch;

import TestCustomView.CustomViewPage;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.interactable;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class DistributionAutoSearchPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Тестовый автопоиск в списке автопоисков */
    protected SelenideElement testAutoSearch = $x("//div[text()='Тестовый автопоиск']");
    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");

    /** Кнопка удаления автопоиска*/
    protected SelenideElement buttonDeleteAutoSearch = $x("(//div[@class='search-autosearch-header-line'])[1]");
    /** Кнопка открытия окна рассылки */
    protected SelenideElement buttonOpenWindowDistribution = $x("//div[@id='search-autosearch-options']");
    /** Кнопка включения рассылки */
    protected SelenideElement buttonEnableDistribution = $x("//div[@id='delivery-view-distribution-active']");
    /** Кнопка сохранения настроек рассылки */
    protected SelenideElement buttonSaveSettingsDistribution = $x("//div[@id='delivery-view-save-button']");


    /** Подпись рассылки */
    private final SelenideElement labelViewDistribution = $x("//span[@id='delivery-view-distribution-active-span']");
    /** Дата и время включения рассылки */
    private final SelenideElement activationDateDistribution = $x("//span[@id='delivery-view-distribution-utc-start']");
    /** Сообщение об ошибке при сохранении рассылки без выбранного аккаунта */
    private final SelenideElement errorMessageSaveEnableDistributionWithoutSelectedAccount = $x("//div[@class='dx-overlay-content dx-invalid-message-content']");


    @Step("Ожидание {number}")
    public DistributionAutoSearchPage waitFor(long number){
        sleep(number);
        return new DistributionAutoSearchPage();
    }

    @Step("Прокрутить до элемента")
    public DistributionAutoSearchPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
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

    @Step("Получить текст включения рассылки")
    public String getLabelDistribution(){
        return labelViewDistribution.getText();
    }

    @Step("Проверка надписи включения рассылки")
    public boolean isLabelEnableDistribution(){

        return labelViewDistribution.getText().contains("Рассылка включена");
    }

    @Step("Проверка соответствия даты включения рассылки и текущей даты")
    public boolean isContainCorrectDateCreateDistribution(){
        Date date = new Date();
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

        return activationDateDistribution.getText().contains(formatterDate.format(date)) && activationDateDistribution.getText().contains(formatterTime.format(date));

    }

    @Step("Проверка отображения сообщения об ошибке при сохранении рассылки без выбранного аккаунта")
    public boolean isVisibleErrorMessageWithoutSelectedAccount(){
        return errorMessageSaveEnableDistributionWithoutSelectedAccount.isDisplayed();
    }
}
