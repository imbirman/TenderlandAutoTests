package TestFilters;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FiltersTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    FiltersPage page = new FiltersPage();

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
    @Description("Проверка соответствия найденного значения по поиску в фильтре ОКПД")
    public void checkResultSearchFilterOKPD(){
        assertEquals(page.dragAndDropFilter(page.filterOKPD)
                .waitFor(500)
                .typeSearch("(85.11.10.000) Услуги в области дошкольного образования")
                .getResultSearchByFilter(), "(85.11.10.000) Услуги в области дошкольного образования");
    }

    @Test
    @Description("Проверка отсутствия в окне фильтра выбранного значения при очистке поля поиска")
    public void checkSwitchShowOnlySelectedOKPDNo(){
        assertTrue(page.dragAndDropFilter(page.filterOKPD)
                .waitFor(500)
                .typeSearch("(85.11.10.000) Услуги в области дошкольного образования")
                .clickButton(page.checkboxOKPD)
                .clearField(page.fieldSearchInFilter)
                .waitFor(500)
                .isNotContainKeyWordByOKPDNo());
    }

    @Test
    @Description("Проверка выбранного значения в окне фильтра ОКПД при очистке поля поиска и выборе 'Показывать только выбранное'")
    public void checkSwitchShowOnlySelectedOKPDYes(){
        assertTrue(page.dragAndDropFilter(page.filterOKPD)
                .waitFor(500)
                .typeSearch("(85.11.10.000) Услуги в области дошкольного образования")
                .clickButton(page.checkboxOKPD)
                .clearField(page.fieldSearchInFilter)
                .waitFor(500)
                .clickButton(page.checkboxShowOnlySelected)
                .waitFor(500)
                .isNotContainKeyWordByOKPDYes());
    }

    @Test
    @Description("Проверка сброса выделения найденного элемента")
    public void checkResetSelectFilterOKPD(){
        assertFalse(page.dragAndDropFilter(page.filterOKPD)
                .waitFor(500)
                .typeSearch("(85.11.10.000) Услуги в области дошкольного образования")
                .clickButton(page.checkboxOKPD)
                .waitFor(500)
                .clickButton(page.buttonReset)
                .waitFor(500)
                .typeSearch("(85.11.10.000) Услуги в области дошкольного образования")
                .waitFor(500)
                .isResetResultSearchByFilterOKPD());
    }

    @Test
    @Description("Проверка соответствия найденного значения по поиску в фильтре Категория")
    public void checkResultSearchFilterCategory(){
        assertEquals(page.dragAndDropFilter(page.filterCategory)
                .waitFor(500)
                .typeSearch("Коммунальные услуги")
                .getResultSearchByFilter(), "Коммунальные услуги");
    }

    @Test
    @Description("Проверка чекбокса \"Без категории\"")
    public void checkResultSearchFilterWithoutCategory(){
        assertTrue(page.dragAndDropFilter(page.filterCategory)
                .waitFor(500)
                .isDisabledCheckboxEmptyCategory());
    }

    @Test
    @Description("Проверка ввода некорректной цены от")
    public void checkIncorrectPriceFrom(){
        assertEquals(page.dragAndDropFilter(page.filterPrice)
                .waitFor(500)
                .typePriceFrom("-dsddвава+* ")
                .waitFor(500)
                .clickButton(page.filterPrice)
                .waitFor(500)
                .getTextFilterPrice(), "0 ₽ — ...");
    }

    @Test
    @Description("Проверка ввода некорректной цены до")
    public void checkIncorrectPriceTo(){
        assertEquals(page.dragAndDropFilter(page.filterPrice)
                .waitFor(500)
                .typePriceTo("-dsddвава+* ")
                .waitFor(500)
                .clickButton(page.filterPrice)
                .waitFor(500)
                .getTextFilterPrice(), "... — 0 ₽");
    }
}
