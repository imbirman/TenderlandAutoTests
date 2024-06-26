package TestMarkSetting;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MarkSettingPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */


    /** Список меток тендера */
    private final ElementsCollection markTenderCollections = $$x("//div[@class='tl-tag-tender']");
    /** Список меток */
    private final ElementsCollection elementListMarkCollections = $$x("//div[@id='tl-user-tag-list']//div[@class='dx-item dx-list-item']//div[not(@*)]");

    /** Список меток тендера в карточном виде */
    private final ElementsCollection markTenderCardViewCollections = $$x("//div[@class='search-result-card-tag-line']");


    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по реестровому номеру и региону" */
    protected SelenideElement buttonAutoSearchRegistryNumberAndRegion = $x("//div[text()='Проверка поиска по реестровому номеру и региону']");
    /** Кнопка автопоиска "Проверка по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Кнопка "Карточный вид" */
    protected SelenideElement buttonCardView = $x("//i[@id='search-panel-card-view']");
    /** Кнопка "Выбрать цвет метки" для карточного вида */
    protected SelenideElement buttonSetMarkCardView = $x("(//div[@class='search-result-card-header']//i)[3]");
    /** Кнопка "Удалить метку" */
    protected SelenideElement buttonDeleteMark = $x("//div[@id='tl-delete-user-tag-button']");
    /** Кнопка выбора всех тендеров на странице */
    protected SelenideElement buttonSelectAllTenders = $x("//div[@id='search-result-checkbox']//span");
    /** Кнопка раскрытия контекстного меню для выбранных тендеров */
    protected SelenideElement buttonContextMenuForSelectedTenders = $x("//i[@id='search-panel-selection-entities']");
    /** Кнопка "Настройки" в контекстном меню */
    protected SelenideElement buttonSettingMark = $x("//div[text()='Настройка']");
    /** Кнопка сохранения метки */
    protected SelenideElement buttonSaveMark = $x("//div[@id='tl-save-user-tag-button']");


    /** Кнопка контекстного меню для строки результата поиска */
    protected SelenideElement contextMenuResultSearch = $x("//table[@class='dx-datagrid-table dx-pointer-events-none dx-datagrid-table-fixed']//a[@class='dx-link dx-icon-overflow dx-link-icon']");
    /** Пункт контекстного меню "Назначить метку" */
    protected SelenideElement markContextMenu = $x("//div[text()='Назначить метку']");
    /** Метка "Красный" в контекстном меню */
    protected SelenideElement redMarkContextMenu = $x("//div[text()='Красный']");


    /** Метка тендера */
    private final SelenideElement markTender = $x("//div[@class='tl-tag-tender']");
    /** Метка тендера в карточном виде */
    private final SelenideElement markTenderCardView = $x("//div[@class='search-result-card-tag-line']");
    /** Текст ошибки при сохранении метки без названия */
    private final SelenideElement errorMessageEmptyFieldNameMark = $x("//div[@id='tl-manage-user-tags-name']//div[@class='dx-overlay-content dx-invalid-message-content']");
    /** Поле для ввода названия метки */
    private final SelenideElement fieldNameMark = $x("//div[@id='tl-manage-user-tags-name']//input");


    @Step("Ожидание {number}")
    public MarkSettingPage waitFor(long number){
        sleep(number);
        return new MarkSettingPage();
    }

    @Step("Прокрутить до элемента")
    public MarkSettingPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
        return new MarkSettingPage();
    }

    @Step("Ввести логин для авторизации")
    public MarkSettingPage typeLogin(String login){loginField.sendKeys(login); return new MarkSettingPage();}

    @Step("Ввести пароль для авторизации")
    public MarkSettingPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MarkSettingPage();
    }

    public MarkSettingPage typeNameMark(String name){
        fieldNameMark.sendKeys(name);
        return new MarkSettingPage();
    } // Ввести название метки

    @Step("Нажать кнопку для открытия окна авторизации")
    public MarkSettingPage clickLogInButton(){
        logInButton.click();
        return new MarkSettingPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MarkSettingPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MarkSettingPage();
    }

    @Step("Нажать кнопку {button}")
    public MarkSettingPage clickButton(SelenideElement button){
        button.click();
        return new MarkSettingPage();
    }

    @Step("Навести курсор на элемент")
    public MarkSettingPage moveToElement(SelenideElement element){
        element.hover();
        return new MarkSettingPage();
    }

    @Step("Проверка, что метка красная")
    public boolean isMarkOfTender(){
        return Objects.requireNonNull(markTender.getAttribute("style")).contains("background: rgb(235, 9, 16);");
    }

    @Step("Проверка, что метка красная в карточном виде")
    public boolean isMarkOfTenderCardView(){
        return Objects.requireNonNull(markTenderCardView.getAttribute("style")).contains("background-color: rgb(235, 9, 16);");
    }

    @Step("Проверка, что метки нет")
    public boolean isDeletionMarkOfTender(){
        return Objects.requireNonNull(markTender.getAttribute("style")).contains("height: 0%;");
    }

    @Step("Проверка установки метки для всех тендеров на странице для табличного вида")
    public boolean isMarkAllSelectedTender(){
        boolean check = true;
        for(SelenideElement type:markTenderCollections){
            if(!Objects.requireNonNull(type.getAttribute("style")).contains("background: rgb(235, 9, 16);")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка установки метки для всех тендеров на странице для карточного вида")
    public boolean isMarkAllSelectedTenderCardView(){
        boolean check = true;
        for(SelenideElement type:markTenderCardViewCollections){
            if(!Objects.requireNonNull(type.getAttribute("style")).contains("background: rgb(235, 9, 16);")){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка заблокированности кнопки удаления метки")
    public boolean isDisabledButtonDeleteMark(){
        return Objects.requireNonNull(buttonDeleteMark.getAttribute("aria-disabled")).contains("true");
    }

    @Step("Проверка появления текста ошибки при сохранении метки")
    public boolean isVisibleErrorMessageSaveMark(){
        return errorMessageEmptyFieldNameMark.is(visible);
    }

    @Step("Проверка текста ошибки при сохранении метки с пустым названием")
    public boolean isCorrectErrorMessageEmptyNameMark(){
        return errorMessageEmptyFieldNameMark.getText().equals("Введите название метки.");
    }

    @Step("Проверка текста ошибки при сохранении метки с дублированным названием")
    public boolean isCorrectErrorMessageDuplicateNameMark(){
        return errorMessageEmptyFieldNameMark.getText().equals("Такая метка уже существует.");
    }

    @Step("Проверка базового списка меток")
    public boolean isCorrectBaseListMark(){
        List<String> baseListMark = elementListMarkCollections.texts();

        List<String> checkBaseListMark = new ArrayList<>();
        checkBaseListMark.add("Красный");
        checkBaseListMark.add("Сиреневый");
        checkBaseListMark.add("Голубой");
        checkBaseListMark.add("Оранжевый");
        checkBaseListMark.add("Зеленый");
        checkBaseListMark.add("Синий");

        return baseListMark.equals(checkBaseListMark);
    }
}
