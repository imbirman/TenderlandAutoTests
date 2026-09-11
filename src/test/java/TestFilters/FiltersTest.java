package TestFilters;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FiltersTest extends BaseTest {

    private final static String BASE_URL = "https://tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "tester";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

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
                .shouldBeVisible(page.nameFilter)
                .typePriceFrom("dsddвава+* ")
                .clickButton(page.filterPrice)
                .getTextFilterPrice(), "Установить значение");
    }

    @Test
    @Description("Проверка ввода некорректной цены до")
    public void checkIncorrectPriceTo(){
        assertEquals(page.dragAndDropFilter(page.filterPrice)
                .shouldBeVisible(page.nameFilter)
                .typePriceTo("dsddвава+* ")
                .clickButton(page.filterPrice)
                .getTextFilterPrice(), "Установить значение");
    }

    @Test
    @Description("Проверка результата поиска с нулевой ценой")
    public void checkResultSearchWithZeroPrice(){
        assertTrue(page.dragAndDropFilter(page.filterPrice)
                .shouldBeVisible(page.nameFilter)
                .typePriceFrom("1000")
                .typePriceTo("10000")
                .clickButton(page.checkboxShowWithoutNMCK)
                .clickButton(page.filterPrice)
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .isContainZeroPrice());
    }

    @Test
    @Description("Проверка результата поиска по реквизитам внутри фильтра 'Заказчик'")
    public void checkSearchInsideFilterCustomer(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .typeSearchInsideFilterCustomerByDetails("305780214002814")
                .waitFor(1000)
                .isCheckSearchInsideFilterCustomerByDetails());

    }

    @Test
    @Description("Проверка результата поиска по тексту внутри фильтра 'Заказчик'")
    public void checkTextSearchInsideFilterCustomer(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .clickButton(page.tabTextSearchInFilterCustomer)
                .waitFor(500)
                .typeSearchInsideFilterCustomerTextSearch("ЗАКУПАЙ")
                .waitFor(500)
                .isCheckSearchByTextInsideFilterCustomer());
    }

    @Test
    @Description("Проверка поиска по адресу регистрации внутри фильтра \"Заказчик\"")
    public void checkExcludeFromSearchInsideFilterCustomer(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .typeSearchInsideFilterCustomerByRegistrationAddress("ОРЕНБУРГ")
                .waitFor(1000)
                .isCheckSearchInsideFilterCustomerByRegistrationAddress());
    }

    @Test
    @Description("Проверка выделения чекбокса 'Выбрать всё' при переключении страницы в фильтре 'Заказчик'")
    public void checkSelectedButtonAllSelect(){
        assertFalse(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .clickButton(page.checkboxSelectAll)
                .waitFor(500)
                .clickButton(page.secondPage)
                .waitFor(1000)
                .isNotSelectedButtonAllSelect());
    }

    @Test
    @Description("Проверка результата поиска по организации внутри фильтра 'Заказчик'")
    public void checkSearchRegionInsideFilterCustomer(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .typeSearchInsideFilterCustomerByNameOrganization("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ КРАСНОГИР МАРИНА ВАСИЛЬЕВНА")
                .waitFor(1500)
                .isContainKeyWordByRegionSearchInsideFilterCustomer());
    }

    @Test
    @Description("Проверка списка элементов в фильтре 'Модуль'")
    public void checkListElementsOfFilterModule(){
        assertTrue(page.dragAndDropFilter(page.filterModule)
                .shouldBeVisible(page.nameFilter)
                .isContainTypesModule());
    }

    @Test
    @Description("Проверка сброса чекбоксов \"Исключено из поиска\"")
    public void checkResetUnSelectedCheckboxElements(){
        assertTrue(page.dragAndDropFilter(page.filterStand)
                .shouldBeVisible(page.nameFilter)
                .clickButton(page.checkboxSelectedAllElements)
                .clickButton(page.checkboxSelectedAllElements)
                .clickButton(page.buttonReset)
                .waitFor(500)
                .isCheckResetUnSelectedCheckboxElements());
    }

    @Test
    @Description("Проверка выделения чекбоксов при просмотре только выбранных элементов")
    public void checkSelectedCheckboxShowOnlySelectedElements(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .shouldBeVisible(page.nameFilter)
                .typeSearchInsideFilterCustomerByDetails("305780214002814")
                .waitFor(1000)
                .clickButton(page.checkboxSelectedAllElements)
                .waitFor(500)
                .clickButton(page.checkboxShowOnlySelected)
                .waitFor(500)
                .isCheckSelectedCheckboxShowOnlySelectedElements());
    }

    @Test
    @Description("Проверка отображения названия чекбокса \"Показывать без региона\"")
    public void checkVisibleNameCheckboxShowWithoutRegion(){
        assertTrue(page.dragAndDropFilter(page.filterRegion)
                .waitFor(1000)
                .isCheckVisibleNameCheckboxShowWithoutRegion());
    }

    @Test
    @Description("Проверка отображения чекбокса \"Показывать без региона\"")
    public void checkVisibleCheckboxShowWithoutRegion(){
        assertTrue(page.dragAndDropFilter(page.filterRegion)
                .waitFor(1000)
                .isCheckVisibleCheckboxShowWithoutRegion());
    }
}
