import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Вкладка с фильтрами для поиска тендеров
 */
public class TabTendersPage{

    private final SelenideElement logInButton = $x("//div[@class='landind-header-block']//span[text()='Войти']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /** Кнопка "Войти в систему" */
    /** Строка таблицы поиска */
    private final ElementsCollection rowResultSearch = $$x("//div[@class='dx-datagrid-content']//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[@role='row']");
    /** Список вторых ячеек таблицы результата поиска */
    private final ElementsCollection secondTableCellsCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[5]");
    /** Ячейка таблицы в результатах поиска для первого столбца для первой строки */
    protected SelenideElement tableCellToCheck = $x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[4]");

    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']"); /** Вкладка "Автопоиски" */
    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Кнопка автопоиска "Проверка по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Фильтр "Регион" в поле построения дерева фильтров для автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement filterRegionRoot = $x("//div[@id='tl-filter-root']//div[text()='Регион']/following::div[@class='search-filters-filter-content']");
    /** Поле поиска внутри фильтра */
    protected SelenideElement fieldSearchInFilterEditor = $x("//div[(contains(@class,'dx-item dx-multiview-item dx-item-selected'))]//input[@class='dx-texteditor-input']");
    /** Список чекбоксов в результате поиска */
    protected ElementsCollection checkboxCollection = $$x("//tbody[@role='presentation']//div[@role='checkbox']");
    /** Кнопка "Применить" */
    protected SelenideElement buttonApply = $x("//div[@id='filter-apply-button']");
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");





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
     * Ввести значение в поле поиска внутри фильтра
     */
    public TabTendersPage typeSearch(String search){
        fieldSearchInFilterEditor.sendKeys(search);
        return new TabTendersPage();
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
        secondTableCellsCollection.remove("");
        boolean check = false;
        for(SelenideElement name : secondTableCellsCollection){
            if(name.getText().contains("мусор")||name.getText().contains("МУСОР")||name.getText().contains("муcор")||name.getText().contains("МУCОР")){
//                System.out.println(name.getText());
                check = true;
                break;
            }
        }
        return check;
    }
}
