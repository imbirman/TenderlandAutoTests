package TestDistributionAutoSearch;

import TestCustomView.CustomViewPage;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DistributionAutoSearchPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    private final ElementsCollection fieldsSelectedCollections = $$x("//div[@id='delivery-view-result-fields']//span");

    /** Тестовый автопоиск в списке автопоисков */
    protected SelenideElement testAutoSearch = $x("//div[text()='Тестовый автопоиск']");
    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Вкладка "Параметры" в настройках рассылки */
    protected SelenideElement tabDistributionSettings = $x("(//div[@id='delivery-view-tabs']/div/div)[1]");
    /** Вкладка "Поля" в настройках рассылки */
    protected SelenideElement tabFieldsDistribution = $x("(//div[@id='delivery-view-tabs']/div/div)[2]");
    /** Первый отчет */
    protected SelenideElement firstReport = $x("(//div[@id='delivery-view-list-reports']//span)[1]");


    /** Кнопка открытия окна рассылки */
    protected SelenideElement buttonOpenWindowDistribution = $x("//div[@id='search-autosearch-options']");
    /** Кнопка включения рассылки */
    protected SelenideElement buttonEnableDistribution = $x("//div[@id='delivery-view-distribution-active']");
    /** Кнопка сохранения настроек рассылки */
    protected SelenideElement buttonSaveSettingsDistribution = $x("//div[@id='delivery-view-save-button']");
    /** Кнопка удаления интервала */
    protected SelenideElement buttonDeleteInterval = $x("//div[@id='delivery-view-delete-interval-button']");
    /** Кнопка добавления интервала */
    protected SelenideElement buttonAddInterval = $x("//div[@id='delivery-view-add-interval-button']");


    /** Тип рассылки "Письмо-карточки" */
    protected SelenideElement radiobuttonTypeDistributionCards = $x("(//div[@id='delivery-view-report-type']//div[@role='radio'])[1]");
    /** Тип рассылки "Письмо-таблица" */
    protected SelenideElement radiobuttonTypeDistributionTable = $x("(//div[@id='delivery-view-report-type']//div[@role='radio'])[2]");
    /** Тип рассылки "Отчет" */
    protected SelenideElement radiobuttonTypeDistributionReport = $x("(//div[@id='delivery-view-report-type']//div[@role='radio'])[3]");


    /** Подпись рассылки */
    private final SelenideElement labelViewDistribution = $x("//span[@id='delivery-view-distribution-active-span']");
    /** Дата и время включения рассылки */
    private final SelenideElement activationDateDistribution = $x("//span[@id='delivery-view-distribution-utc-start']");
    /** Сообщение об ошибке при сохранении рассылки без выбранного аккаунта */
    private final SelenideElement errorMessageSaveEnableDistributionWithoutSelectedAccount = $x("//div[@class='dx-overlay-content dx-invalid-message-content']");
    /** Подпись о времени и периоде рассылки */
    private final SelenideElement labelSettingsViewDistribution = $x("//div[@class='search-autosearch-view-settings-text']");
    /** Название вкладки для выбора настроек рассылки */
    private final SelenideElement titleTabTypeDistribution = $x("//div[@id='delivery-view-title-name']");
    /** Подпись вкладки для выбора настроек рассылки */
    private final SelenideElement labelTabTypeDistribution = $x("//div[@id='delivery-view-fields-label']");


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

    @Step("Получить текст ошибки при сохранении настроек рассылки без выбранного аккаунта")
    public String getErrorMessageWithoutSelectedAccount(){
        return errorMessageSaveEnableDistributionWithoutSelectedAccount.getText();
    }

    @Step("Получить текст подписи о времени и периоде рассылки")
    public String getTextLabelSettingsViewDistribution(){
        return labelSettingsViewDistribution.getText();
    }

    @Step("Получить название вкладки для выбора настроек типа рассылки")
    public String getTitleTabTypeDistribution(){
        return titleTabTypeDistribution.getText();
    }

    @Step("Получить подпись вкладки для выбора настроек типа рассылки")
    public String getLabelTabTypeDistribution(){
        return labelTabTypeDistribution.getText();
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

    @Step("Проверка кликабельности вкладки 'Параметры' в настройках рассылки")
    public boolean isClickableTabSettingsDistribution(){
        return tabDistributionSettings.is(interactable);
    }

    @Step("Проверка кликабельности вкладки 'Поля' в настройках рассылки")
    public boolean isClickableTabFieldsDistribution(){
        return tabFieldsDistribution.is(interactable);
    }

    @Step("Проверка списка выбранных полей")
    public boolean isCorrectListSelectedFields(){
        List<String> listSelectedFields = fieldsSelectedCollections.texts();
        List<String> listFields = new ArrayList<>();
        listFields.add("Реестровый номер");
        listFields.add("Название");
        listFields.add("Начальная цена");
        listFields.add("Наименование заказчика");
        listFields.add("Дата публикации");
        listFields.add("Дата окончания подачи заявок");
        listFields.add("Регион");
        listFields.add("Тип тендера");
        listFields.add("Категория лота");
        listFields.add("Документы к тендеру");
        listFields.add("Модуль");
        listFields.add("Ссылка на площадку");

        return listSelectedFields.equals(listFields);
    }

    @Step("Проверка типа рассылки по умолчанию")
    public boolean isDefaultTypeDistribution(){
        return Objects.requireNonNull(radiobuttonTypeDistributionCards.getAttribute("class")).contains("dx-radiobutton-checked") &&
                !Objects.requireNonNull(radiobuttonTypeDistributionTable.getAttribute("class")).contains("dx-radiobutton-checked") &&
                !Objects.requireNonNull(radiobuttonTypeDistributionReport.getAttribute("class")).contains("dx-radiobutton-checked");
    }

    @Step("Проверка кликабельности")
    public boolean isInteractableButtonDeleteInterval(){
        return Objects.equals(buttonDeleteInterval.getAttribute("aria-disabled"), "true");
    }

    @Step("Проверка отображения подписи периода и времени")
    public boolean isDisplayedSettingsViewDistribution(){
        return labelSettingsViewDistribution.isDisplayed();
    }

}
