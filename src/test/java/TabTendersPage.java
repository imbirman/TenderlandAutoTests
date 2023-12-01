import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.annotation.Nonnull;

import static com.codeborne.selenide.DragAndDropOptions.usingActions;
import static com.codeborne.selenide.DragAndDropOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Вкладка с фильтрами для поиска тендеров
 */
public class TabTendersPage{

    private final SelenideElement logInButton = $x("//div[@class='landind-header-block']//span[text()='Войти']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Содержимое документации */
    private final SelenideElement fieldDocumentation = $x("//div[@class='files-view-page-content']");
    /** Ячейка таблицы для открытия документации извещения тендера */
    protected SelenideElement cellTableToOpenDocumentationNotice = $x("(//td//a)[2]");
    /** Поле поиска фильтров в блоке фильтров */
    private final SelenideElement fieldSearchFilters = $x("//div[@id='search-filters-search-textbox']//input");
    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Поле для ввода даты публикации "от" */
    private final SelenideElement fieldPublicationDateFrom = $x("//div[@id='textbox-filter-editor-compact-5-from']//input[@role='textbox']");
    /** Поле для ввода даты публикации "до" */
    private final SelenideElement fieldPublicationDateTo = $x("//div[@id='textbox-filter-editor-compact-5-to']//input[@role='textbox']");
    /** Сообщение об ошибке при вводе даты */
    private final SelenideElement errorMessageInvalidDate = $x("//div[@class='dx-overlay-content dx-invalid-message-content']");


    /** Список чекбоксов в результате поиска */
    private final ElementsCollection checkboxCollection = $$x("//tbody[@role='presentation']//div[@role='checkbox']");
    /** Чекбокс в фильтре */
    private final ElementsCollection checkBoxFilterCollection = $$x("//div[@role='checkbox'][@class='dx-widget dx-checkbox dx-list-select-checkbox']");
    /** Список строк таблицы поиска */
    private final ElementsCollection rowResultSearchCollection = $$x("//div[@class='dx-datagrid-content']//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[@role='row']");
    /** Список вторых ячеек таблицы результата поиска */
    private final ElementsCollection secondTableCellsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[5]");
    /** Список дат публикации тендеров для автопоиска "Проверка поиска по дате публикации" */
    private final ElementsCollection datePublicationTableCellsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[8]");
    /** Список дат начала подачи заявок*/
    private final ElementsCollection startOrEndDateRequestCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[9]");
    /** Список дат проведения тендера */
    private final ElementsCollection dateOfTheTenderCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[8]");
    /** Список ячеек в результатах поиска "Категория лота" */
    private final ElementsCollection tableCellCategoryCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[12]");
    /** Список ячеек в результатах поиска "Начальная цена" */
    private final ElementsCollection tableCellPriceCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[6]");
    /** Список ячеек в результатах поиска "Тип тендера" */
    private final ElementsCollection tableCellTenderTypeCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[11]");
    /** Список ячеек в результатах поиска "Площадка" */
    private final ElementsCollection tableCellAreaCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[16]");
    /** Список ячеек в результатах поиска "Модуль" */
    private final ElementsCollection tableCellModuleCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[14]");
    /** Список ячеек в результатах поиска "Участник" */
    private final ElementsCollection tableCellParticipantCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[16]");
    /** Список ячеек в результатах поиска "Реестровый номер" */
    private final ElementsCollection tableCellToCheckCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[4]");
    /** Список поисковых слов в извещении (выделенные) */
    private final ElementsCollection searchWordIntoNoticeDocumentationCollection = $$x("//em");
    /** Список результатов поиска в блоке фильтров */
    private final ElementsCollection resultSearchFiltersCollection = $$x("//div[@id='list-tenders']//div[not(@style='display: none;')]");
    /** Список ячеек таблицы результатов поиска "Регион" */
    private final ElementsCollection cellTableRegionCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[10]");
    /** Список текстов выбранных чекбоксов внутри фильтра */
    private final ElementsCollection textCheckboxSelected = $$x("//div[@class='dx-widget dx-checkbox dx-state-hover dx-checkbox-checked']//following-sibling::div[@style='margin-left: 25px;']");


    protected SelenideElement mainWindow = $x("//div[@class='tl-content']"); /* Основное окно сайта */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']"); /* Вкладка "Автопоиски" */


    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Кнопка автопоиска "Проверка по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Кнопка автопоиска "Проверка поиска по дате публикации" */
    protected SelenideElement buttonCheckPublicationDate = $x("//div[text()='Проверка поиска по дате публикации']");
    /** Кнопка автопоиска "Проверка поиска по дате (только начало)" */
    protected SelenideElement buttonCheckPublicationDateWithOnlyStartDate = $x("//div[text()='Проверка поиска по дате (только начало)']");
    /** Кнопка автопоиска "Проверка поиска по дате (только конец)" */
    protected SelenideElement buttonCheckPublicationDateWithOnlyEndDate = $x("//div[text()='Проверка поиска по дате (только конец)']");
    /** Кнопка автопоиска "Проверка поиска по дате начала подачи заявок" */
    protected SelenideElement buttonCheckStartSubmissionOfApplicationDate = $x("//div[text()='Проверка поиска по дате начала подачи заявок']");
    /** Кнопка автопоиска "Проверка поиска по дате окончания подачи заявок" */
    protected SelenideElement buttonCheckEndSubmissionOfApplicationDate = $x("//div[text()='Проверка поиска по дате окончания подачи заявок']");
    /** Кнопка автопоиска "Проверка поиска по дате проведения тендера" */
    protected SelenideElement buttonValidateSearchByTenderDate = $x("//div[text()='Проверка поиска по дате проведения тендера']");
    /** Кнопка автопоиска "Проверка поиска по категории" */
    protected SelenideElement buttonValidateSearchByCategory = $x("//div[text()='Проверка поиска по категории']");
    /** Кнопка автопоиска "Проверка поиска по цене" */
    protected SelenideElement buttonCheckSearchByPrice = $x("//div[text()='Проверка поиска по цене']");
    /** Кнопка автопоиска "Проверка поиска по типу тендера" */
    protected SelenideElement buttonCheckSearchByTenderType = $x("//div[text()='Проверка поиска по типу тендера']");
    /** Кнопка автопоиска "Проверка поиска по площадке" */
    protected SelenideElement buttonCheckSearchByTenderStand = $x("//div[text()='Проверка поиска по площадке']");
    /** Кнопка автопоиска "Проверка поиска по модулю" */
    protected SelenideElement buttonCheckSearchByTenderModule = $x("//div[text()='Проверка поиска по модулю']");
    /** Кнопка автопоиска "Проверка поиска по участнику" */
    protected SelenideElement buttonCheckSearchByParticipant = $x("//div[text()='Проверка поиска по участнику']");
    /** Кнопка автопоиска "Проверка поиска по моим тендерам" */
    protected SelenideElement buttonCheckSearchByMineTenders = $x("//div[text()='Проверка поиска по моим тендерам']");
    /** Кнопка автопоиска "Проверка поиска по документации" */
    protected SelenideElement buttonCheckSearchByDocumentation = $x("//div[text()='Проверка поиска по документации']");
    /** Кнопка автопоиска "Проверка поиска по извещению" */
    protected SelenideElement buttonCheckSearchByNotice = $x("//div[text()='Проверка поиска по извещению']");
    /** Фильтр "Дата публикации" в блоке фильтров */
    protected SelenideElement filterDatePublication = $x("//span[text()='публикации']/parent::div");
    /** Фильтр "Дата определения победителя" в блоке фильтров */
    protected SelenideElement filterDateDeterminationWinner = $x("//span[text()='определения победителя']");


    /** Фильтр "Цена" в автопоиске "Проверка поиска по цене" */
    protected SelenideElement filterValidateSearchByTenderPrice = $x("//div[text()='10000 ₽ — 100000 ₽']");
    /** Фильтр "Модуль" в автопоиске "Проверка поиска по модулю" */
    protected SelenideElement filterSearchByTenderModule = $x("//div[text()='Государственные тендеры']");
    /** Фильтр "Мои Тендеры" в автопоиске "Проверка поиска по моим тендерам" */
    protected SelenideElement filterSearchByMineTendersOrContractsStatus = $x("//div[@class='search-filters-tagbox-tag-content']");
    /** Чекбокс в фильтре */
    protected SelenideElement notSelectedCheckBoxFilter = $x("//div[@role='checkbox'][@class='dx-widget dx-checkbox dx-list-select-checkbox']");
    /** Фильтр "Регион" в поле построения дерева фильтров для автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement filterRegionRoot = $x("//div[@id='tl-filter-root']//div[text()='Регион']/following::div[@class='search-filters-filter-content']");
    /** Фильтр "Название тендера" в поле построения дерева фильтров для автопоиска "Проверка поиска по названию тендера и исключению из названия" */
    protected SelenideElement filterNameTender = $x("//div[@id='tl-filter-root']//div[text()='Название тендера']/following::div[@class='search-filters-filter-content']");
    /** Фильтр "Категория" в блоке фильтров */
    protected SelenideElement filterCategory = $x("//span[text()='Категория']");
    /** Ячейка таблицы в результатах поиска для первого столбца для первой строки */
    protected SelenideElement tableCellToCheck = $x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[4]");
    /** Ячейка таблицы для открытия документации тендера */
    protected SelenideElement cellTableToOpenDocumentation = $x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//a");
    /** Кнопка скрытия фильтра */
    protected SelenideElement buttonHideFilter = $x("(//i[@class='material-icons-round icon-20px icon-grey icon-grey-hover md-filter_alt_off'])[2]");



    /** Чекбокс "Транслитерация" */
    protected SelenideElement checkBoxTransliteration = $x("//div[@id='filter-editor-compact-1-transliteration']");
    /** Чекбокс первого пункта фильтра */
    protected SelenideElement checkboxFirstInFilter = $x("(//div[@id='filter-editor-2']//span[@class='dx-checkbox-icon'])[1]");
    /** Кнопка раскрытия подкатегории */
    protected SelenideElement buttonOpenTreeList = $x("(//div[@class='dx-treelist-icon-container'])[1]");
    /** Кнопка "Применить" */
    protected SelenideElement buttonApply = $x("//div[@id='filter-apply-button']");
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");
    /** Поле для ввода параметра, исключаемого из поиска */
    protected SelenideElement fieldNameTenderDeletion = $x("//div[@id='filter-editor-compact-1-exclude']//textarea");
    /** Поле для ввода названия тендера для поиска */
    protected SelenideElement fieldNameTender = $x("//div[@id='filter-editor-compact-1-include']//textarea"); //
    /** Поле поиска внутри фильтра */
    protected SelenideElement fieldSearchInFilterEditor = $x("//div[(contains(@class,'dx-item dx-multiview-item dx-item-selected'))]//input[@class='dx-texteditor-input']");
    /** Поле для ввода цены "от" */
    protected SelenideElement fieldPriceFrom = $x("//div[@id='filter-editor-compact-4-from']//input[@role='spinbutton']");
    /** Поле для ввода цены "до" */
    protected SelenideElement fieldPriceTo = $x("//div[@id='filter-editor-compact-4-to']//input[@role='spinbutton']");
    /** Чекбокс "Выбрать всё" в фильтре "Мои тендеры" */
    protected SelenideElement checkboxSelectAllMyTenders = $x("//div[@role='checkbox'][@class='dx-widget dx-checkbox dx-list-select-all-checkbox']");
    private WebDriver driver;


    /**
     * Переключиться на заданную вкладку
     */
    public TabTendersPage switchToTab(int numberTab){
        switchTo().window(numberTab);
        return new TabTendersPage();
    }
    /**
     * Перетащить фильтр в поле построения
     */
    public TabTendersPage DragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new TabTendersPage();
    }
    /**
     * Ожидание
     */
    public TabTendersPage waitFor(long number){
        sleep(number);
        return new TabTendersPage();
    }

