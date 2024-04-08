package TestGeneralChecks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GeneralChecksPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Список скрытых ячеек таблицы результатов поиска для проверки реестрового номера */
    private final ElementsCollection hiddenCellTableForCheckRegistryNumber = $$x("//div[@id='search-result-wrapper']//table//tr[@class='dx-row dx-data-row dx-row-lines search-result-hidden-row']/td[4]/div");


    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Кнопка автопоиска "Проверка скрытия результатов поиска" */
    protected SelenideElement buttonCheckHideResultSearch = $x("//div[text()='Проверка скрытия результатов поиска']");
    /** Кнопка контекстного меню для строки результата поиска */
    protected SelenideElement buttonContextMenuResultSearch = $x("//table[@class='dx-datagrid-table dx-pointer-events-none dx-datagrid-table-fixed']//a[@class='dx-link dx-icon-overflow dx-link-icon']");
    /** Кнопка открытия окна для отображения скрытых результатов поиска */
    protected SelenideElement buttonOpenShowHideEntities = $x("//div[@id='search-panel-hidden-counter']");
    /** Кнопка смены отображения скрытых результатов поиска */
    protected SelenideElement buttonSwitchShowHideEntities = $x("//div[@id='show-hide-entities-swith']");
    /** Кнопка очистки поля построения фильтров */
    protected SelenideElement buttonClearBuildingFieldSearch = $x("//div[@id='search-filters-clear-button']");
    /** Кнопка удаления автопоиска в области построения фильтров */
    protected SelenideElement buttonDeleteAutoSearch = $x("//i[@id='autosearch-delete-button']");


    /** Область подсказки */
    private final SelenideElement hintArea = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Пометка количества выбранных тендеров */
    private final SelenideElement selectionCounter = $x("//div[@id='search-panel-selection-counter']");


    /** Фильтр логики И/ИЛИ */
    protected SelenideElement filterAndOr = $x("//div[@id='tl-filter-root']//span");
    /** Чекбокс "Выбрать всё" для таблицы результата поиска */
    protected SelenideElement checkBoxSelectedAllForTableResultSearch = $x("//div[@id='search-result-checkbox']/div");
    /** Вторая страница таблицы результата поиска */
    protected SelenideElement secondPageSearch = $x("//div[@class='dx-page']");
    /** Пункт контекстного меню "Скрыть тендер" */
    protected SelenideElement hideContextMenu = $x("//div[text()='Скрыть тендер']");
    /** Пункт контекстного меню "Добавить в Мои тендеры" */
    protected SelenideElement addInMineTendersContextMenu = $x("//div[text()='Добавить в Мои тендеры']");
    /** Пункт контекстного меню "Метка тендера" */
    protected SelenideElement markContextMenu = $x("//div[text()='Назначить метку']");



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

    @Step("Проверка контекстного меню на второй странице после выбора всех элементов на первой странице")
    public boolean isNameElementsContextMenu(){
        return hideContextMenu.is(enabled) && addInMineTendersContextMenu.is(enabled) && markContextMenu.is(enabled);
    }

    @Step("Проверка отображения в результатах поиска скрытого элемента")
    public boolean isContainHideTender(){
        boolean check = false;
        for(String type : hiddenCellTableForCheckRegistryNumber.texts()){
            if(type.contains("32008750757")){check = true; break;}
        }
        return check;
    }

    @Step("Проверка отображения в результатах поиска скрытого элемента")
    public boolean isNotContainHideTender(){
        boolean check = true;
        for(String type : hiddenCellTableForCheckRegistryNumber.texts()){
            if(type.contains("32008750757")){check = false; break;}
        }
        return check;
    }

    @Step("Проверка чекбокса 'Выбрать всё' для результатов поиска после нажатия на кнопку 'Очистить поле'")
    public boolean isNotSelectedCheckBoxSelectedAllForResultSearch(){
        return !checkBoxSelectedAllForTableResultSearch.is(checked);
    }

    @Step("Проверка отображения счетчика выбранных тендеров в поиске")
    public boolean isDisplayedSelectionCounter(){
        return selectionCounter.is(visible);
    }

    @Step("Проверка значения счетчика выбранных тендеров в поиске")
    public boolean isCorrectSelectionCounter(){
        return selectionCounter.getText().equals("12");
    }
}
