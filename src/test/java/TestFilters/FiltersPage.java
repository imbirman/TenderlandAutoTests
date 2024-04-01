package TestFilters;

import TestAuditor.AuditorPage;
import TestDistributionAutoSearch.DistributionAutoSearchPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import javax.annotation.Nonnull;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class FiltersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    /** Список результатов поиска внутри фильтра */
    private final ElementsCollection resultSearchInFilterCollections = $$x("//span[@class='dx-treelist-search-text']");


    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Результат поиска внутри фильтра */
    private final SelenideElement resultSearchInFilter = $x("//span[@class='dx-treelist-search-text']");

    /** Поле поиска внутри фильтра */
    protected SelenideElement fieldSearchInFilter = $x("//div[(contains(@class,'dx-item dx-multiview-item dx-item-selected'))]//input[@class='dx-texteditor-input']");
    /** Фильтр "ОКПД 2" в блоке фильтров */
    protected SelenideElement filterOKPD = $x("//span[text()='ОКПД 2']");
    /** Чекбокс фильтра ОКПД */
    protected SelenideElement checkboxOKPD = $x("(//div[@class='dx-checkbox-container'])[last()]");


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

    @Step("Очистить поле")
    public FiltersPage clearField(SelenideElement field){field.clear(); return new FiltersPage();}

    @Step("Получить текст найденного значения в фильтре \"ОКПД\"")
    public String getResultSearchByFilter(){
        return resultSearchInFilter.getText();
    }

    @Step("Проверка отображения поискового элемента при пустом поле поиска в фильтре ОКПД")
    public boolean isNotContainKeyWordByOKPDNo(){
        boolean check = true;
        for(SelenideElement type : resultSearchInFilterCollections){
            if(type.getText().contains("(85.11.10.000) Услуги в области дошкольного образования")){
//                System.out.println("Услуги: " + type.getText());
                check = false;
                break;
            }
        }
        return check;
    }
}
