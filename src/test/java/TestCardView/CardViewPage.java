package TestCardView;

import TestAuditor.AuditorPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class CardViewPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Кнопка открытия настроек пользовательского вида */
    protected SelenideElement buttonOpenWindowCustomView = $x("//i[@class='material-icons-outline icon-24px icon-grey icon-grey-hover md-settings']");
    /** Пункт "Настройки" */
    protected SelenideElement buttonAccordionSettings = $x("(//div[contains(@class,'dx-item dx-accordion-item')])[3]");

    /** Пункт "Табличный вид" в настройках пользовательского вида */
    protected SelenideElement radiobuttonTableView = $x("(//div[@id='search-view-type']//div[@class='dx-radio-value-container']/div)[1]");
    /** Пункт "Карточный вид" в настройках пользовательского вида */
    protected SelenideElement radiobuttonCardView = $x("(//div[@id='search-view-type']//div[@class='dx-radio-value-container']/div)[2]");

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


}
