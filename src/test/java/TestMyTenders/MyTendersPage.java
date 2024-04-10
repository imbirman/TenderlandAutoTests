package TestMyTenders;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Condition.*;

public class MyTendersPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка удаления добавленного тендера в списке тендеров */
    protected SelenideElement buttonDeleteAddedTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");



    /** Окно подтверждения удаления */
    private final SelenideElement windowApproveDelete = $x("//div[@class='dx-overlay-content dx-popup-normal dx-popup-draggable dx-resizable dx-popup-flex-height']");
    /** Кнопка удаления тендера в списке тендеров */
    private final SelenideElement buttonDeleteTenderInListTenders = $x("(//div[@class='favourite-kanban-delete-favourite'])[1]");
    /** Кнопка для скачивания документации тендера в списке тендеров */
    private final SelenideElement buttonLoadDocumentationInListTenders = $x("(//div[@class='favourite-kanban-load-documents'])[1]");


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

    @Step("Проверка появления окна подтверждения удаления")
    public boolean isCheckVisibleWindowApproveDelete(){
        return windowApproveDelete.is(visible);
    }

    public boolean isCheckClickableButtonDeleteTenderInListTenders(){
        return buttonDeleteTenderInListTenders.is(interactable);
    } // Проверка кликабельности кнопки удаления тендера в списке тендеров

    public boolean isCheckClickableButtonLoadDocumentationTenderInListTenders(){
        return buttonLoadDocumentationInListTenders.is(interactable);
    } // Проверка кликабельности кнопки скачивания документации тендера в списке тендеров
}
