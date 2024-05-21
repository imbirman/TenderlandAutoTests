package TestMyTendersTask;

import Base.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTendersTaskTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitMyTenders";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    MyTendersTaskPage page = new MyTendersTaskPage();

    @Description("Открытие сайта для входа")
    private void openURL(String url){
        Selenide.open(url);
    }

    @BeforeEach
    @Description("Ввод логина/пароля и вход на сайт")
    public void beforeMethod(){
        openURL(BASE_URL);
        page.clickLogInButton()
                .waitFor(500)
                .typeLogin(BASE_LOGIN)
                .waitFor(500)
                .typePassword(BASE_PASSWORD)
                .waitFor(2000)
                .clickConfirmLogInButton()
                .waitFor(3000)
                .clickButton(page.openTabMenu.shouldBe(Condition.interactable))
                .waitFor(500)
                .clickButton(page.buttonTabMenuMyTenders);
    }

    @Test
    @Description("Проверка добавления новой задачи")
    public void checkAddingTask(){
            assertEquals(page.waitFor(2000)
                    .clickButton(page.openCardFirstTender)
                    .waitFor(1000)
                    .clickButton(page.buttonAddTask)
                    .waitFor(500)
                    .typeNameTask("тестовая задача")
                    .waitFor(500)
                    .clickButton(page.openDeadlineWindow)
                    .waitFor(500)
                    .clickButton(page.buttonSaveDeadline)
                    .waitFor(500)
                    .clickButton(page.buttonSaveTask)
                    .waitFor(500)
                    .getNameLastTask(), "тестовая задача");
    }

    @Test
    @Description("Проверка добавления задачи с дублирующим названием")
    public void checkAddingTaskWithDuplicateName(){
            assertNotEquals(page.waitFor(1000)
                    .clickButton(page.openCardFirstTender)
                    .waitFor(1000)
                    .clickButton(page.buttonAddTask)
                    .waitFor(500)
                    .typeNameTask("тестовая задача 1")
                    .waitFor(500)
                    .clickButton(page.openDeadlineWindow)
                    .waitFor(500)
                    .clickButton(page.buttonSaveDeadline)
                    .waitFor(500)
                    .clickButton(page.buttonSaveTask)
                    .waitFor(500)
                    .getNameLastTask(), "тестовая задача 1");
    }

    @Test
    @Description("Проверка удаления задачи")
    public void checkDeleteTask(){
            assertTrue(page.waitFor(1000)
                    .clickButton(page.openCardFirstTender)
                    .waitFor(1000)
                    .clickButton(page.getButtonDeleteTaskByNumber(3))
                    .waitFor(500)
                    .clickButton(page.buttonConfirmationDeleteTask)
                    .waitFor(500)
                    .isCheckDeleteTask());
    }

    @Test
    @Description("Проверка статуса добавленной задачи")
    public void checkStatusAddedTask(){
        assertTrue(page.waitFor(1000)
                .clickButton(page.openCardFirstTender)
                .waitFor(1000)
                .clickButton(page.buttonAddTask)
                .waitFor(500)
                .typeNameTask("тест статуса")
                .waitFor(500)
                .clickButton(page.openDeadlineWindow)
                .waitFor(500)
                .clickButton(page.buttonSaveDeadline)
                .waitFor(500)
                .clickButton(page.buttonSaveTask)
                .waitFor(500)
                .isCheckStatusAddedTask());
    }

    @Test
    @Description("Проверка статуса задачи после нажатия переключателя \"Выполнено\"")
    public void checkStatusTaskAfterClickRadiobuttonComplete(){
        assertTrue(page.waitFor(1000)
                .clickButton(page.openCardFirstTender)
                .waitFor(2000)
                .clickButton(page.radiobuttonCompleteTask)
                .waitFor(500)
                .clickButton(page.groupCompleteTasks)
                .waitFor(500)
                .isCheckStatusTaskComplete());
    }

    @Test
    @Description("Проверка количества возможных статусов задачи")
    public void checkNumberOfPossibleTaskStatus(){
        assertEquals(page.waitFor(1000)
                .clickButton(page.openCardFirstTender)
                .waitFor(1000)
                .clickButton(page.statusWorkingTask)
                .waitFor(500)
                .getNumberPossibleStatusesTask(), 3);
    }

}
