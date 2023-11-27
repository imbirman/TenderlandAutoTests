import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
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
    /** Строка таблицы поиска */
    private final ElementsCollection rowResultSearch = $$x("//div[@class='dx-datagrid-content']//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[@role='row']");
    /** Список вторых ячеек таблицы результата поиска */
    private final ElementsCollection secondTableCellsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[5]");
    /** Список дат публикации тендеров для автопоиска "Проверка поиска по дате публикации" */
    private final ElementsCollection datePublicationTableCellsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[8]");
    protected final SelenideElement mainWindow = $x("//div[@class='tl-content']"); /* Основное окно сайта */
    /** Ячейка таблицы в результатах поиска для первого столбца для первой строки */
    protected SelenideElement tableCellToCheck = $x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[4]");
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']"); /* Вкладка "Автопоиски" */
    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Кнопка автопоиска "Проверка по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Кнопка автопоиска "Проверка поиска по дате публикации" */
    protected SelenideElement buttonCheckPublicationDate = $x("//div[text()='Проверка поиска по дате публикации']");
    /** Кнопка автопоиска "Проверка поиска по дате (только начало)" */
    protected SelenideElement buttonCheckPublicationDateWithOnlyStartDate = $x("//div[text()='Проверка поиска по дате (только начало)']");
    /** Фильтр "Регион" в поле построения дерева фильтров для автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement filterRegionRoot = $x("//div[@id='tl-filter-root']//div[text()='Регион']/following::div[@class='search-filters-filter-content']");
    /** Фильтр "Название тендера" в поле построения дерева фильтров для автопоиска "Проверка поиска по названию тендера и исключению из названия" */
    protected SelenideElement filterNameTender = $x("//div[@id='tl-filter-root']//div[text()='Название тендера']/following::div[@class='search-filters-filter-content']");
    /** Список чекбоксов в результате поиска */
    protected ElementsCollection checkboxCollection = $$x("//tbody[@role='presentation']//div[@role='checkbox']");
    /** Чекбокс "Транслитерация" */
    protected SelenideElement checkBoxTransliteration = $x("//div[@id='filter-editor-compact-1-transliteration']"); //
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





    /** Ожидание */
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
    } //

    /**
     * Очистить поле
     */
    public TabTendersPage clearField(SelenideElement field){field.clear(); return new TabTendersPage();} //

    /**
     * Получение количества строк в таблице результата поиска
     */
    public int getNumberOfRowResultSearch(){
        return rowResultSearch.size();
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
     */
    public boolean checkDate(String startDate, String endDate) throws ParseException {
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
            Date rightDate = dateFormat.parse(endDate);
            if(currentDate.getTime() < leftDate.getTime() || currentDate.getTime() > rightDate.getTime()){
                check = false;
                break;
            }
//            System.out.println(date.getText());
        }
        return check;
    }

    /**
     * Проверка, что дата находится после заданной даты
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
}
