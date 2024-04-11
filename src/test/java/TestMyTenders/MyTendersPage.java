package TestMyTenders;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyTendersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Главная вкладка "Карточки" */
    protected SelenideElement tabCards = $x("//div[@id='favourite-select-tabs']//div[text()='Карточки']");
    /** Главная вкладка "Таблица" */
    protected SelenideElement tabTable = $x("//div[@id='favourite-select-tabs']//div[text()='Таблица']");
    /** Главная вкладка "Календарь" */
    protected SelenideElement tabCalendar = $x("//div[@id='favourite-select-tabs']//div[text()='Календарь']");

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Кнопка контекстного меню столбца */
    protected SelenideElement contextMenuColumn = $x("//div[@class='favourite-kanban-list-title']/i");
    /** Открыть карточку первого тендера */
    protected SelenideElement openCardFirstTender = $x("//div[@class='dx-treelist-text-content']/div[@class='favourite-kanban-card']");


    /** Список вкладок в карточке тендера */
    private final ElementsCollection tabInCardTenderCollections = $$x("//div[@id='tender-tab-panel']//div[contains(@class, 'dx-item dx-tab')]");


    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка удаления добавленного тендера в списке тендеров */
    protected SelenideElement buttonDeleteAddedTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");
    /** Кнопка "Удалить" контекстного меню столбца */
    protected SelenideElement buttonDeleteContextMenuColumn = $x("(//div[@class='dx-submenu']//div[@role='menuitem'])[3]");
    /** Кнопка смены метки в карточке тендера */
    protected SelenideElement buttonChangeTagInCardTender =$x("//div[@class='favourite-card-control-icons']//i[@class='mdi mdi-24px mdi-tag-outline']");
    /** Кнопка "Ссылка на источник" */
    private final SelenideElement buttonLinkOfSourceInCard = $x("//i[@id='favourites-card-link']");
    /** Кнопка "Удалить тендер" */
    private final SelenideElement buttonDeleteTenderInCard = $x("//i[@class='mdi mdi-24px mdi-delete-outline']");



    /** Окно подтверждения удаления */
    private final SelenideElement windowApproveDelete = $x("//div[@class='dx-overlay-content dx-popup-normal dx-popup-draggable dx-resizable dx-popup-flex-height']");
    /** Кнопка удаления тендера в списке тендеров */
    private final SelenideElement buttonDeleteTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");
    /** Кнопка для скачивания документации тендера в списке тендеров */
    private final SelenideElement buttonLoadDocumentationInListTenders = $x("(//div[@class='favourite-kanban-load-documents'])[1]");

    /** Панель тендера в карточке тендера */
    private final SelenideElement panelTenderInCardTender = $x("//div[@id='tender-tab-panel']");



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
}
