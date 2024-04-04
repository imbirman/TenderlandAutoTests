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

    @Test
    @Description("Проверка результата поиска с нулевой ценой")
    public void checkResultSearchWithZeroPrice(){
        assertTrue(page.dragAndDropFilter(page.filterPrice)
                .waitFor(500)
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
                .waitFor(500)
                .typeSearchInsideFilterCustomerByDetails("305780214002814")
                .waitFor(1000)
                .isCheckSearchInsideFilterCustomerByDetails());

    }

    @Test
    @Description("Проверка результата поиска по тексту внутри фильтра 'Заказчик'")
    public void checkTextSearchInsideFilterCustomer(){
        assertTrue(page.dragAndDropFilter(page.filterCustomer)
                .waitFor(500)
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
                .waitFor(500)
                .typeSearchInsideFilterCustomerByRegistrationAddress("ОРЕНБУРГ")
                .waitFor(1000)
                .isCheckSearchInsideFilterCustomerByRegistrationAddress());
    }

    @Test
    @Description("Проверка выделения чекбокса 'Выбрать всё' при переключении страницы в фильтре 'Заказчик'")
    public void checkSelectedButtonAllSelect(){
        assertFalse(page.dragAndDropFilter(page.filterCustomer)
                .waitFor(500)
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
                .waitFor(500)
                .typeSearchInsideFilterCustomerByNameOrganization("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ КРАСНОГИР МАРИНА ВАСИЛЬЕВНА")
                .waitFor(1500)
                .isContainKeyWordByRegionSearchInsideFilterCustomer());
    }

    @Test
    @Description("Проверка списка элементов комбобокса 'Направление' в фильтре 'Дата публикации'")
    public void checkListElementsComboboxDirection(){
        assertTrue(page.dragAndDropFilter(page.filterDatePublication)
                .waitFor(1000)
                .clickButton(page.tabRangeInFilterDatePublication)
                .waitFor(500)
                .clickButton(page.comboboxDirection)
                .waitFor(500)
                .isContainTypesDirection());
    }

    @Test
    @Description("Проверка списка элементов комбобокса 'Период' в фильтре 'Дата публикации'")
    public void checkListElementsComboboxPeriod(){
        assertTrue(page.dragAndDropFilter(page.filterDatePublication)
                .waitFor(1000)
                .clickButton(page.tabRangeInFilterDatePublication)
                .waitFor(500)
                .clickButton(page.comboboxPeriod)
                .waitFor(500)
                .isContainTypesPeriod());
    }

    @Test
    @Description("Проверка списка элементов в фильтре 'Модуль'")
    public void checkListElementsOfFilterModule(){
        assertTrue(page.dragAndDropFilter(page.filterModule)
                .waitFor(1000)
                .isContainTypesModule());
    }

    @Test
    @Description("Проверка сброса чекбоксов \"Исключено из поиска\"")
    public void checkResetUnSelectedCheckboxElements(){
        assertTrue(page.dragAndDropFilter(page.filterStand)
                .waitFor(1000)
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
                .waitFor(1000)
                .typeSearchInsideFilterCustomerByDetails("305780214002814")
                .waitFor(500)
                .clickButton(page.checkboxSelectedAllElements)
                .waitFor(500)
                .clickButton(page.buttonApply)
                .clickButton(page.filterInTree)
                .clickButton(page.checkboxShowOnlySelected)
                .waitFor(500)
                .isCheckSelectedCheckboxShowOnlySelectedElements());
    }
}
