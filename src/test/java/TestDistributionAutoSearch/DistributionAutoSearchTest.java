package TestDistributionAutoSearch;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistributionAutoSearchTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitAutoSearch";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    DistributionAutoSearchPage page = new DistributionAutoSearchPage();

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
                .waitFor(400)
                .typePassword(BASE_PASSWORD)
                .clickConfirmLogInButton();
    }

    @Test
    @Description("Проверка отсутствия даты включения рассылки автопоиска")
    public void checkDisabledDateDistribution(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .getLabelDistribution(), "Включить рассылку");
    }

    @Test
    @Description("Проверка отображения надписи включения рассылки автопоиска")
    public void checkVisibleLabelEnableDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .isLabelEnableDistribution());
    }

    @Test
    @Description("Проверка даты включения рассылки")
    public void checkDateEnableDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .isContainCorrectDateCreateDistribution());
    }

    @Test
    @Description("Проверка отображения ошибки при сохранении включенной рассылки без выбранного аккаунта")
    public void checkDisplayedErrorMessageSaveDistributionWithoutSelectedAccount(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .clickButton(page.buttonSaveSettingsDistribution)
                .isVisibleErrorMessageWithoutSelectedAccount());
    }

    @Test
    @Description("Проверка текста ошибки при сохранении включенной рассылки без выбранного аккаунта")
    public void checkErrorMessageSaveDistributionWithoutSelectedAccount(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .clickButton(page.buttonSaveSettingsDistribution)
                .getErrorMessageWithoutSelectedAccount(),"Выберите хотя бы один аккаунт для рассылки");
    }

    @Test
    @Description("Проверка кликабельности вкладки 'Параметры' в настройках рассылки")
    public void checkClickableTabSettingsDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .isClickableTabSettingsDistribution());
    }

    @Test
    @Description("Проверка кликабельности вкладки 'Поля' в настройках рассылки")
    public void checkClickableTabFieldsDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .isClickableTabFieldsDistribution());
    }

    @Test
    @Description("Проверка списка выбранных полей в настройках рассылки по умолчанию")
    public void checkDefaultListFieldsSelected(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.tabFieldsDistribution)
                .isCorrectListSelectedFields());
    }

    @Test
    @Description("Проверка типа рассылки по умолчанию")
    public void checkDefaultTypeDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .isDefaultTypeDistribution());
    }

    @Test
    @Description("Проверка кликабельности кнопки удаления интервала по умолчанию")
    public void checkDefaultInteractableButtonDeleteInterval(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .isInteractableButtonDeleteInterval());
    }

    @Test
    @Description("Проверка кликабельности кнопки удаления интервала после его добавления")
    public void checkInteractableButtonDeleteIntervalAfterAddedInterval(){
        assertFalse(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonAddInterval)
                .waitFor(500)
                .isInteractableButtonDeleteInterval());
    }
}
