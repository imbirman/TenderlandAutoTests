package TestMyTendersFilters;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MyTendersFiltersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");

    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка раскрытия списка фильтров */
    protected SelenideElement buttonOpenListFilters = $x("//i[@id='favourites-filter-list']");
    /** Ответственный за тендер в карточке тендера */
    protected SelenideElement buttonChangeResponsibleInCardTender = $x("//div[@id='favourite-tender-select-responsible']//div[@class='dx-texteditor-container']//input");
    /** Очистить поле "Наличие задач" */
    protected SelenideElement buttonClearFieldAvailabilityTask = $x("//div[@id='favourite-search-select-with-tasks']//i[@class='dx-icon material-icons-round icon-16px icon-grey md-close']");


    /** Фильтр выбора пользователя в фильтре "По ответственному" */
    protected SelenideElement filterResponsible = $x("//div[@id='favourite-search-select-responsibles']//input");
    /** Первый пункт в списке фильтра */
    protected SelenideElement firstElementInListFilter = $x("(//div[@class='dx-overlay-content dx-popup-normal dx-resizable']//div[@class='dx-item-content dx-list-item-content'])[1]");
    /** Фильтр "Поиск по меткам" */
    protected SelenideElement filterTags = $x("//div[@id='favourite-search-select-tags']//div[@class='dx-texteditor-container']//input");
    /** Фильтр "Наличие задач" */
    protected SelenideElement filterAvailabilityTask = $x("(//div[@id='favourite-search-select-with-tasks']//input)[2]");
    /** Фильтр "Этапы" */
    protected SelenideElement filterStages = $x("//div[@id='favourite-search-select-user-stages']//input");
    /** Открыть карточку первого тендера */
    protected SelenideElement openCardFirstTender = $x("//div[@class='dx-treelist-text-content']/div[@class='favourite-kanban-card']");
    /** Выбрать красную метку */
    protected SelenideElement selectRedTagInList = $x("(//div[@class='dx-item dx-list-item'])[2]");


    /** Поле для ввода поиска по тендеру */
    private final SelenideElement searchTenderField = $x("//div[@id='favourite-search-name']//input");

    /** Список номеров тендеров в списке тендеров в первом столбце */
    private final ElementsCollection registerNumberTenderInListTendersForFirstColumnCollections = $$x("(//div[@class='favourite-kanban-list'])[1]//div[@class='favourite-kanban-card-regnumber']/a");
    /** Список фильтров */
    private final ElementsCollection filterForCheckNumberFiltersCollections = $$x("//div[@class='favourite-filter']");
    /** Список названий тендеров в списке тендеров */
    private final ElementsCollection nameTenderInListTendersCollections = $$x("//div[@class='favourite-kanban-card-name']");


    /** Метка в карточке тендера */
    private final SelenideElement tagInCardTender = $x("//div[@id='favourite-card-tag']");



    @Step("Ожидание {number}")
    public MyTendersFiltersPage waitFor(long number){
        sleep(number);
        return new MyTendersFiltersPage();
    }

    @Step("Ввести логин для авторизации")
    public MyTendersFiltersPage typeLogin(String login){loginField.sendKeys(login); return new MyTendersFiltersPage();}

    @Step("Ввести пароль для авторизации")
    public MyTendersFiltersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MyTendersFiltersPage();
    }

    @Step("Ввести данные для поиска по тендеру")
    public MyTendersFiltersPage typeSearchByTender(String search){
        searchTenderField.sendKeys(search);
        return new MyTendersFiltersPage();
    }


    @Step("Нажать кнопку для открытия окна авторизации")
    public MyTendersFiltersPage clickLogInButton(){
        logInButton.click();
        return new MyTendersFiltersPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MyTendersFiltersPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MyTendersFiltersPage();
    }

    @Step("Нажать кнопку {button}")
    public MyTendersFiltersPage clickButton(SelenideElement button){
        button.click();
        return new MyTendersFiltersPage();
    }

    @Step("Получить количество фильтров")
    public Integer getNumberFilters(){
        return filterForCheckNumberFiltersCollections.size();
    }

    @Step("Получение значение ответственного в карточке тендера")
    public String getResponsibleInCardTender(){
        return buttonChangeResponsibleInCardTender.getValue();
    }

    @Step("Проверка результата поиска по реестровому номеру тендера")
    public boolean isCheckSearchByRegisterNumberTender(){
        boolean check = true;
        List<String> checkRegisterNumber = registerNumberTenderInListTendersForFirstColumnCollections.texts();
        for(String type : checkRegisterNumber){
            if(!type.contains("01")){check = false;
                System.out.println(checkRegisterNumber);
                break;}
        }
        return check;
    }

    @Step("Проверка результата поиска по названию тендера")
    public boolean isCheckSearchByNameTender(){
        boolean check = false;
        List<String> checkRegisterNumber = nameTenderInListTendersCollections.texts();
        for(String type : checkRegisterNumber){
            if(type.contains("усл")){check = true; break;}
        }
        return check;
    }

    @Step("Проверка поиска по метке")
    public boolean isCheckSearchByTags(){
        return Objects.requireNonNull(tagInCardTender.getAttribute("style")).contains("background-color: rgb(235, 9, 16)");
    }

    @Step("Проверка на сброс значения фильтра \"Наличие задач\"")
    public boolean isCheckResetFilterAvailabilityTask(){
        return Objects.requireNonNull(filterAvailabilityTask.getValue()).isEmpty();
    }

    @Step("Проверка наличия фильтра \"Этапы\"")
    public boolean isCheckFilterSearchByStages(){
        return filterStages.is(visible);
    }
}
