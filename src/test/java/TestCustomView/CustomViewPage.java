package TestCustomView;

import TestCardView.CardViewPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.*;

public class CustomViewPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Пометка "Выбрано полей" */
    private final SelenideElement labelSelectedFields = $x("//div[@id='search-view-fields-label']");

    /** Список элементов для выбора в блоке "Поля таблицы" */
    private final ElementsCollection elementUnitTableFieldsForSelectionCollection = $$x("//div[@id='search-view-multiview']//div[@class='dx-item dx-multiview-item dx-item-selected']//span");
    /** Список выбранных элементов в блоке "Поля таблицы" */
    private final ElementsCollection elementUnitTableFieldsSelectedCollection = $$x("//div[@id='search-view-result-fields-scroll']//span");
    /** Список элементов для выбора в блоке "Детализация" */
    private final ElementsCollection elementUnitDetailingFieldsSelectionCollection = $$x("//div[@id='search-view-details']//div");
    /** Список выбранных элементов в блоке "Детализация" */
    private final ElementsCollection elementUnitDetailingFieldsSelectedCollection = $$x("//div[@id='search-view-result-details']//span");
    /** Список кнопок открытия контекстного меню последнего в списке пользовательского вида */
    private final ElementsCollection buttonOpenContextMenuCustomView = $$x("//div[@id='search-view-tabs']//i");


    /** Список вкладок пользовательского вида */
    protected ElementsCollection tabCustomView = $$x("//div[@id='search-view-tabs']//div[@class='dx-tabs-wrapper']/div");


    /** Кнопка открытия настроек пользовательского вида */
    protected SelenideElement buttonOpenWindowCustomView = $x("//i[@class='material-icons-outline icon-24px icon-grey icon-grey-hover md-settings']");
    /** Кнопка добавления нового пользовательского вида */
    protected SelenideElement buttonAddNewCustomView = $x("//div[@id='search-view-create-button']");
    /** Вкладка "Контракты" */
    protected SelenideElement buttonTabContracts = $x("(//div[@id='search-view-tabs-fields']/div/div)[2]");
    /** Вкладка "Планы" */
    protected SelenideElement buttonTabPlans = $x("(//div[@id='search-view-tabs-fields']/div/div)[3]");
    /** Пункт "Детализация" */
    protected SelenideElement buttonAccordionDetailing = $x("(//div[contains(@class,'dx-item dx-accordion-item')])[2]");
    /** Пункт "Настройки" */
    protected SelenideElement buttonAccordionSettings = $x("(//div[contains(@class,'dx-item dx-accordion-item')])[3]");
    /** Кнопка закрытия окна пользовательского вида */
    protected SelenideElement buttonCloseWindowCustomView = $x("//div[@role='toolbar']//i");
    /** Пункт "По убыванию" в настройках пользовательского вида */
    protected SelenideElement radiobuttonDescending = $x("(//div[@id='search-view-sorting-direction']/div/div[contains(@class, 'dx-radiobutton')])[2]");
    /** Чекбокс "Раскрывать детализации" */
    protected SelenideElement checkboxDiscloseDetailing = $x("//div[@id='search-view-autoexpand-details']");
    /** Кнопка контекстного меню "Переименовать" */
    protected SelenideElement buttonContextMenuRename = $x("(//div[@class='common-context-menu-item'])[1]");
    /** Кнопка контекстного меню "Удалить" */
    protected SelenideElement buttonContextMenuDelete = $x("(//div[@class='common-context-menu-item'])[2]");

    /** Пометка "Выбрано детализаций" */
    private final SelenideElement labelSelectedDetailing = $x("//div[@id='search-view-details-label']");



    /** Поле для сортировки */
    protected SelenideElement fieldForSorting =$x("//div[@id='search-view-sorting-field']//div[@class='dx-texteditor-input-container']//input");


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
        return elementUnitTableFieldsForSelectionCollection.size();
    }

    @Step("Получение количества выбранных элементов в блоке \"Поля таблицы\"")
    public Integer getNumberElementsTableFieldsSelected(){
        return elementUnitTableFieldsSelectedCollection.size();
    }

    @Step("Получение количества элементов для выбора в блоке \"Детализация\"")
    public Integer getNumberElementsDetailingFieldsForSelection(){
        return elementUnitDetailingFieldsSelectionCollection.size();
    }

    @Step("Получение количества выбранных элементов в блоке \"Детализация\"")
    public Integer getNumberElementsDetailingFieldsSelected(){
        return elementUnitDetailingFieldsSelectedCollection.size();
    }

    @Step("Получение значения поля для сортировки")
    public String getValueDefaultSortField(){
        return fieldForSorting.getValue();
    }

    @Step("Получение значения пометки \"Выбрано полей\"")
    public String getValueLabelSelectedFields(){
        return labelSelectedFields.getText();
    }

    @Step("Получение значения пометки \"Выбрано детализаций\"")
    public String getValueLabelSelectedDetailing(){
        return labelSelectedDetailing.getText();
    }

    @Step("Получить количество вкладок пользовательских видов")
    public Integer getNumberTabCustomView(){
        return tabCustomView.size();
    }

    @Step("Получить вкладку пользовательского вида по его порядковому номеру")
    public SelenideElement getTabCustomViewByOriginalNumber(int number){
        return tabCustomView.get(number);
    }

    @Step("Получить кнопку контекстного меню пользовательского вида по его порядковому номеру")
    public SelenideElement getButtonContextMenuCustomViewByOriginalNumber(int number){
        return buttonOpenContextMenuCustomView.get(number);
    }

    @Step("Проверка, что значение сортировки \"По убыванию\" выставлено по умолчанию")
    public boolean checkSelectedRadiobuttonDescending(){
        return Objects.requireNonNull(radiobuttonDescending.getAttribute("aria-checked")).contains("true");
    }

    @Step("Проверка, что чекбокс \"Раскрывать детализации\" не выставлен по умолчанию")
    public boolean checkCheckboxDiscloseDetailing(){
        return Objects.requireNonNull(checkboxDiscloseDetailing.getAttribute("aria-checked")).contains("false");
    }

    @Step("Проверка кликабельности кнопки контекстного меню пользовательского вида \"Переименовать\"")
    public boolean checkClickableButtonRenameContextMenuCustomView(){
        return buttonContextMenuRename.is(interactable);
    }

    @Step("Проверка кликабельности кнопки контекстного меню пользовательского вида \"Удалить\"")
    public boolean checkClickableButtonDeleteContextMenuCustomView(){
        return buttonContextMenuDelete.is(interactable);
    }

}
