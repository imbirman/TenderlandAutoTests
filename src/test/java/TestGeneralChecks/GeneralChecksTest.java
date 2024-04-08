package TestGeneralChecks;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeneralChecksTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    GeneralChecksPage page = new GeneralChecksPage();

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
    @Description("Проверка соответствия области подсказки фильтру И")
    public void checkCorrectHintAreaAnd(){
        assertTrue(page.isCorrectHintAreaAnd());
    }

    @Test
    @Description("Проверка соответствия области подсказки фильтру ИЛИ")
    public void checkCorrectHintAreaOr(){
        assertTrue(page.clickButton(page.filterAndOr)
                .isCorrectHintAreaOr());
    }

    @Test
    @Description("Проверка контекстного меню на второй странице после выбора всех элементов на первой странице в результатах поиска")
    public void checkNameElementsContextMenu(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckTenderNameAndNameDeletion)
                .waitFor(500)
                .clickButton(page.checkBoxSelectedAllForTableResultSearch)
                .waitFor(500)
                .clickButton(page.secondPageSearch)
                .waitFor(500)
                .clickButton(page.buttonContextMenuResultSearch)
                .isNameElementsContextMenu());
    }

    @Test
    @Description("Проверка отображения в результатах поиска скрытого элемента")
    public void checkContainHideTender(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckHideResultSearch)
                .clickButton(page.buttonCheckHideResultSearch)
                .waitFor(500)
                .clickButton(page.buttonOpenShowHideEntities)
                .waitFor(500)
                .clickButton(page.buttonSwitchShowHideEntities)
                .waitFor(500)
                .isContainHideTender());

    }

    @Test
    @Description("Проверка не отображения в результатах поиска скрытого элемента")
    public void checkNotContainHideTender(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckHideResultSearch)
                .waitFor(1000)
                .isNotContainHideTender());
    }

    @Test
    @Description("Проверка чекбокса 'Выбрать всё' для результатов поиска")
    public void checkNotSelectedCheckBoxSelectedAllForResultSearch(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckTenderNameAndNameDeletion)
                .waitFor(500)
                .clickButton(page.checkBoxSelectedAllForTableResultSearch)
                .waitFor(500)
                .scrollToElement(page.buttonDeleteAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonClearBuildingFieldSearch)
                .waitFor(500)
                .isNotSelectedCheckBoxSelectedAllForResultSearch());
    }

    @Test
    @Description("Проверка отображения счетчика выбранных тендеров в результате поиска")
    public void checkDisplayedSelectionCounter(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckHideResultSearch)
                .clickButton(page.buttonCheckHideResultSearch)
                .waitFor(1000)
                .clickButton(page.checkBoxSelectedAllForTableResultSearch)
                .isDisplayedSelectionCounter());
    }
}
