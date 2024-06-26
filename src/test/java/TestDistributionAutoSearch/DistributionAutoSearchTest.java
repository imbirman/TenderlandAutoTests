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

    @Test
    @Description("Проверка отображения подписи времени и периода рассылки")
    public void checkDisplayedSettingsViewDistribution(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .isDisplayedSettingsViewDistribution());
    }

    @Test
    @Description("Проверка текста подписи времени и периода рассылки по умолчанию")
    public void checkDefaultTextSettingsViewDistribution(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .getTextLabelSettingsViewDistribution(),"Рассылка придет раз в 1 час с 9:00 до 12:00");
    }

    @Test
    @Description("Проверка текста подписи времени и периода рассылки после добавления интервала")
    public void checkTextSettingsViewDistributionAfterAddedInterval(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonAddInterval)
                .waitFor(500)
                .getTextLabelSettingsViewDistribution(),"Рассылка придет раз в 1 час с 9:00 до 12:00, с 13:00 до 15:00");
    }

    @Test
    @Description("Проверка названия вкладки по умолчанию для настроек типа рассылки")
    public void checkDefaultTitleTabTypeDistribution(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .getTitleTabTypeDistribution(), "Поля");
    }

    @Test
    @Description("Проверка названия вкладки для настроек типа рассылки после выбора типа рассылки 'отчет'")
    public void checkTitleTabTypeDistributionAfterSelectedTypeDistributionReport(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.radiobuttonTypeDistributionReport)
                .waitFor(500)
                .getTitleTabTypeDistribution(), "Отчёты");
    }

    @Test
    @Description("Проверка подписи вкладки настроек рассылки после выбора отчета")
    public void checkLabelTabTypeDistribution(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.radiobuttonTypeDistributionReport)
                .waitFor(500)
                .clickButton(page.tabFieldsDistribution)
                .waitFor(500)
                .clickButton(page.firstReport)
                .waitFor(500)
                .getLabelTabTypeDistribution(), "Выбран отчёт - Тестовый отчет");
    }

    @Test
    @Description("Проверка ошибки при сохранении рассылки без полей")
    public void checkErrorSaveDistributionWithoutSelectedFields(){
        assertEquals(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.buttonEnableDistribution)
                .waitFor(500)
                .clickButton(page.fieldAccountForDistribution)
                .clickButton(page.accountForDistribution)
                .clickButton(page.tabFieldsDistribution)
                .waitFor(500)
                .clickButton(page.buttonDeleteAllFieldsForDistribution)
                .waitFor(500)
                .clickButton(page.buttonSaveSettingsDistribution)
                .waitFor(500)
                .getLabelTabTypeDistribution(), "Выберите хотя бы одно поле!");
    }

    @Test
    @Description("Проверка корректности списка периодов рассылки")
    public void checkCorrectFrequencyDistributionList(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.testAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenWindowDistribution)
                .waitFor(500)
                .clickButton(page.frequencyDistribution)
                .waitFor(500)
                .isCorrectFrequencyDistributionList());
    }
}
