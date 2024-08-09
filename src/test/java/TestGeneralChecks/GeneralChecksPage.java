package TestGeneralChecks;

import TestAuditor.AuditorPage;
import TestTabContractFilters.TabContractsPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GeneralChecksPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); // Кнопка входа в систему 
    private final SelenideElement loginField = $x("//input[@id='username']"); // Поле для ввода логина
    private final SelenideElement passwordField = $x("//input[@type='password']"); // Поле для ввода пароля 
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); // Кнопка "Войти в систему" 

    // Список скрытых ячеек таблицы результатов поиска для проверки реестрового номера 
    private final ElementsCollection hiddenCellTableForCheckRegistryNumber = $$x("//div[@id='search-result-wrapper']//table//tr[@class='dx-row dx-data-row dx-row-lines search-result-hidden-row']/td[4]/div");
    // Поле дерева фильтров
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    // Список строк таблицы поиска
    private final ElementsCollection rowResultSearchCollection = $$x("//div[@class='dx-datagrid-content']//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[@role='row']");
    // Список строк таблицы поиска
    private final SelenideElement firstRowResultSearch = $x("//div[@class='dx-datagrid-content']//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[@role='row']");
    // Пункт меню "Документация" в карточке тендера
    private final SelenideElement documentationInCardTender = $x("//div[@id='entity-menu']//div[text()='Документация']");
    // Иконка извещения в документации
    private final SelenideElement iconNoticeInDocumentation = $x("//div[@class='entity-files-icon html-icon']");

    // Текущая дата "От"
    protected SelenideElement currentDateFrom = $x("//div[@id='calendar-filter-editor-compact-5-from']//td[@class='dx-calendar-cell dx-calendar-today']");
    //Текущая дата "До"
    protected SelenideElement currentDateTo = $x("//div[@id='calendar-filter-editor-compact-5-to']//td[@class='dx-calendar-cell dx-calendar-today']");

    // Кнопка открытия бокового меню
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");

    // Вкладка "Автопоиски" 
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    // Кнопка автопоиска "Проверка поиска по названию тендера и исключению из названия" 
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    // Кнопка автопоиска "Проверка скрытия результатов поиска" 
    protected SelenideElement buttonCheckHideResultSearch = $x("//div[text()='Проверка скрытия результатов поиска']");
    // Кнопка контекстного меню для строки результата поиска 
    protected SelenideElement buttonContextMenuResultSearch = $x("//table[@class='dx-datagrid-table dx-pointer-events-none dx-datagrid-table-fixed']//a[@class='dx-link dx-icon-overflow dx-link-icon']");
    // Кнопка открытия окна для отображения скрытых результатов поиска 
    protected SelenideElement buttonOpenShowHideEntities = $x("//div[@id='search-panel-hidden-counter']");
    // Кнопка смены отображения скрытых результатов поиска 
    protected SelenideElement buttonSwitchShowHideEntities = $x("//div[@id='show-hide-entities-swith']");
    // Кнопка очистки поля построения фильтров 
    protected SelenideElement buttonClearBuildingFieldSearch = $x("//div[@id='search-filters-clear-button']");
    // Кнопка удаления автопоиска в области построения фильтров 
    protected SelenideElement buttonDeleteAutoSearch = $x("//i[@id='autosearch-delete-button']");
    // Кнопка "Искать"
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");

    // Фильтр "публикации" в списке фильтров
    protected SelenideElement filterDatePublication = $x("//span[text()='публикации']");

    // Область подсказки 
    private final SelenideElement hintArea = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    // Пометка количества выбранных тендеров 
    private final SelenideElement selectionCounter = $x("//div[@id='search-panel-selection-counter']");


    // Фильтр логики И/ИЛИ 
    protected SelenideElement filterAndOr = $x("//div[@id='tl-filter-root']//span");
    // Чекбокс "Выбрать всё" для таблицы результата поиска 
    protected SelenideElement checkBoxSelectedAllForTableResultSearch = $x("//div[@id='search-result-checkbox']/div");
    // Вторая страница таблицы результата поиска 
    protected SelenideElement secondPageSearch = $x("//div[@class='dx-page']");
    // Пункт контекстного меню "Скрыть тендер" 
    protected SelenideElement hideContextMenu = $x("//div[text()='Скрыть тендер']");
    // Пункт контекстного меню "Добавить в Мои тендеры" 
    protected SelenideElement addInMineTendersContextMenu = $x("//div[text()='Добавить в Мои тендеры']");
    // Пункт контекстного меню "Метка тендера" 
    protected SelenideElement markContextMenu = $x("//div[text()='Назначить метку']");



    @Step("Ожидание {number}")
    public GeneralChecksPage waitFor(long number){
        sleep(number);
        return new GeneralChecksPage();
    }

    @Step("Прокрутить до элемента")
    public GeneralChecksPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new GeneralChecksPage();
    }

    @Step("Перетаскиваем фильтр в поле построения")
    public GeneralChecksPage dragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new GeneralChecksPage();
    }

    @Step("Ожидание интерактивности элемента")
    public GeneralChecksPage waitElementInteractable(SelenideElement element){
        element.shouldBe(interactable);
        return new GeneralChecksPage();
    }

    @Step("Закрыть вкладку под номером {numberTab}")
    public GeneralChecksPage closeTab(int numberTab){
        switchTo().window(numberTab).close();
        return new GeneralChecksPage();
    }

    @Step("Переключиться на вкладку под номером {numberTab}")
    public GeneralChecksPage switchToTab(int numberTab){
        switchTo().window(numberTab);
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

    @Step("Проверка корректной загрузки документов в карточке тендера")
    public boolean isCorrectLoadDocumentation(){
        firstRowResultSearch.shouldBe(visible);
        int sizeArray = rowResultSearchCollection.size();
        for(int i=0; i<sizeArray; i++){
            if(i+2<sizeArray){
                scrollToElement(rowResultSearchCollection.get(i+2));}
            if(i==sizeArray-1){continue;}
            clickButton(rowResultSearchCollection.get(i));
            switchToTab(1);
            documentationInCardTender.shouldBe(visible);
            clickButton(documentationInCardTender);
            if(!iconNoticeInDocumentation.is(visible)){return false;}
            closeTab(1);
            switchToTab(0);
        }

        return true;
    }
}
