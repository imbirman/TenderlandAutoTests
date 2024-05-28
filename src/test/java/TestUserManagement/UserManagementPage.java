package TestUserManagement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.eo.Se;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UserManagementPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */

    /** Список привязанных автопоисков */
    private final ElementsCollection linkedAutosearchCollections = $$x("//div[@class='user-profile-autosearch-mail-container']");
    /** Список почт или телеграмма  */
    private final ElementsCollection mailOrTelegramCollections = $$x("//div[@class='user-profile-accounts-list-item-content-name']");


    /** Кнопка открытия окна управления профилем пользователя */
    protected SelenideElement buttonOpenManagementProfile = $x("(//div[@id='user-profile-menu-list']//div[@class='dx-item dx-list-item'])[1]");
    /** Кнопка открытия окна управления рассылками пользователя */
    protected SelenideElement buttonOpenManagementMailing = $x("(//div[@id='user-profile-menu-list']//div[@class='dx-item dx-list-item'])[2]");
    /** Кнопка открытия окна управления пользователями */
    protected SelenideElement buttonOpenManagementUsers = $x("(//div[@id='user-profile-menu-list']//div[@class='dx-item dx-list-item'])[3]");
    /** Кнопка добавления почты */
    protected SelenideElement buttonAddedMail = $x("//div[@id='user-accounts-create-mail-button']");
    /** Открыть окно управления */
    protected SelenideElement openWindowUserManagement = $x("//div[@class='common-header-login-block']");


    /** Чекбокс "Приводить время к выбранному" */
    protected SelenideElement checkboxCastTimeToUser = $x("//div[@id='user-profile-cast-time-to-user']");

    /** Пометка времени кабинета пользователя */
    private final SelenideElement labelTimeUser = $x("//div[@id='user-profile-time-label']");
    /** Сообщение об ошибке при попытке добавления почты при пустом соответствующем поле */
    private final SelenideElement messageErrorFieldAddedMail = $x("//div[@class='dx-overlay-content dx-invalid-message-content']");
    /** Поле для ввода ключевых слов для поиска автопоисков */
    private final SelenideElement fieldSearchAutoSearch = $x("//div[@id='user-accounts-search-autosearches']//input");
    /** Поле для ввода ключевых слов для поиска почты или телеграмма */
    private final SelenideElement fieldSearchMailOrTelegram = $x("//div[@id='user-accounts-search-accounts']//input");
    /** Поле для добавления почты */
    private final SelenideElement fieldEnterMailForAdded = $x("//div[@id='user-accounts-create-mail-textbox']//input");


    @Step("Ожидание {number}")
    public UserManagementPage waitFor(long number){
        sleep(number);
        return new UserManagementPage();
    }

    @Step("Ввести логин для авторизации")
    public UserManagementPage typeLogin(String login){loginField.sendKeys(login); return new UserManagementPage();}

    @Step("Ввести пароль для авторизации")
    public UserManagementPage typePassword(String password){
        passwordField.sendKeys(password);
        return new UserManagementPage();
    }

    @Step("Ввести ключевое слово для поиска автопоисков")
    public UserManagementPage typeSearchAutoSearch(String search){
        fieldSearchAutoSearch.sendKeys(search);
        return new UserManagementPage();
    }

    @Step("Ввести ключевое слово для поиска почты или телеграмма")
    public UserManagementPage typeSearchMailOrTelegram(String type){
        fieldSearchMailOrTelegram.sendKeys(type);
        return new UserManagementPage();
    }

    @Step("Ввод почты")
    public UserManagementPage typeMail(String mail){
        fieldEnterMailForAdded.sendKeys(mail);
        return new UserManagementPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public UserManagementPage clickLogInButton(){
        logInButton.click();
        return new UserManagementPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public UserManagementPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new UserManagementPage();
    }

    @Step("Нажать кнопку {button}")
    public UserManagementPage clickButton(SelenideElement button){
        button.click();
        return new UserManagementPage();
    }

    @Step("Получить сообщение об ошибке добавления почты при пустом соответствующем поле")
    public String getMessageErrorEmptyFieldAddedMail(){
        return messageErrorFieldAddedMail.getText();
    }

    @Step("Проверка пометки времени кабинета пользователя по умолчанию")
    public boolean isDefaultLabelTimeUser(){
        return labelTimeUser.getText().equals("Кабинет работает по времени площадок");
    }

    @Step("Проверка пометки времени кабинета пользователя после нажатия чекбокса")
    public boolean isLabelTimeUserAfterSelectedCheckbox(){
        return labelTimeUser.getText().equals("Кабинет работает по выбранному часовому поясу");
    }

    @Step("Проверка наличия сообщения об ошибке добавления почты при пустом соответствующем поле")
    public boolean isVisibleMessageErrorFieldAddedMail(){
        return messageErrorFieldAddedMail.is(visible);
    }

    @Step("Проверка поиска привязанных автопоисков")
    public boolean isCorrectSearchAutosearch(){
        boolean check = false;
        for(String type:linkedAutosearchCollections.texts()){
            if(type.contains("Проверка")){check = true; break;}
        }
        return check;
    }

    @Step("Проверка поиска почты или телеграмма")
    public boolean isCorrectSearchMailOrTelegram(){
        boolean check = false;
        for (String type: mailOrTelegramCollections.texts()){
            if(type.contains("agafonov")){check = true; break;}
        }
        return check;
    }

    @Step("Проверка списка почт")
    public boolean isCorrectListMailsAfterAddedMail(){
        boolean check = false;
        for(String type: mailOrTelegramCollections.texts()){
            if(type.contains("test@test.ru")){check = true; break;}
        }
        return check;
    }

}
