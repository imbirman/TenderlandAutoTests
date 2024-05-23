package TestMyTendersTask;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
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
    protected ElementsCollection possibleStatusesTaskCollection = $$x("//div[@class='dx-popup-content']//div[contains(@class, 'favourite-task-status')]");
    /** Список возможных ответственных за задачу */
    protected ElementsCollection possibleResponsibleForTaskCollection = $$x("//div[@class='favourite-card-task-menu-item-name']");
    /** Список статусов задачи */
    private final ElementsCollection statusTaskInListTasksCollection = $$x("//div[contains(@class, 'favourite-task-statusbox')]//div[@class='dx-texteditor-container']//input");
    /** Список времени оповещений задачи*/
    private final ElementsCollection notifyTimeCollection = $$x("//div[@id='favourites-popover-notify-list']//div[@class='favourites-popover-item']");



    /** Кнопка в боковом меню "Мои тендеры" */
    protected SelenideElement buttonTabMenuMyTenders = $x("//div[@id='main-menu-list']//div[text()='Мои тендеры']");
    /** Кнопка добавления задачи во вкладке "Карточки" */
    protected SelenideElement buttonAddTask = $x("//div[@id='favourite-create-task']");
    /** Кнопка сохранения задачи */
    protected SelenideElement buttonSaveTask = $x("//div[@id='favourite-task-button-create']");
    /** Кнопка подтверждения удаления задачи */
    protected SelenideElement buttonConfirmationDeleteTask = $x("//div[@class='common-popup-confirm-footer']//span[text()='Удалить']");
    /** Кнопка сохранения времени окончания выполнения задачи */
    protected SelenideElement buttonSaveDeadline = $x("//span[text() = 'OK']");
    /** Кнопка открытия списка вариантов оповещений */
    protected SelenideElement buttonOpenListNotifyTask = $x("//div[@class='favourite-card-task-header']//i");



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
    /** Ответственный за задачу */
    protected SelenideElement responsibleForTask = $x("//div[@id='favourite-tender-tasks']//div[@class='tl-flex-space-container']//div[@class='dx-texteditor-input-container']/input");



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
        return possibleStatusesTaskCollection.size();
    }

    @Step("Получить количество вариантов времен оповещений")
    public Integer getNumberNotifyTimes(){
        return notifyTimeCollection.size();
    }

    @Step("Получить количество возможных ответственных за задачу")
    public Integer getNumberPossibleResponsibleForTask(){
        return possibleResponsibleForTaskCollection.size();
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

    @Step("Проверка списка возможных статусов задачи")
    public boolean isCheckListPossibleStatusesTask(){
        List<String> list = new ArrayList<>();
        list.add("в работе");
        list.add("выполнена");
        list.add("просрочена");
        return list.equals(possibleStatusesTaskCollection.texts());
    }

    @Step("Проверка списка вариантов оповещений")
    public boolean isCheckListNotifyTask(){
        List<String> list = new ArrayList<>();
        list.add("За 1 час до окончания");
        list.add("За 2 часа до окончания");
        list.add("За 3 часа до окончания");
        list.add("За 5 часов до окончания");
        list.add("За 8 часов до окончания");
        list.add("За 12 часов до окончания");
        list.add("За сутки до окончания");
        return list.equals(notifyTimeCollection.texts());
    }

    @Step("Првоерка списка возможных ответственных за задачу")
    public boolean isCheckPossibleResponsibleForTask(){
        List<String> list = new ArrayList<>();
        list.add("Админ");
        list.add("Тестовый Тест Тестович");
        return list.equals(possibleResponsibleForTaskCollection.texts());
    }

}
