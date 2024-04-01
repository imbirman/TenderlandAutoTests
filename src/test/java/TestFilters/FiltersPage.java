package TestFilters;

import TestAuditor.AuditorPage;
import TestDistributionAutoSearch.DistributionAutoSearchPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class FiltersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    /** Список результатов поиска внутри фильтра */
    private final ElementsCollection resultSearchInFilterCollections = $$x("//span[@class='dx-treelist-search-text']");
    /** Список результатов работы чекбокса "Показывать только выбранное" */
    private final ElementsCollection resultShowOnlySelectedCollections = $$x("//div[@class='dx-treelist-text-content']");
    /** Список ячеек таблицы в результатах поиска для столбца "Цена" */
    private final ElementsCollection cellTablePriceCollections = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[6]");
    /** Список полей таблицы "Наименование организации" в фильтре "Заказчик" */
    private final ElementsCollection cellTableInsideFilterCustomerNameOrganization = $$x("(//div[@class='search-filters-editor-div']//td[2]/span)[1]");


    /** Кнопка "Сбросить" */
    protected SelenideElement buttonReset = $x("//div[@id='filter-cancel-button']");
    /** Кнопка "Поиск" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");


    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Результат поиска внутри фильтра */
    private final SelenideElement resultSearchInFilter = $x("//span[@class='dx-treelist-search-text']");
    /** Поле чекбокса "Показывать без категории" */
    private final SelenideElement fieldWithoutCategory = $x("//div[@class='tl-filter-options']//div[@aria-disabled]");
    /** Поле для ввода цены "от" */
    private final SelenideElement fieldPriceFrom = $x("//div[@id='filter-editor-compact-3-from']//input[@role='spinbutton']");
    /** Поле для ввода цены "до" */
    private final SelenideElement fieldPriceTo = $x("//div[@id='filter-editor-compact-3-to']//input[@role='spinbutton']");
    /** Текст надписи фильтра цена */
    private final SelenideElement filterPriceText = $x("//div[@class='tl-filter-description']");
    /** Поле поиска по реквизитам во вкладке "Справочник" внутри фильтра "Заказчик" */
    private final SelenideElement fieldSearchByDetailsInFilterCustomer = $x("(//tr[@class='dx-row dx-datagrid-filter-row']//input[@type='text'])[1]");



    /** Фильтр "ОКПД 2" в блоке фильтров */
    protected SelenideElement filterOKPD = $x("//span[text()='ОКПД 2']");
    /** Фильтр "Категория" в блоке фильтров */
    protected SelenideElement filterCategory = $x("//span[text()='Категория']");
    /** Фильтр "Цена" в блоке фильтров */
    protected SelenideElement filterPrice = $x("//span[text()='Цена']");
    /** Фильтр "Заказчик" в блоке фильтров */
    protected SelenideElement filterCustomer = $x("//span[text()='Заказчик']");
    /** Фильтр "Дата публикации" в блоке фильтров */
    protected SelenideElement filterDatePublication = $x("//div[@id='list-tenders-filters-group']//span[text()='публикации']");
    /** Фильтр "Модуль" в блоке фильтров */
    protected SelenideElement filterModule = $x("//span[text()='Модуль']");
    /** Фильтр "Метка" в блоке фильтров */
    protected SelenideElement filterMark = $x("//span[text()='Метка']");
    /** Фильтр "Площадка" в блоке фильтров */
    protected SelenideElement filterStand = $x("//span[text()='Площадка']");
    /** Фильтр "Регион" в блоке фильтров */
    protected SelenideElement filterRegion = $x("//span[text()='Регион']");


    /** Поле поиска внутри фильтра */
    protected SelenideElement fieldSearchInFilter = $x("//div[(contains(@class,'dx-item dx-multiview-item dx-item-selected'))]//input[@class='dx-texteditor-input']");
    /** Чекбокс фильтра ОКПД */
    protected SelenideElement checkboxOKPD = $x("(//div[@class='dx-checkbox-container'])[last()]");
    /** Переключатель "Показывать только выбранное" */
    protected SelenideElement checkboxShowOnlySelected = $x("//div[@id='filter-editor-show-selected-rows']");
    /** Чекбокс "Показывать с нулевой ценой" */
    protected SelenideElement checkboxShowWithoutNMCK = $x("//div[@id='filter-editor-compact-3-undefined_values']//div[@class='dx-switch-handle']");


    @Step("Ожидание {number}")
    public FiltersPage waitFor(long number){
        sleep(number);
        return new FiltersPage();
    }

    @Step("Прокрутить до элемента")
    public FiltersPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
        return new FiltersPage();
    }

    @Step("Ввести логин для авторизации")
    public FiltersPage typeLogin(String login){loginField.sendKeys(login); return new FiltersPage();}

    @Step("Ввести пароль для авторизации")
    public FiltersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new FiltersPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public FiltersPage clickLogInButton(){
        logInButton.click();
        return new FiltersPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public FiltersPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new FiltersPage();
    }

    @Step("Нажать кнопку {button}")
    public FiltersPage clickButton(SelenideElement button){
        button.click();
        return new FiltersPage();
    }

    @Step("Перетаскиваем фильтр в поле построения")
    public FiltersPage dragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска")
    public FiltersPage typeSearch(String search){
        fieldSearchInFilter.sendKeys(search);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле цены от")
    public FiltersPage typePriceFrom(String price){
        fieldPriceFrom.sendKeys(price);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле цены до")
    public FiltersPage typePriceTo(String price){
        fieldPriceTo.sendKeys(price);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска по реквизитам фильтра \"Заказчик\"")
    public FiltersPage typeSearchInsideFilterCustomerByDetails(String search){
        fieldSearchByDetailsInFilterCustomer.sendKeys(search);
        fieldSearchByDetailsInFilterCustomer.sendKeys(Keys.ENTER);
        return new FiltersPage();
    }

    @Step("Очистить поле")
    public FiltersPage clearField(SelenideElement field){field.clear(); return new FiltersPage();}

    @Step("Получить текст найденного значения в фильтре \"ОКПД\"")
    public String getResultSearchByFilter(){
        return resultSearchInFilter.getText();
    }

    @Step("Получить текст фильтра \"Цена\"")
    public String getTextFilterPrice(){
        return filterPriceText.getText();
    }

    @Step("Проверка отображения поискового элемента при пустом поле поиска в фильтре ОКПД")
    public boolean isNotContainKeyWordByOKPDNo(){
        boolean check = true;
        for(SelenideElement type : resultSearchInFilterCollections){
            if(type.getText().contains("(85.11.10.000) Услуги в области дошкольного образования")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка отображения поискового элемента при включенном чекбоксе \"Показывать только выбранное\" в фильтре ОКПД")
    public boolean isNotContainKeyWordByOKPDYes(){
        boolean check = false;
        for(SelenideElement type : resultShowOnlySelectedCollections){
            if(type.getText().contains("(85.11.10.000) Услуги в области дошкольного образования")){
                check = true;
                break;
            }
        }
        return check;
    }

    @Step(" Получить текст найденного значения в фильтре \"ОКПД\"")
    public boolean isResetResultSearchByFilterOKPD(){
        return resultSearchInFilter.isSelected();
    }

    @Step("Проверка блокирования чекбокса \"Без категории\" в фильтре \"Категория\"")
    public boolean isDisabledCheckboxEmptyCategory(){
        return Objects.requireNonNull(fieldWithoutCategory.getAttribute("class")).contains("dx-state-disabled");
    }

    @Step("Проверка отображения поискового элемента при включенном чекбоксе \"Показывать с нулевой ценой\"")
    public boolean isContainZeroPrice(){
        boolean check = false;
        for(SelenideElement type : cellTablePriceCollections){
            if(type.getText().contains("0.00 ₽")){
                check = true;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по реквизитам внутри фильтра \"Заказчик\"")
    public boolean isCheckSearchInsideFilterCustomerByDetails(){
        boolean check = true;
        for(SelenideElement type : cellTableInsideFilterCustomerNameOrganization){
            if(!(type.getText().contains("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ МАЛОВА НАТАЛЬЯ БОРИСОВНА"))){
                check = false;
                break;
            }
        }
        return check;
    }
}
