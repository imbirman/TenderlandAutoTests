package TestTabContractFilters;

import TestTabTenderFilters.TabTendersPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import javax.annotation.Nonnull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;

public class TabContractsPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");

    /** Список названий продукта в списке продуктов карточки контракта */
    private final ElementsCollection listProductInCardContractCollection = $$x("//div[@class='dx-datagrid-rowsview dx-datagrid-nowrap dx-scrollable dx-visibility-change-handler dx-scrollable-both dx-scrollable-simulated']//table//tr/following::td[1]"); //
    /** Список цен в результатах поиска*/
    private final ElementsCollection tableCellPriceCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[5]");
    /** Список чекбоксов в фильтре "Статус" */
    private final ElementsCollection checkboxStatusContractsCollection = $$x("//div[@role='checkbox'][@class='dx-widget dx-checkbox dx-list-select-checkbox']");
    /** Список статусов контракта в результате поиска */
    private final ElementsCollection tableCellStatusContractsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[9]");
    /** Список дат контракта */
    private final ElementsCollection tableCellDateCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[7]");
    /** Список штрафов в карточке контракта */
    private final ElementsCollection listMulctInCardContractCollection = $$x("(//div[@id='penalty-blocks-content']//div[@class='dx-scrollable-content']//td)[4]/div"); //


    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по продуктам" */
    protected SelenideElement buttonCheckSearchByProduct = $x("//div[text()='Проверка поиска по продуктам']");
    /** Кнопка автопоиска "Проверка поиска по цене" */
    protected SelenideElement buttonCheckSearchByPrice = $x("//div[text()='Проверка поиска по цене']");
    /** Кнопка автопоиска "Проверка поиска по статусу" */
    protected SelenideElement buttonCheckSearchByStatusContracts = $x("//div[text()='Проверка поиска по статусу']");
    /** Кнопка автопоиска "Проверка поиска по дате публикации" */
    protected SelenideElement buttonCheckPublicationDate = $x("//div[text()='Проверка поиска по дате публикации']");
    /** Кнопка автопоиска "Проверка поиска по дате начала исполнения" */
    protected SelenideElement buttonValidateSearchByContractExecutionStartDate = $x("//div[text()='Проверка поиска по дате начала исполнения']");
    /** Кнопка автопоиска "Проверка поиска по дате окончания исполнения" */
    protected SelenideElement buttonValidateSearchByContractExecutionEndDate = $x("//div[text()='Проверка поиска по дате окончания исполнения']");
    /** Кнопка автопоиска "Проверка поиска по фактической дате исполнения" */
    protected SelenideElement buttonValidateSearchByContractActualExecutionDate = $x("//div[text()='Проверка поиска по фактической дате исполнения']");
    /** Кнопка автопоиска "Проверка поиска по дате подписания" */
    protected SelenideElement buttonValidateSearchByContractDateOfSigning = $x("//div[text()='Проверка поиска по дате подписания']");
    /** Кнопка автопоиска "Проверка поиска по штрафу" */
    protected SelenideElement buttonCheckSearchByMulct = $x("//div[text()='Проверка поиска по штрафу']");


    /** Ячейка таблицы в результатах поиска для первого столбца для первой строки для тестов по штрафам */
    protected SelenideElement tableCellToCheckForSwitchToNextTab = $x("(//div[@class='dx-datagrid-content']//tbody[@role='presentation']/tr)[1]");
    /** Вкладка "Список продуктов" в карточке контракта */
    protected SelenideElement tabListProductsInCardContract = $x("(//div[@id='entity-menu']//div[@class='dx-item dx-list-item'])[2]");
    /** Вкладка "Штрафы" в карточке контракта */
    protected SelenideElement tabMulctContracts = $x("(//div[@id='entity-menu']//div[@class='dx-item dx-list-item'])[4]");

    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");
    /** Кнопка для очистки поля даты "от" */
    protected SelenideElement buttonClearFieldDateFrom = $x("//div[@id='textbox-filter-editor-compact-5-from']//span[@class='dx-icon dx-icon-clear']");

    /** Фильтр "Цена" в автопоиске "Проверка поиска по цене" */
    protected SelenideElement filterValidateSearchByTenderPrice = $x("//div[text()='10000 ₽ — 100000 ₽']");
    /** Поле для ввода цены "от" */
    protected SelenideElement fieldPriceFrom = $x("//div[@id='filter-editor-compact-4-from']//input[@role='spinbutton']");
    /** Поле для ввода цены "до" */
    protected SelenideElement fieldPriceTo = $x("//div[@id='filter-editor-compact-4-to']//input[@role='spinbutton']");
    /** Поле для ввода даты публикации "от" */
    protected SelenideElement fieldPublicationDateFrom = $x("//div[@id='textbox-filter-editor-compact-5-from']//input[@role='textbox']");
    /** Фильтр "Статус" в автопоиске "Проверка поиска по статусу" */
    protected SelenideElement filterSearchContractsStatus = $x("//div[@class='search-filters-tagbox-tag-content']");
    /** Фильтр "Дата публикации" в автопоиске "Проверка поиска по дате публикации" */
    protected SelenideElement filterPublicationDate = $x("//div[text()='09.01.2021 — 09.01.2021']");
    /** Фильтр "Штраф" в автопоиске "Проверка поиска по штрафу" */
    protected SelenideElement filterSearchByMulct = $x("//div[@class='search-filters-tagbox-tag-content']");


    @Step("Переключиться на вкладку под номером {numberTab}")
    public TabContractsPage switchToTab(int numberTab){
        switchTo().window(numberTab);
        return new TabContractsPage();
    }

    @Step("Прокрутить до элемента")
    public TabContractsPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new TabContractsPage();
    }

    @Step("Перетаскиваем фильтр в поле построения")
    public TabContractsPage DragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new TabContractsPage();
    }

    @Step("Ожидание {number}")
    public TabContractsPage waitFor(long number){
        sleep(number);
        return new TabContractsPage();
    }

    @Step("Ожидание элемента")
    public TabContractsPage waitElement(SelenideElement element){
        element.shouldBe(visible);
        return new TabContractsPage();
    }

    @Step("Очистить поле")
    public TabContractsPage clearField(SelenideElement field){field.clear(); return new TabContractsPage();} //

    @Step("Ввести логин для авторизации")
    public TabContractsPage typeLogin(String login){loginField.sendKeys(login); return new TabContractsPage();}

    @Step("Ввести пароль для авторизации")
    public TabContractsPage typePassword(String password){
        passwordField.sendKeys(password);
        return new TabContractsPage();
    }

    @Step("Ввести цену \"от\"")
    public TabContractsPage typePriceFrom(String price){
        fieldPriceFrom.sendKeys(price);
        return new TabContractsPage();
    }

    @Step("Ввести цену \"до\"")
    public TabContractsPage typePriceTo(String price){
        fieldPriceTo.sendKeys(price);
        return new TabContractsPage();
    }

    @Step("Ввести дату публикации \"от\"")
    public TabContractsPage typeDateFrom(String date){
        fieldPublicationDateFrom.sendKeys(date);
        return new TabContractsPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public TabContractsPage clickLogInButton(){
        logInButton.click();
        return new TabContractsPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public void clickConfirmLogInButton(){
        confirmLogInButton.click();
    }

    @Step("Нажать кнопку{button}")
    public TabContractsPage clickButton(SelenideElement button){
        button.click();
        return new TabContractsPage();
    }
    @Step("Получить чекбокс по его порядковому номеру в фильтре \"Мои тендеры\" у тендера или \"Статус\" у контракта")
    public SelenideElement getCheckboxInFilter(int numberCheckbox){
        List<SelenideElement> array;
        array = checkboxStatusContractsCollection;
        return array.get(numberCheckbox);
    }

    @Step("Проверка включает ли карточка контракта искомый продукт")
    public boolean isContainCardContractSearchWord(){
        List<String> array;
        array = listProductInCardContractCollection.texts();
        array.remove(array.size()-1);
        boolean check = false;
        for(String type : array){
            if(type.contains("Мусор") || type.contains("МУСОР") || type.contains("мусор")){
                check = true;
                break;
            }
        }
        return check;
    }

    @Step("Проверка цены")
    public boolean checkPrice(float priceFrom, float priceTo){
        boolean check = true;
        List<String> array;
        array = tableCellPriceCollection.texts();
        array.remove(array.size()-1);
        for(String price : array){
            String priceCheck = price;
            priceCheck = priceCheck.replace(" ₽", "");
            priceCheck = priceCheck.replace(" ", "");
            float floatPriceForCheck = Float.parseFloat(priceCheck);
            if(floatPriceForCheck < priceFrom || floatPriceForCheck > priceTo){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по статусу контракта \"Исполнение\"")
    public boolean isContainBeingExecuted(){
        List<String> array;
        array = tableCellStatusContractsCollection.texts();
        array.remove(array.size()-1);
        boolean check = true;
        for(String type : array){
            if(!type.contains("Исполнение")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по статусу контракта \"Исполнение прекращено\"")
    public boolean isContainExecutionTerminated(){
        List<String> array;
        array = tableCellStatusContractsCollection.texts();
        array.remove(array.size()-1);
        boolean check = true;
        for(String type : array){
            if(!type.contains("Исполнение прекращено")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по статусу контракта \"Исполнение завершено\"")
    public boolean isContainExecutionCompleted(){
        List<String> array;
        array = tableCellStatusContractsCollection.texts();
        array.remove(array.size()-1);
        boolean check = true;
        for(String type : array){
            if(!type.contains("Исполнение завершено")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка, что дата публикации находится в заданном диапазоне")
    public boolean checkDate(String startDate, String endDate) throws ParseException {
        boolean check = true;
        List<String> array;
        array = tableCellDateCollection.texts();
        array.remove(array.size()-1);
        for(String date : array) {
            String dateStr = date;
            dateStr = dateStr.replace("\n" + "(UTC+03:00)", "");
            dateStr = dateStr.replace("\n", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date currentDate = dateFormat.parse(dateStr);
            Date leftDate = dateFormat.parse(startDate);
            Date rightDate = dateFormat.parse(endDate);
            if(!(currentDate.getTime() >= leftDate.getTime() && currentDate.getTime() <= rightDate.getTime())){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка включает ли карточка контракта искомый штраф")
    public boolean isContainCardContractSearchByDelayInPerformanceBySupplier(){
        boolean check = false;
        List<String> array;
        array = listMulctInCardContractCollection.texts();
        for(String type : array){
            if(type.contains("Просрочка исполнения поставщиком")){
                check = true;
                break;
            }
        }
        return check;
    }

    @Step("Проверка включает ли карточка контракта искомый штраф")
    public boolean isContainCardContractSearchByDelayInFulfillmentOfObligationsByCustomer(){
        boolean check = false;
        List<String> array;
        array = listMulctInCardContractCollection.texts();
        for(String type : array){
            if(type.contains("Просрочка исполнения заказчиком обязательств")){
                check = true;
                break;
            }
        }
        return check;
    }

}
