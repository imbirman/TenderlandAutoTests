package TestFilters;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FiltersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    /** Список результатов поиска внутри фильтра */
    private final ElementsCollection resultSearchInFilterCollections = $$x("//span[@class='dx-treelist-search-text']");
    /** Список результатов работы чекбокса "Показывать только выбранное" */
    private final ElementsCollection resultShowOnlySelectedCollections = $$x("//div[@class='dx-treelist-text-content']");
    /** Список ячеек таблицы в результатах поиска для столбца "Цена" */
    private final ElementsCollection cellTablePriceCollections = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[6]");
    /** Список полей таблицы "Наименование организации" в фильтре "Заказчик" во вкладке "Поиск по тексту" */
    private final ElementsCollection cellTableInsideFilterCustomerNameOrganizationInTabSearchByTextCollections = $$x("(//div[@class='search-filters-editor-div']//td[2]/span)[1]");
    /** Список полей таблицы "Наименование организации" в фильтре "Заказчик" во вкладке "Справочник" */
    private final ElementsCollection cellTableInsideFilterCustomerNameOrganizationInTabDirectoryCollections = $$x("(//div[@class='search-filters-editor-div']//td[4]/span)[1]");
    /** Список ячеек таблицы в результатах поиска для столбца "Полное название" внутри фильтра "Заказчик" во вкладке "Поиск по тексту" */
    private final ElementsCollection cellTableInsideFilterCustomerFullTitleTextSearchCollections = $$x("//div[@id='filter-editor-5search-block']//tbody[@role='presentation']//tr[@class='dx-row dx-data-row dx-row-lines']/td[3]");
    /** Список ячеек таблицы в результатах поиска для столбца "Адрес регистрации" внутри фильтра "Заказчик" во вкладке "Поиск по тексту"  */
    private final ElementsCollection cellTableInsideFilterCustomerRegistrationAddressCollections = $$x("//div[@class='dx-datagrid-content']//tr[@class='dx-row dx-data-row dx-row-lines']/td[5]");
    /** Список элементов комбобокса во вкладке "Диапазон" фильтра "Дата публикации" */
    private final ElementsCollection elementOfComboboxCollections = $$x("(//div[@aria-label='Dropdown'])[2]//div[@class='dx-item-content dx-list-item-content']");
    /** Список элементов списка фильтра "Модуль" */
    private final ElementsCollection elementOfFilterModuleCollections = $$x("//div[@class='dx-item-content dx-list-item-content']");
    /** Список выбранных меток */
    private final ElementsCollection elementOfSelectMarkCollections = $$x("//div[contains(@class, 'dx-item dx-list-item dx-list-item-selected')]/div[@class='dx-item-content dx-list-item-content']/div/div[2]");
    /** Список чекбоксов элемента внутри фильтра */
    protected ElementsCollection checkboxElementInsideFilterCollections = $$x("//div[@id='search-filter-editor-multiview']//span[@class='dx-checkbox-icon']");


    /** Кнопка "Сбросить" */
    protected SelenideElement buttonReset = $x("//div[@id='filter-cancel-button']");
    /** Кнопка "Поиск" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");
    /** Кнопка "Применить" */
    protected SelenideElement buttonApply = $x("//div[@id='filter-apply-button']");


    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@id='search-filter-root']//div[@class='dx-sortable search-filter-content search-filter-drop-area']");
    /** Результат поиска внутри фильтра */
    private final SelenideElement resultSearchInFilter = $x("//span[@class='tl-highlighter']");
    /** Поле чекбокса "Показывать без категории" */
    private final SelenideElement fieldWithoutCategory = $x("//div[@id='filter-editor-2-undefined_category']");
    /** Поле для ввода цены "от" */
    private final SelenideElement fieldPriceFrom = $x("//div[@id='filter-editor-compact-3-from']//input[@role='spinbutton']");
    /** Поле для ввода цены "до" */
    private final SelenideElement fieldPriceTo = $x("//div[@id='filter-editor-compact-3-to']//input[@role='spinbutton']");
    /** Текст надписи фильтра цена */
    private final SelenideElement filterPriceText = $x("//div[@class='search-filter-description']");
    /** Поле поиска по реквизитам во вкладке "Справочник" внутри фильтра "Заказчик" */
    private final SelenideElement fieldSearchByDetailsInFilterCustomer = $x("(//tr[@class='dx-row dx-datagrid-filter-row']//input[@type='text'])[1]");
    /** Поле поиска внутри фильтра "Заказчик" */
    private final SelenideElement fieldSearchByCustomerTextSearch = $x("//div[@id='include_block']//textarea");
    /** Поле поиска по адресу регистрации во вкладке "Справочник" внутри фильтра "Заказчик" */
    private final SelenideElement fieldSearchByRegistrationAddressInFilterCustomer = $x("(//tr[@class='dx-row dx-datagrid-filter-row']//input[@type='text'])[3]");
    /** Поле поиска по наименованию организации во вкладке "Выбор из справочника" внутри фильтра "Заказчик" */
    private final SelenideElement fieldSearchByNameOrganizationInFilterCustomer = $x("(//tr[@class='dx-row dx-datagrid-filter-row']//input[@type='text'])[2]");
    /** Подпись чекбокса "Показывать без региона" в фильтре "Регион" */
    private final SelenideElement nameCheckboxShowWithoutRegion = $x("(//div[@id='search-filter-editor-multiview']//span[@class='dx-checkbox-text'])[1]");


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
    /** Чекбокс "Показывать без региона" в фильтре "Регион" */
    private final SelenideElement checkboxShowWithoutRegion = $x("(//div[@id='search-filter-editor-multiview']//span[@class='dx-checkbox-icon'])[1]");
    /** Вкладка "Поиск по тексту" в фильтре "Заказчик" */
    protected SelenideElement tabTextSearchInFilterCustomer = $x("//div[@class='search-filters-editor-div']//span[text()='Поиск по тексту']");
    /** Вкладка "Диапазон" в фильтре "Дата публикации" */
    protected SelenideElement tabRangeInFilterDatePublication = $x("(//div[@class='dx-item dx-tab dx-tabpanel-tab']//span)[1]");
    /** Чекбокс в окне фильтра "Выбрать всё" */
    protected SelenideElement checkboxSelectAll = $x("//div[@id='filter-editor-5']//div[@class='dx-checkbox-container tl-filter-check-all']//span");
    /** Чекбокс "Выбрать всё" */
    protected SelenideElement checkboxSelectedAllElements = $x("(//div[@class='search-filters-editor-div']//span[@class='dx-checkbox-icon'])[1]");
    /** Вторая страница списка в окне фильтра */
    protected SelenideElement secondPage = $x("(//div[@class='dx-page'])[1]");
    /** Текст фильтра в дереве фильтров */
    protected SelenideElement filterInTree = $x("//div[@class='search-filter-description']");
    /** Наименование фильтра в области построения */
    protected SelenideElement nameFilter = $x("//div[@class='search-filter-header-info-container']");


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

    @Step("Видимость элемента")
    public FiltersPage shouldBeVisible(SelenideElement element){
        element.shouldBe(visible);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска по реквизитам фильтра \"Заказчик\"")
    public FiltersPage typeSearchInsideFilterCustomerByDetails(String search){
        fieldSearchByDetailsInFilterCustomer.sendKeys(search);
        fieldSearchByDetailsInFilterCustomer.sendKeys(Keys.ENTER);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска по тексту фильтра \"Заказчик\"")
    public FiltersPage typeSearchInsideFilterCustomerTextSearch(String search){
        fieldSearchByCustomerTextSearch.sendKeys(search);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска по адресу организации фильтра \"Заказчик\"")
    public FiltersPage typeSearchInsideFilterCustomerByRegistrationAddress(String search){
        fieldSearchByRegistrationAddressInFilterCustomer.sendKeys(search);
        return new FiltersPage();
    }

    @Step("Ввести значение в поле поиска по наименованию организации фильтра \"Заказчик\"")
    public FiltersPage typeSearchInsideFilterCustomerByNameOrganization(String search){
        fieldSearchByNameOrganizationInFilterCustomer.sendKeys(search);
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
        for(SelenideElement type : cellTableInsideFilterCustomerNameOrganizationInTabSearchByTextCollections){
            if(!(type.getText().contains("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ МАЛОВА НАТАЛЬЯ БОРИСОВНА"))){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по тексту внутри фильтра \"Заказчик\"")
    public boolean isCheckSearchByTextInsideFilterCustomer(){
        boolean check = true;
        for(SelenideElement type : cellTableInsideFilterCustomerFullTitleTextSearchCollections){
            if(!(type.getText().contains("ЗАКУПАЙ"))){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по адресу регистрации внутри фильтра \"Заказчик\"")
    public boolean isCheckSearchInsideFilterCustomerByRegistrationAddress(){
        boolean check = true;
        for(SelenideElement type : cellTableInsideFilterCustomerRegistrationAddressCollections){
            if(!type.getText().contains("ОРЕНБУРГ") && type.isDisplayed()){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка выделения чекбокса \"Выбрать всё\" в фильтре \"Заказчик\"")
    public boolean isNotSelectedButtonAllSelect(){
        return Objects.requireNonNull(checkboxSelectAll.getAttribute("style")).contains("background-color: rgb(69, 161, 92)");
    }

    @Step("Проверка поиска по организации внутри фильтра \"Заказчик\"")
    public boolean isContainKeyWordByRegionSearchInsideFilterCustomer() {
        boolean check = false;
        for (SelenideElement type : cellTableInsideFilterCustomerNameOrganizationInTabDirectoryCollections) {
            if ((type.getText().contains("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ КРАСНОГИР МАРИНА ВАСИЛЬЕВНА"))) {
                check = true;
            }
        }

        return check;
    }

    @Step("Проверка на соответствие списка модулей в фильтре \"Модуль\"")
    public boolean isContainTypesModule(){
        List<String> keyModule = elementOfFilterModuleCollections.texts();
        keyModule.remove(0);
        keyModule.remove(0);
        List<String> checkArray = new ArrayList<>();
        checkArray.add("Государственные тендеры");
        checkArray.add("Коммерческие тендеры");
        checkArray.add("СНГ");
        checkArray.add("Реализация имущества");
        return keyModule.equals(checkArray);
    }

    @Step("Проверка сброса чекбоксов \"Исключено из поиска\"")
    public boolean isCheckResetUnSelectedCheckboxElements(){
        boolean check = true;
        for(SelenideElement type:checkboxElementInsideFilterCollections){
            if(type.isSelected()){check = false; break;}
        }
        return check;
    }

    @Step("Проверка выделения чекбоксов при просмотре только выбранных элементов")
    public boolean isCheckSelectedCheckboxShowOnlySelectedElements(){
        boolean check = true;
        for(SelenideElement type:checkboxElementInsideFilterCollections){
            if(Objects.equals(type.getAttribute("style"), "background-color: rgb(235, 9, 16);") || Objects.equals(type.getAttribute("style"), "background-color: white;"))
            {check = false; break;}
        }
        return check;
    }

    @Step("Проверка отображения названия чекбокса \"Показывать без региона\"")
    public boolean isCheckVisibleNameCheckboxShowWithoutRegion(){
        return nameCheckboxShowWithoutRegion.isDisplayed();
    }

    @Step("Проверка отображения чекбокса \"Показывать без региона\"")
    public boolean isCheckVisibleCheckboxShowWithoutRegion(){
        return checkboxShowWithoutRegion.isDisplayed();
    }
}