    /**
     * Ввести логин для входа
     */
    public TabTendersPage typeLogin(String login){loginField.sendKeys(login); return new TabTendersPage();}

    /**
     * Ввести пароль для входа
     */
    public TabTendersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new TabTendersPage();
    }

    /**
     * Кликнуть на кнопку "Войти"
     */
    public TabTendersPage clickLogInButton(){
        logInButton.click();
        return new TabTendersPage();
    }

    /**
     * Кликнуть на кнопку "Войти в систему"
     */
    public void clickConfirmLogInButton(){
        confirmLogInButton.click();
    }

    /**
     * Кликнуть по кнопке / выбрать radiobutton или checkbox
     */
    public TabTendersPage clickButton(SelenideElement button){
        button.click();
        return new TabTendersPage();
    }

    /**
     * Скролл до элемента
     */
    public TabTendersPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new TabTendersPage();
    }

    /**
     * Ввести значение в поле поиска внутри фильтра
     */
    public TabTendersPage typeSearch(String search){
        fieldSearchInFilterEditor.sendKeys(search);
        return new TabTendersPage();
    }

    /**
     * Ввести значение, исключаемое из поиска
     */
    public TabTendersPage typeDeletion(String name){
        fieldNameTenderDeletion.sendKeys(name);
        return new TabTendersPage();
    }

    /**
     * Ввести ключевое слово для поиска по названию тендера
     */
    public TabTendersPage typeNameTender(String name){
        fieldNameTender.sendKeys(name);
        return new TabTendersPage();
    }

    /**
     * Ввести цену "от"
     */
    public TabTendersPage typePriceFrom(String price){
        fieldPriceFrom.sendKeys(price);
        return new TabTendersPage();
    }

    /**
     * Ввести цену "до"
     */
    public TabTendersPage typePriceTo(String price){
        fieldPriceTo.sendKeys(price);
        return new TabTendersPage();
    }

    /**
     * Ввести значение в поле поиска
     */
    public TabTendersPage typeSearchFilters(String search){
        fieldSearchFilters.sendKeys(search);
        return new TabTendersPage();
    } //

    /**
     * Очистить поле
     */
    public TabTendersPage clearField(SelenideElement field){field.clear(); return new TabTendersPage();} //

    /**
     * Получение количества строк в таблице результата поиска
     */
    public int getNumberOfRowResultSearch(){
        return rowResultSearchCollection.size();
    }

    /**
     * Получение реестрового номера
     */
    public String getRegistryNumber(){
        return tableCellToCheck.getText();
    }

    /**
     * Получить чекбокс по его порядковому номеру
     */
    public SelenideElement getCheckboxInFilterRegion(int numberCheckbox){
        return checkboxCollection.get(numberCheckbox);
    }

    /**
     * Получить чекбокс по его порядковому номеру в фильтре "Мои тендеры" у тендера или "Статус" у контракта
     */
    public SelenideElement getCheckboxInFilter(int numberCheckbox){
        return checkBoxFilterCollection.get(numberCheckbox);
    }

    /**
     * Проверка, соответствует ли количество строк в таблице результата поиска заданному
     */
    public boolean isEqualNumberOfRowResultSearch(int number){
        return getNumberOfRowResultSearch()==number;
    }

    /**
     * Проверка включения в название тендеров ключевого слова
     */
    public boolean isContainNameTender(){
        List<String> array;
        array = secondTableCellsCollection.texts();
        array.remove(array.size()-1);
        boolean check = false;
        for(String name : array){
            if(name.contains("мусор")||name.contains("МУСОР")||name.contains("муcор")||name.contains("МУCОР")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     *  Проверка исключения из поиска ключевого слова
     */
    public boolean isContainDeletionNameTender(){
        List<String> array;
        array = secondTableCellsCollection.texts();
        array.remove(array.size()-1);
        boolean check = true;
        for(String name : array){
            if(name.contains("мусоровоз")||name.contains("МУСОРОВОЗ")){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка, что дата находится в заданном диапазоне
     * @param caseTypeDate 1 - дата публикации, 2 - дата начала подачи заявок, 3 - дата подачи тендера
     */
    public boolean checkDate(String startDate, String endDate, int caseTypeDate) throws ParseException {
        boolean check = true;
        List<String> array;
        switch (caseTypeDate){
            case 1: array = datePublicationTableCellsCollection.texts();
                break;
            case 2: array = startOrEndDateRequestCollection.texts();
                break;
            case 3: array = dateOfTheTenderCollection.texts();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + caseTypeDate);
        }
        array.remove(array.size()-1);
        for(String date : array) {
            String dateStr = date;
            dateStr = dateStr.replace("\n" + "(UTC+03:00)", "");
            dateStr = dateStr.replace("\n", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date currentDate = dateFormat.parse(dateStr);
            Date leftDate = dateFormat.parse(startDate);
            Date rightDate = dateFormat.parse(endDate);
            if(currentDate.getTime() < leftDate.getTime() || currentDate.getTime() > rightDate.getTime()){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка, что дата публикации находится после заданной даты
     */
    public boolean checkDateWithOnlyStartDate(String startDate) throws ParseException {
        boolean check = true;
        List<String> array;
        array = datePublicationTableCellsCollection.texts();
        array.remove(array.size()-1);
        for(String date : array) {
            String dateStr = date;
            dateStr = dateStr.replace("\n" + "(UTC+03:00)", "");
            dateStr = dateStr.replace("\n", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date currentDate = dateFormat.parse(dateStr);
            Date leftDate = dateFormat.parse(startDate);
            if(currentDate.getTime() < leftDate.getTime()){
                check = false;
                break;
            }
        }

        return check;
    }

    /**
     * Проверка, что дата публикации находится до заданной даты
     */
    public boolean checkDateWithOnlyEndDate(String endDate) throws ParseException {
        boolean check = true;
        List<String> array;
        array = datePublicationTableCellsCollection.texts();
        array.remove(array.size()-1);
        for(String date : array) {
            String dateStr = date;
            dateStr = dateStr.replace("\n" + "(UTC+03:00)", "");
            dateStr = dateStr.replace("\n", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date currentDate = dateFormat.parse(dateStr);
            Date rightDate = dateFormat.parse(endDate);
            if(currentDate.getTime() > rightDate.getTime()){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по категории
     */
    public boolean isContainCategoryName(){
        boolean check = true;
        List<String> array;
        array = tableCellCategoryCollection.texts();
        array.remove(array.size()-1);
        for(String name : array){
            if(!(name.contains("Коммунальные услуги"))){
//                System.out.println("Услуги: " + name.getText());
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка цены
     */
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

    /**
     * Проверка поиска по типу тендера
     */
    public boolean isContainTenderType(){
        boolean check = true;
        List<String> array;
        array = tableCellTenderTypeCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("Закупка малого объема"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по площадке
     */
    public boolean isContainTenderStand(){
        boolean check = true;
        List<String> array;
        array = tableCellAreaCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ «РТС-ТЕНДЕР»"))){
                check = false;
                System.out.println(type);
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по модулю "Государственные тендеры"
     */
    public boolean isContainOnlyGovernmentTenders(){
        boolean check = true;
        List<String> array;
        array = tableCellModuleCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("Государственные тендеры"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по модулю "Государственные тендеры" и "Коммерческие тендеры"
     */
    public boolean isContainOnlyGovernmentAndCommercialTenders(){
        boolean check = true;
        List<String> array;
        array = tableCellModuleCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("Государственные тендеры")) && !(type.contains("Коммерческие тендеры"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по модулю "Государственные тендеры" и "Коммерческие тендеры" и "СНГ"
     */
    public boolean isContainOnlyGovernmentAndCommercialAndCISTenders(){
        boolean check = true;
        List<String> array;
        array = tableCellModuleCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("Государственные тендеры")) && !(type.contains("Коммерческие тендеры")) && !(type.contains("СНГ"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по всем модулям
     */
    public boolean isContainAllModulesTenders(){
        boolean check = true;
        List<String> array;
        array = tableCellModuleCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("Государственные тендеры")) && !(type.contains("Коммерческие тендеры"))
                    && !(type.contains("СНГ")) && !(type.contains("Реализация имущества"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по участнику
     */
    public boolean isContainParticipant(){
        boolean check = true;
        List<String> array;
        array = tableCellParticipantCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"ТРАНСЭКОСЕРВИС\""))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по новым тендерам фильтра "Мои тендеры"
     */
    public boolean isContainNewTenders(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("400022118701")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по подготовке заявки фильтра "Мои тендеры"
     */
    public boolean isContainApplicationPreparation(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("0130300010421000001")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по определению победителя фильтра "Мои тендеры"
     */
    public boolean isContainDeterminationOfWinner(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("8976791")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по заключению контракта фильтра "Мои тендеры"
     */
    public boolean isContainConclusionOfContract(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("0126200000421000268")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по исполнению контракта фильтра "Мои тендеры"
     */
    public boolean isContainExecutionOfContract(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("0306200004521000009")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по архиву фильтра "Мои тендеры"
     */
    public boolean isContainArchiveTenders(){
        boolean check = false;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(type.contains("0848300064121000009")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по всем тендерам из "Мои тендеры"
     */
    public boolean isContainAllMineTenders(){
        boolean check = true;
        List<String> array;
        array = tableCellToCheckCollection.texts();
        array.remove(array.size()-1);
        for(String type : array){
            if(!(type.contains("0130300010421000001")) && !(type.contains("0848300064121000009"))
                    && !(type.contains("0126200000421000268")) && !(type.contains("400022118701"))
                    && !(type.contains("0306200004521000009")) && !(type.contains("8976791"))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска по документации
     */
    public boolean isContainSearchDocumentation(){
        String textDocumentation = fieldDocumentation.getText();
        return textDocumentation.contains("мусор") || textDocumentation.contains("Мусор") || textDocumentation.contains("МУСОР");
    }

    /**
     * Проверка поиска по извещению
     */
    public boolean isContainSearchWordIntoNoticeDocumentation(){
        boolean check = false;
        List<String> array;
        array = searchWordIntoNoticeDocumentationCollection.texts();
        for(String type : array){
            if(type.contains("мусор") || type.contains("Мусор") || type.contains("МУСОР")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска в блоке фильтров
     */
    public boolean isContainFiltersFromSearchField(){
        boolean check = false;
        List<String> array;
        array = resultSearchFiltersCollection.texts();
        for(String type : array){
            if(type.contains("Название тендера")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Проверка поиска со скрытым фильтром
     */
    public boolean isContainWithoutHideFilter(){
        boolean check = false;
        List<String> array;
        array = cellTableRegionCollection.texts();
        for(String type : array){
            if(!type.contains("Санкт-Петербург город")){
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Ввести дату публикации "от"
     */
    public TabTendersPage typeDateFrom(String date){
        fieldPublicationDateFrom.click();
        fieldPublicationDateFrom.sendKeys(date);
        return new TabTendersPage();
    }

    /**
     * Ввести дату публикации "до"
     */
    public TabTendersPage typeDateTo(String date){
        fieldPublicationDateTo.click();
        fieldPublicationDateTo.sendKeys(date);
        return new TabTendersPage();
    }
    /**
     * Проверка наличия ошибки при некорректном вводе даты в фильтр
     */
    public boolean isVisibleErrorInvalidEnterDate(){
        return errorMessageInvalidDate.isDisplayed();
    }

    /**
     * Проверка текста ошибки при некорректном вводе даты в фильтр
     */
    public boolean isTextErrorInvalidEnterDate(){
        return errorMessageInvalidDate.getText().equals("Неверная дата.");
    }

    /**
     * Получить количество выбранных элементов фильтра "Категории"
     */
    public int getNumberSelectedCategories(){
        return textCheckboxSelected.size();
    }

    /**
     * Проверка на соответствие списка выбранных элементов фильтра "Категории"
     */
    public boolean isContainSelectedCategory(){
//        List<String> textCheckbox = findAll(textCheckboxSelected).texts();
        List<String> array;
        array = textCheckboxSelected.texts();
        array.remove(0);
//        textCheckbox.remove(0);
        List<String> checkArray = new ArrayList<>();
        checkArray.add("Банковские услуги");
        checkArray.add("Бухгалтерский учет, аудит");
        checkArray.add("Кадровые услуги");
        checkArray.add("Консультационные услуги");
        checkArray.add("Медицинское страхование");
        checkArray.add("Оценка, экспертиза");
        checkArray.add("Реализация имущества");
        checkArray.add("Страхование");
        checkArray.add("Услуги по лицензированию, сертификации и аттестации");
        checkArray.add("Юридические услуги");

        return checkArray.equals(array);
    }
}
