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

    /** Ячейка таблицы в результатах поиска для первого столбца для первой строки */
    protected SelenideElement tableCellToCheck = $x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[4]");
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']"); /** Вкладка "Автопоиски" */
    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Фильтр "Регион" в поле построения дерева фильтров для автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement filterRegionRoot = $x("//div[@id='tl-filter-root']//div[text()='Регион']/following::div[@class='search-filters-filter-content']");
    /** Поле поиска внутри фильтра */
    protected SelenideElement fieldSearchInFilterEditor = $x("//div[(contains(@class,'dx-item dx-multiview-item dx-item-selected'))]//input[@class='dx-texteditor-input']");
    /** Список чекбоксов в результате поиска */
    protected ElementsCollection checkboxCollection = $$x("//tbody[@role='presentation']//div[@role='checkbox']");
    /** Кнопка "Применить" */
    protected SelenideElement buttonApply = $x("//div[@id='filter-apply-button']");
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");

    public void waitFor(long number){
        sleep(number);
    } // Ожидание

    /**
     * Ввести логин для входа
     */
    public void typeLogin(String login){loginField.sendKeys(login);}

    /**
     * Ввести пароль для входа
     */
    public void typePassword(String password){
        passwordField.sendKeys(password);
    }

    /**
     * Кликнуть на кнопку "Войти"
     */
    public void clickLogInButton(){
        logInButton.click();
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
    public void clickButton(SelenideElement button){
        button.click();
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
    public void typeSearch(String search){
        fieldSearchInFilterEditor.sendKeys(search);
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
}
