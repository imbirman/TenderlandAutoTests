package TestMyTendersTask;

import TestMyTendersFilters.MyTendersFiltersPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MyTendersTaskPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");


    /** Список кнопок удаления последней задачи в списке */
    protected ElementsCollection buttonDeleteTaskCollection = $$x("//div[@id='tasks-multiview']//i[contains(@class, 'favourite-task-delete-button')]");
    /** Список названий задач */
    protected ElementsCollection nameTaskCollection = $$x("//div[@id='tasks-multiview']//div[@class='favourite-card-name-task']");
    /** Список статусов задачи */
    private final ElementsCollection statusTaskInListTasksCollection = $$x("//div[@id='favourite-tender-tasks']/div/div[2]/div");


    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка добавления задачи во вкладке "Карточки" */
    protected SelenideElement buttonAddTask = $x("//div[@id='tasks-multiview']//div[@style='cursor: pointer;']/i");
    /** Кнопка добавления задачи во вкладке "Таблица" */
    protected SelenideElement buttonAddTaskInTabTable = $x("//div[@class='favourite-table-add-task']/div");
    protected SelenideElement buttonConfirmationDeleteTask = $x("//span[text()='Удалить']");

    /** Открыть карточку первого тендера */
    protected SelenideElement openCardFirstTender = $x("//div[@class='dx-treelist-text-content']/div[@class='favourite-kanban-card']");
    /** Название последней задачи */
    protected SelenideElement nameLastTask = $x("(//div[@id='tasks-multiview']//div[@class='favourite-card-name-task'])[last()]");


    /** Поле ввода названия задача */
    private final SelenideElement fieldEntryNameTask = $x("//div[@id='tasks-multiview']//input[@class='dx-texteditor-input']");



    @Step("Ожидание {number}")
    public MyTendersTaskPage waitFor(long number){
        sleep(number);
        return new MyTendersTaskPage();
    }

    @Step("Ввести логин для авторизации")
    public MyTendersTaskPage typeLogin(String login){loginField.sendKeys(login); return new MyTendersTaskPage();}

    @Step("Ввести пароль для авторизации")
    public MyTendersTaskPage typePassword(String password){
        passwordField.sendKeys(password);
        return new MyTendersTaskPage();
    }

    @Step("Ввод названия задачи")
    public MyTendersTaskPage typeNameTask(String name) {
        fieldEntryNameTask.sendKeys(name);
        waitFor(100);
        fieldEntryNameTask.sendKeys(Keys.ENTER);
        return new MyTendersTaskPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public MyTendersTaskPage clickLogInButton(){
        logInButton.click();
        return new MyTendersTaskPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public MyTendersTaskPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new MyTendersTaskPage();
    }

    @Step("Нажать кнопку {button}")
    public MyTendersTaskPage clickButton(SelenideElement button){
        button.click();
        return new MyTendersTaskPage();
    }

    @Step("Получение названия последней задачи")
    public String getNameLastTask(){
        return nameLastTask.getText();
    }

    @Step("Получить кнопку удаления задачи по её порядковому номеру")
    public SelenideElement getButtonDeleteTaskByNumber(int number){
        return buttonDeleteTaskCollection.get(number);
    }

    @Step("Проверка удаления задачи")
    public boolean isCheckDeleteTask(){
        boolean check = true;
        List<String> namesTasks = nameTaskCollection.texts();
        for(String type:namesTasks){
            if(type.contains("тестовая задача 4")){check = false; break;}
        }
        return check;
    }

    @Step("Проверка статуса созданной задачи")
    public boolean isCheckStatusAddedTask(){
        return statusTaskInListTasksCollection.get(statusTaskInListTasksCollection.size()-1).getText().equals("ВЫПОЛНЯЕТСЯ");
    }
}
