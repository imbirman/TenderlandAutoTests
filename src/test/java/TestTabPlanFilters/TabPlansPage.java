package TestTabPlanFilters;

import TestTabContractFilters.TabContractsPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TabPlansPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");

    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по названию плана" */
    protected SelenideElement buttonCheckSearchByNamePlan = $x("//div[text()='Проверка поиска по названию плана']");
    /** Кнопка автопоиска "Проверка поиска по типу плана" */
    protected SelenideElement buttonCheckSearchByTypePlan = $x("//div[text()='Проверка поиска по типу плана']");

    /**  Фильтр "Тип" в автопоиске "Проверка поиска по типу плана" */
    protected SelenideElement filterSearchByTypePlan = $x("//div[@class='search-filters-tagbox-tag-content']");

    /** Список названий позиции плана */
    private final ElementsCollection tableCellNamePositionCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[5]");
    /** Список типов плана */
    private final ElementsCollection tableCellTypePlanCollection = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[12]");
    /** Список чекбоксов в фильтре "Тип" */
    private final ElementsCollection checkboxTypePlansCollection = $$x("//div[@role='checkbox'][@class='dx-widget dx-checkbox dx-list-select-checkbox']");

    /** Кнопка удаления автопоиска */
    protected SelenideElement buttonDeleteAutoSearch = $x("//i[@id='search-autosearch-delete']");

    @Step("Ожидание {number}")
    public TabPlansPage waitFor(long number){
        sleep(number);
        return new TabPlansPage();
    }

    @Step("Прокрутить до элемента")
    public TabPlansPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new TabPlansPage();
    }

    @Step("Ввести логин для авторизации")
    public TabPlansPage typeLogin(String login){loginField.sendKeys(login); return new TabPlansPage();}

    @Step("Ввести пароль для авторизации")
    public TabPlansPage typePassword(String password){
        passwordField.sendKeys(password);
        return new TabPlansPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public TabPlansPage clickLogInButton(){
        logInButton.click();
        return new TabPlansPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public void clickConfirmLogInButton(){
        confirmLogInButton.click();
    }

    @Step("Нажать кнопку{button}")
    public TabPlansPage clickButton(SelenideElement button){
        button.click();
        return new TabPlansPage();
    }

    @Step("Получить чекбокс по его порядковому номеру в фильтре")
    public SelenideElement getCheckboxInFilter(int numberCheckbox){
        List<SelenideElement> array;
        array = checkboxTypePlansCollection;
        return array.get(numberCheckbox);
    }

    @Step("Проверка результатов поиска по названию плана")
    public boolean isContainNamePlan(){
        boolean check = true;
        List<String> namePlanForCheck = tableCellNamePositionCollection.texts();
        namePlanForCheck.remove(namePlanForCheck.size()-1);
        for(String namePlan : namePlanForCheck){
            if(!(namePlan.contains("мусор") || namePlan.contains("Мусор") || namePlan.contains("МУСОР"))){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по типу плана")
    public boolean isContainTypePlan(){
        boolean check = true;
        List<String> typePlanForCheck = tableCellTypePlanCollection.texts();
        typePlanForCheck.remove(typePlanForCheck.size()-1);
        for(String typePlan : typePlanForCheck){
            if(!typePlan.contains("План")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по типу плана")
    public boolean isContainTypePlanSchedule(){
        boolean check = true;
        List<String> typePlanForCheck = tableCellTypePlanCollection.texts();
        typePlanForCheck.remove(typePlanForCheck.size()-1);
        for(String typePlan : typePlanForCheck){
            if(!typePlan.contains("План-график")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка поиска по типу плана")
    public boolean isContainTypePlanSchedule2017(){
        boolean check = true;
        List<String> typePlanForCheck = tableCellTypePlanCollection.texts();
        typePlanForCheck.remove(typePlanForCheck.size()-1);
        for(String typePlan : typePlanForCheck){
            if(!typePlan.contains("План-график 2017")){
                check = false;
                break;
            }
        }
        return check;
    }
}
