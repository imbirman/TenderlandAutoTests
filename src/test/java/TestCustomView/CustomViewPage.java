package TestCustomView;

import TestAuditor.AuditorPage;
import TestCardView.CardViewPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
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
    /** Сообщение об ошибке поля "Название" пользовательского вида */
    private final SelenideElement errorMessageEmptyFieldNameCustomView = $x("//div[@class='dx-overlay-content dx-invalid-message-content']");

    /** Список элементов для выбора в блоке "Поля таблицы" */
    private final ElementsCollection elementUnitTableFieldsForSelectionCollection = $$x("//div[@id='search-view-multiview']//div[@class='dx-item dx-multiview-item dx-item-selected']//span");
    /** Список выбранных элементов в блоке "Поля таблицы" */
    private final ElementsCollection elementUnitTableFieldsSelectedCollection = $$x("//div[@id='search-view-result-fields-scroll']//span");
    /** Список элементов для выбора в блоке "Детализация" */
    private final ElementsCollection elementUnitDetailingFieldsSelectionCollection = $$x("//div[@id='search-view-details']//div");
    /** Список выбранных элементов в блоке "Детализация" */
    private final ElementsCollection elementUnitDetailingFieldsSelectedCollection = $$x("//div[@id='search-view-result-details']//span");
    /** Список кнопок открытия контекстного меню последнего в списке пользовательского вида */
    private final ElementsCollection buttonOpenContextMenuCustomViewCollection = $$x("//div[@id='search-view-tabs']//i");
    /** Список названий пользовательского вида */
    private final ElementsCollection labelNameCustomViewCollection = $$x("//div[@id='search-view-tabs']//div[@class='dx-tabs-wrapper']//div[@class='common-small-tab-name']");
    /** Список элементов списка столбцов */
    private final ElementsCollection elementOfListColumnsCollection = $$x("//div[@id='search-view-tenders-fields']//span");
    /** Список элементов списка выбранных столбцов */
    private final ElementsCollection elementOfListSelectedColumnsCollection = $$x("//div[@id='search-view-result-fields']//span");


    /** Список вкладок пользовательского вида */
    protected ElementsCollection tabCustomView = $$x("//div[@id='search-view-tabs']//div[@class='dx-tabs-wrapper']/div");

    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Тестирование пользовательского вида" */
    protected SelenideElement buttonAutoSearchTestCustomView = $x("//div[text()='Тестирование пользовательского вида']");


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
    /** Кнопка удаления всех выбранных полей пользовательского вида */
    protected SelenideElement buttonDeleteAllSelectedFields = $x("(//div[@class='search-view-result-header']//i)[2]");
    /** Кнопка "Сохранить настройки" */
    protected SelenideElement buttonSaveCustomView = $x("//div[@id='search-view-save-button']");
    /** Пункт "По убыванию" в настройках пользовательского вида */
    protected SelenideElement radiobuttonDescending = $x("(//div[@id='search-view-sorting-direction']/div/div[contains(@class, 'dx-radiobutton')])[2]");
    /** Чекбокс "Раскрывать детализации" */
    protected SelenideElement checkboxDiscloseDetailing = $x("//div[@id='search-view-autoexpand-details']");
    /** Кнопка контекстного меню "Переименовать" */
    protected SelenideElement buttonContextMenuRename = $x("(//div[@class='common-context-menu-item'])[1]");
    /** Кнопка контекстного меню "Удалить" */
    protected SelenideElement buttonContextMenuDelete = $x("(//div[@class='common-context-menu-item'])[2]");
    /** Поле для ввода названия пользовательского вида */
    protected SelenideElement fieldNameCustomView = $x("//div[@id='search-view-tabs']//input");
    /** Поле поиска столбцов в пользовательском виде */
    protected SelenideElement fieldSearchColumnCustomView = $x("//div[@id='search-view-textbox-fields']//input");
    /**Элемент списка столбцов */
    protected SelenideElement elementOfListColumns = $x("//div[@id='search-view-tenders-fields']//span");


    /** Ошибка при сохранении пользовательского вида без выбранных полей */
    private final SelenideElement labelErrorSaveCustomViewWithoutSelectedFields = $x("//div[@class='search-view-result-error-label']");
    /** Пометка "Выбрано детализаций" */
    private final SelenideElement labelSelectedDetailing = $x("//div[@id='search-view-details-label']");



    /** Поле для сортировки */
    protected SelenideElement fieldForSorting =$x("//div[@id='search-view-sorting-field']//div[@class='dx-texteditor-input-container']//input");


    @Step("Ожидание {number}")
    public CustomViewPage waitFor(long number){
        sleep(number);
        return new CustomViewPage();
    }

    @Step("Прокрутить до элемента")
    public CustomViewPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new CustomViewPage();
    }

    @Step("Ввести логин для авторизации")
    public CustomViewPage typeLogin(String login){loginField.sendKeys(login); return new CustomViewPage();}

    @Step("Ввести пароль для авторизации")
    public CustomViewPage typePassword(String password){
        passwordField.sendKeys(password);
        return new CustomViewPage();
    }

    @Step("Ввести название пользовательского вида")
    public CustomViewPage typeNameCustomView(String name){
        fieldNameCustomView.sendKeys(name);
        fieldNameCustomView.sendKeys(Keys.ENTER);
        return new CustomViewPage();
    }

    @Step("Ввести значение поиска столбца для пользовательского вида")
    public CustomViewPage typeSearchColumnCustomView(String search){
        fieldSearchColumnCustomView.sendKeys(search);
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
        return buttonOpenContextMenuCustomViewCollection.get(number);
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

    @Step("Проверка отображения сообщение об ошибке при сохранении пользовательского вида без выбранных полей")
    public boolean checkVisibleLabelErrorSaveCustomViewWithoutSelectedFields(){
        return labelErrorSaveCustomViewWithoutSelectedFields.isDisplayed();
    }

    @Step("Проверить отображение элемента, в котором хранится название пользовательского вида")
    public boolean checkDisplayedNameTabCustomViewByNumber(int number){
        return labelNameCustomViewCollection.get(number).isDisplayed();
    }

    @Step("Проверка сообщения об ошибке при сохранении пользовательского вида при пустом названии")
    public boolean isErrorMessageEmptyNameFieldCustomView(){
        return errorMessageEmptyFieldNameCustomView.getText().contains("Введите название") && errorMessageEmptyFieldNameCustomView.isDisplayed();
    }


    @Step("Проверка поиска в окне пользовательского вида")
    public boolean isContainResultSearchColumnCustomView() {
        List<String> checkColumn = elementOfListColumnsCollection.texts();
        List<String> checkSelectedColumn = elementOfListSelectedColumnsCollection.texts();
        boolean checkIsContainColumn = false;
        boolean checkISContainSelectedColumn = false;

        for (String type : checkColumn) {
            if (type.contains("Реестровый номер")) {
                checkIsContainColumn = true;
                break;
            }
        }
        for (String type : checkSelectedColumn) {
            if (type.contains("Реестровый номер")) {
                checkISContainSelectedColumn = true;
                break;
            }
        }
        return checkIsContainColumn || checkISContainSelectedColumn;
    }

    @Step("Проверка, заблокирован ли для выбора элемент детализации контрактов, если не выбраны соответствующие поля таблицы")
    public boolean isDisabledElementContractDetailingToSelect(){
        boolean check = false;
        for(SelenideElement type: elementUnitDetailingFieldsSelectionCollection){
            if(Objects.requireNonNull(type.getAttribute("style")).contains("background-color: rgb(255, 230, 197); opacity: 0.3;")){
                check = true;
                break;
            }
        }
        return check;
    }
}
