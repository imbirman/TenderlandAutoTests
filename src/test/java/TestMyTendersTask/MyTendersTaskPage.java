package TestMyTendersTask;

import TestMyTendersFilters.MyTendersFiltersPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class MyTendersTaskPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@id='username']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//div[@id='password']//input"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");


    /** Список кнопок удаления задачи в списке */
    protected ElementsCollection buttonDeleteTaskCollection = $$x("//div[contains(@class, 'favourite-task-button-delete')]");
    /** Список названий задач */
    protected ElementsCollection nameTaskCollection = $$x("//div[@id='favourite-task-name-create']//textarea");
    /** Список чекбоксов выполнения задачи */
    protected ElementsCollection checkboxCompleteTaskCollection = $$x("//div[@id='tasks-multiview']//span[@class='dx-checkbox-icon']");
    /** Список возможных статусов задачи */
    protected ElementsCollection possibleStatusesTask = $$x("//div[@class='dx-popup-content']//div[contains(@class, 'favourite-task-status')]");
    /** Список статусов задачи */
    private final ElementsCollection statusTaskInListTasksCollection = $$x("//div[contains(@class, 'favourite-task-statusbox')]//div[@class='dx-texteditor-container']//input");



    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка добавления задачи во вкладке "Карточки" */
    protected SelenideElement buttonAddTask = $x("//div[@id='favourite-create-task']");
    /** Кнопка сохранения задачи */
    protected SelenideElement buttonSaveTask = $x("//div[@id='favourite-task-button-create']");
    /** Кнопка подтверждения удаления задачи */
    protected SelenideElement buttonConfirmationDeleteTask = $x("//div[@class='common-popup-confirm-footer']//span[text()='Удалить']");
    /** Кнопка, возвращающая к задаче */
    protected SelenideElement buttonBackToTask = $x("//div[@id='tasks-multiview']//div[@class='favourite-card-back-tasks']");
    /** Кнопка сохранения времени окончания выполнения задачи */
    protected SelenideElement buttonSaveDeadline = $x("//span[text() = 'OK']");


    /** Открыть карточку первого тендера */
    protected SelenideElement openCardFirstTender = $x("//div[@class='dx-treelist-text-content']/div[@class='favourite-kanban-card']");
    /** Название последней задачи */
    protected SelenideElement nameLastTask = $x("(//div[@id='favourite-tender-tasks-working']//textarea)[last()]");
    /** Кнопка включения метки выполнения задачи */
    protected SelenideElement radiobuttonCompleteTask = $x("//div[@id='favourite-tender-tasks-working']//div[@role='checkbox']");
    /** Открыть Окно срока окончания задачи */
    protected SelenideElement openDeadlineWindow = $x("//div[@id='favourite-task-endtime-create']");
    /** Блок выполненных задач */
    protected SelenideElement groupCompleteTasks = $x("(//div[@class='favourite-tender-tasks-group'])[2]");
    /** Статус задачи в работе */
    protected SelenideElement statusWorkingTask = $x("//div[@id='favourite-tender-tasks-working']//div[contains(@class, 'favourite-task-statusbox')]//input[@class='dx-texteditor-input']");



    /** Поле ввода названия задача */
    private final SelenideElement fieldEntryNameTask = $x("//div[@id='favourite-task-name-create']//textarea");
    /** Статус выполненной задачи */
    private final SelenideElement statusCompleteTask = $x("//div[@id='favourite-tender-tasks-complete']//div[contains(@class, 'favourite-task-statusbox')]//input[@class='dx-texteditor-input']");



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
        return nameLastTask.getValue();
    }

    @Step("Получить кнопку удаления задачи по её порядковому номеру")
    public SelenideElement getButtonDeleteTaskByNumber(int number){
        return buttonDeleteTaskCollection.get(number);
    }

    @Step("Получить количество возможных статусов задачи")
    public Integer getNumberPossibleStatusesTask(){
        return possibleStatusesTask.size();
    }

    @Step("Получение задачи по ее порядковому номеру")
    public SelenideElement getTaskByNumber(int number){
        return nameTaskCollection.get(number);
    }

    @Step("Получение статуса задачи в списке задач по её порядковому номеру")
    public SelenideElement getStatusTaskInListTasksByNumber(int number){
        return statusTaskInListTasksCollection.get(number);
    }

    @Step("Получение чекбокса задачи в списке задач по его порядковому номеру")
    public SelenideElement getCheckboxInListTasksByNumber(int number){
        return checkboxCompleteTaskCollection.get(number);
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
        return Objects.equals(statusTaskInListTasksCollection.get(statusTaskInListTasksCollection.size() - 1).getValue(), "в работе");
    }

    @Step("Проверка статуса задачи в окне задачи после нажатия  \"Выполнено\"")
    public boolean isCheckStatusTaskComplete(){
        return Objects.equals(statusCompleteTask.getValue(), "выполнена");
    }

}
