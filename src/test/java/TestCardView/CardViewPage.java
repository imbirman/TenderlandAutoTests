package TestCardView;

import TestAuditor.AuditorPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Condition.*;

public class CardViewPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка открытия настроек пользовательского вида */
    protected SelenideElement buttonOpenWindowCustomView = $x("//i[@class='material-icons-outline icon-24px icon-grey icon-grey-hover md-settings']");
    /** Пункт "Настройки" */
    protected SelenideElement buttonAccordionSettings = $x("(//div[contains(@class,'dx-item dx-accordion-item')])[3]");
    /** Кнопка закрытия окна пользовательского вида */
    protected SelenideElement buttonCloseWindowCustomView = $x("//div[@role='toolbar']//i");
    /** Кнопка "Сохранить вид" */
    protected SelenideElement buttonSaveCustomView = $x("//div[@id='search-view-save-button']");
    /** Кнопка автопоиска "Проверка поиска по названию тендера и исключению из названия" */
    protected SelenideElement buttonCheckTenderNameAndNameDeletion = $x("//div[text()='Проверка поиска по названию тендера и исключению из названия']");
    /** Кнопка раскрытия списка пользовательских видов */
    protected SelenideElement buttonOpenListCustomView = $x("//div[@id='search-panel-change-custom-view']");
    /** Пункт списка пользовательского вида "Тестовый вид" */
    protected SelenideElement buttonItemCustomViewTestView = $x("//div[text()='Тестовый вид']");
    /** Кнопка "Карточный вид" */
    protected SelenideElement buttonCardView = $x("//div[@id='search-panel-change-custom-view']");
    /** Кнопка "Табличный вид" */
    protected SelenideElement buttonTableView = $x("//div[@id='search-panel-table-view']");
    /** Кнопка открытия детализации тендера */
    protected SelenideElement buttonOpenDetailing = $x("(//div[contains(@class,'dx-item dx-accordion-item')])[2]");
    /** Кнопка "Выгрузить продукты" в детализации тендера */
    protected SelenideElement buttonUnloadProducts = $x("(//div[@id='search-result-wrapper']//div[@class='dx-item-content dx-multiview-item-content']/div)[2]");


    /** Пункт "Табличный вид" в настройках пользовательского вида */
    protected SelenideElement radiobuttonTableView = $x("(//div[@id='search-view-type']//div[@class='dx-radio-value-container']/div)[1]");
    /** Пункт "Карточный вид" в настройках пользовательского вида */
    protected SelenideElement radiobuttonCardView = $x("(//div[@id='search-view-type']//div[@class='dx-radio-value-container']/div)[2]");
    /** Чекбокс "Раскрывать карточки" */
    protected SelenideElement checkboxOpenCards = $x("//div[@id='search-view-autoexpand-cards']");

    /** Блок с пунктом "Раскрывать карточки" */
    private final SelenideElement blockOpenCards = $x("//div[@id='search-view-autoexpand-cards-checkbox']");


    @Step("Ожидание {number}")
    public CardViewPage waitFor(long number){
        sleep(number);
        return new CardViewPage();
    }

    @Step("Ввести логин для авторизации")
    public CardViewPage typeLogin(String login){loginField.sendKeys(login); return new CardViewPage();}

    @Step("Ввести пароль для авторизации")
    public CardViewPage typePassword(String password){
        passwordField.sendKeys(password);
        return new CardViewPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public CardViewPage clickLogInButton(){
        logInButton.click();
        return new CardViewPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public CardViewPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new CardViewPage();
    }

    @Step("Нажать кнопку {button}")
    public CardViewPage clickButton(SelenideElement button){
        button.click();
        return new CardViewPage();
    }

    @Step("Проверка отображения при выборе карточного вида блока с чекбоксом \"Раскрывать карточки\"")
    public boolean isDisplayedBlockOpenCards(){
        return blockOpenCards.isDisplayed();
    }

    @Step("Проверка, что по умолчанию выбран табличный вид")
    public boolean isSelectedTableView(){
        return Objects.requireNonNull(radiobuttonTableView.getAttribute("class")).contains("dx-radiobutton-icon-checked");
    }

    @Step("Проверка, что карточный вид выбран")
    public boolean isSelectedCardView(){
        return Objects.requireNonNull(radiobuttonCardView.getAttribute("class")).contains("dx-radiobutton-icon-checked");
    }

    @Step("Проверка работы чекбокса \"Раскрывать карточки\"")
    public boolean isSelectedCheckboxOpenCards(){
        return Objects.requireNonNull(checkboxOpenCards.getAttribute("class")).contains("dx-checkbox-checked");
    }

    public boolean isDisplayedButtonTableView(){
        return buttonTableView.isDisplayed();
    } // Проверка, что кнопка "Табличный вид" отображается

    public boolean isDisplayedButtonCardView(){
        return buttonCardView.isDisplayed();
    } // Проверка, что кнопка "Карточный вид" отображается

    public boolean isClickableButtonTableView(){
        return buttonTableView.is(interactable);
    } // Проверка, что кнопка "Табличный вид" кликабельна

    public boolean isClickableButtonCardView(){
        return buttonCardView.is(interactable);
    } // Проверка, что кнопка "Карточный вид" кликабельна

}
