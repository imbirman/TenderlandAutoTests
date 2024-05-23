package TestMyTenders;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyTendersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Главная вкладка "Карточки" */
    protected SelenideElement tabCards = $x("(//div[contains(@class,'dx-item dx-tab')])[1]");
    /** Главная вкладка "Таблица" */
    protected SelenideElement tabTable = $x("(//div[contains(@class,'dx-item dx-tab')])[2]");
    /** Главная вкладка "Календарь" */
    protected SelenideElement tabCalendar = $x("(//div[contains(@class,'dx-item dx-tab')])[3]");

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Кнопка контекстного меню столбца */
    protected SelenideElement contextMenuColumn = $x("//div[@class='favourite-kanban-list-title']/i");
    /** Открыть карточку первого тендера */
    protected SelenideElement openCardFirstTender = $x("//div[@class='dx-treelist-text-content']/div[@class='favourite-kanban-card']");
    /** Выбор тестового пользователя в качестве ответственного для первого тендера в карточке тендера */
    protected SelenideElement userTestInCardTender = $x("//div[text()='Тестовый Тест Тестович']");
    /** Выбор пользователя "Админ" в качестве ответственного для первого тендера в карточке тендера */
    protected SelenideElement userAdminInCardTender = $x("//div[text()='Админ']");
    /** Выбор тестового пользователя в качестве ответственного для первого тендера, вкладка "Таблица" */
    protected SelenideElement userTestInListUsersTabTable = $x("(//div[@class='dx-item-content dx-list-item-content'])[2]");
    /** Поле для ввода заметки во вкладке "Таблица" */
    protected SelenideElement firstFieldEntryNoticeInTabTable = $x("(//div[@class='favourite-table-td-name']//a[text()='0148300041821000004']//following::textarea)[1]");
    /** Поле для ввода заметки в карточке тендера */
    protected SelenideElement fieldEntryNoticeInCardTender = $x("//div[@id='favourite-card-notice']//textarea");


    /** Список вкладок в карточке тендера */
    private final ElementsCollection tabInCardTenderCollections = $$x("//div[@id='tender-tab-panel']//div[contains(@class, 'dx-item dx-tab')]");
    /** Элемент контекстного меню столбца */
    private final ElementsCollection elementContextMenuColumnCollections = $$x("//div[@class='favourite-kanban-context-menu-item']");


    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка удаления добавленного тендера в списке тендеров */
    protected SelenideElement buttonDeleteAddedTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");
    /** Кнопка "Удалить" контекстного меню столбца */
    protected SelenideElement buttonDeleteContextMenuColumn = $x("(//div[@class='dx-submenu']//div[@role='menuitem'])[3]");
    /** Кнопка смены метки в карточке тендера */
    protected SelenideElement buttonChangeTagInCardTender =$x("//div[@class='favourites-card-tag-button']");
    /** Кнопка "Ссылка на источник" */
    private final SelenideElement buttonLinkOfSourceInCard = $x("//i[@id='favourites-card-link']");
    /** Кнопка "Удалить тендер" */
    private final SelenideElement buttonDeleteTenderInCard = $x("//i[@id='favourites-card-link-delete-icon']");
    /** Кнопка раскрытия списка ответственных по тендеру */
    protected SelenideElement buttonOpenListResponsibleInCardTender = $x("//div[@id='favourite-tender-select-responsible']//div[@class='dx-dropdowneditor-icon']");

    protected SelenideElement buttonOpenListResponsibleInTable = $x("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container']//div[@class='dx-dropdowneditor-icon'])[5]");
    /** Кнопка закрытия карточки тендера */
    protected SelenideElement buttonCloseCardTender = $x("//i[@class='dx-icon dx-icon-close']");



    /** Окно подтверждения удаления */
    private final SelenideElement windowApproveDelete = $x("//div[@class='dx-overlay-content dx-popup-normal dx-popup-draggable dx-resizable dx-popup-flex-height']");
    /** Кнопка удаления тендера в списке тендеров */
    private final SelenideElement buttonDeleteTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");
    /** Кнопка для скачивания документации тендера в списке тендеров */
    private final SelenideElement buttonLoadDocumentationInListTenders = $x("(//div[@class='favourite-kanban-load-documents'])[1]");

    /** Панель тендера в карточке тендера */
    private final SelenideElement panelTenderInCardTender = $x("//div[@class='favourite-tender-card-div']");
    /** Ответственный по тендеру в карточке тендера */
    private final SelenideElement userResponsibleInCardTender = $x("//div[@id='favourite-tender-select-responsible']//div[@class='dx-texteditor-container']//input");
    /** Ответственный по тендеру на вкладке "Таблица" */
    private final SelenideElement userResponsibleInTabTable = $x("(//div[@class='dx-scrollable-wrapper']//div[@class='dx-texteditor-input-container']//input)[5]");



    @Step("Ожидание {number}")
    public MyTendersPage waitFor(long number){
        sleep(number);
        return new MyTendersPage();
    }

    @Step("Прокрутить до элемента")
    public MyTendersPage scrollToElement(SelenideElement element){
        element.scrollIntoView(true);
        return new MyTendersPage();
    }

    @Step("Ввести логин для авторизации")
    public MyTendersPage typeLogin(String login){loginField.sendKeys(login); return new MyTendersPage();}

    @Step("Ввести пароль для авторизации")
    public MyTendersPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MyTendersPage();
    }

    @Step("Очистить поле")
    public MyTendersPage clearField(SelenideElement field){
        field.clear();
        return new MyTendersPage();
    }

    @Step("Ввести заметку во вкладке \"Таблица\"")
    public MyTendersPage typeNoticeInTabTable(String notice){
        firstFieldEntryNoticeInTabTable.sendKeys(notice);
        return new MyTendersPage();
    }

    @Step("Ввести заметку в карточке тендера")
    public MyTendersPage typeNoticeInCardTender(String notice){
        fieldEntryNoticeInCardTender.sendKeys(notice);
        return new MyTendersPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public MyTendersPage clickLogInButton(){
        logInButton.click();
        return new MyTendersPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MyTendersPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MyTendersPage();
    }

    @Step("Нажать кнопку {button}")
    public MyTendersPage clickButton(SelenideElement button){
        button.click();
        return new MyTendersPage();
    }

    @Step("Получить количество вкладок в карточке тендера")
    public Integer getNumberTabInCardTender(){
        return tabInCardTenderCollections.size();
    }

    @Step("Проверка появления окна подтверждения удаления")
    public boolean isCheckVisibleWindowApproveDelete(){
        return windowApproveDelete.is(visible);
    }

    @Step("Проверка кликабельности кнопки удаления тендера в списке тендеров")
    public boolean isCheckClickableButtonDeleteTenderInListTenders(){
        return buttonDeleteTenderInListTenders.is(interactable);
    }

    @Step("Проверка кликабельности кнопки скачивания документации тендера в списке тендеров")
    public boolean isCheckClickableButtonLoadDocumentationTenderInListTenders(){
        return buttonLoadDocumentationInListTenders.is(interactable);
    }

    @Step("Проверка некликабельности кнопки \"Удалить\" контекстного меню столбца")
    public boolean isCheckDisableButtonDeleteContextMenuColumn(){
        return Objects.requireNonNull(buttonDeleteContextMenuColumn.getAttribute("class")).contains("dx-state-disabled");
    }

    @Step("Проверка отображения карточки тендера")
    public boolean isCheckVisibleCard(){
        return panelTenderInCardTender.is(visible);
    }

    @Step("Проверка кликабельности кнопки смены метки в карточке тендера")
    public boolean isCheckClickableButtonChangeTag(){
        return buttonChangeTagInCardTender.is(interactable);
    }

    @Step("Проверка кликабельности кнопки ссылки на источник в карточке тендера")
    public boolean isCheckClickableButtonLinkSource(){
        return buttonLinkOfSourceInCard.is(interactable);
    }

    @Step("Проверка кликабельности кнопки удаления тендера в карточке тендера")
    public boolean isCheckClickableButtonDeleteTenderInCard(){
        return buttonDeleteTenderInCard.is(interactable);
    }

    @Step("Проверка корректности названия вкладок в карточке тендера")
    public boolean isCorrectNameTabCards(){
        List<String> checkArray = new ArrayList<>();
        checkArray.add("CВЕДЕНИЯ");
        checkArray.add("ЗАКАЗЧИК");
        checkArray.add("ДОКУМЕНТАЦИЯ");
        checkArray.add("ЖУРНАЛ");
        checkArray.add("КОММЕНТАРИИ");
        System.out.println(tabInCardTenderCollections.texts());
        return checkArray.equals(tabInCardTenderCollections.texts());
    }

    @Step("Проверка кликабельности главной вкладки \"Карточки\"")
    public boolean isCheckClickableTabCards(){
        return tabCards.is(interactable);
    }

    @Step("Проверка кликабельности главной вкладки \"Таблица\"")
    public boolean isCheckClickableTabTable(){
        return tabTable.is(interactable);
    }

    @Step("Проверка кликабельности главной вкладки \"Календарь\"")
    public boolean isCheckClickableTabCalendar(){
        return tabCalendar.is(interactable);
    }

    @Step("Получение значения ответственного тендера во вкладке \"Таблица\"")
    public String getResponsibleInTabTable(){
        return userResponsibleInTabTable.getValue();
    }

    @Step("Получение значение ответственного в карточке тендера")
    public String getResponsibleInCardTender(){
        return userResponsibleInCardTender.getValue();
    }

    @Step("Проверка заметки в карточке тендера")
    public boolean isCheckNoticeInCardTender(){
        return Objects.equals(fieldEntryNoticeInCardTender.getValue(), "тестовая заметка2");
    }

    @Step("Проверка заметки во вкладке \"Таблица\"")
    public boolean isCheckNoticeInTabTable(){
        return Objects.equals(firstFieldEntryNoticeInTabTable.getValue(), "тестовая заметка");
    }

    @Step("Проверка кнопок контекстного меню столбца")
    public boolean isCheckButtonsContextMenuColumn(){
        List<String> elements = elementContextMenuColumnCollections.texts();
        List<String> elementsForCheck = new ArrayList<>();
        elementsForCheck.add("Переместить все");
        elementsForCheck.add("Выгрузить");
        elementsForCheck.add("Удалить");
        return elements.equals(elementsForCheck);
    }
}
