package TestTabContractFilters;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TabContractsTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitContract";
    private final static String BASE_PASSWORD = "Hyqpmaz0";
    TabContractsPage page = new TabContractsPage();

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
    @Description("Проверка результата поиска контракта по продуктам")
    public void checkSearchContractByProduct(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonCheckSearchByProduct)
                .waitFor(4500)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .waitFor(2000)
                .switchToTab(1)
                .clickButton(page.tabListProductsInCardContract)
                .isContainCardContractSearchWord());
    }

    @Test
    @Description("Проверка поиска по цене контракта")
    public void checkPriceContract(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByPrice)
                .waitFor(2500)
                .checkPrice(10000,100000));
    }

    @Test
    @Description("Проверка поиска по статусу контракта 'Исполнение'")
    public void checkSearchByBeingExecuted(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByStatusContracts)
                .clickButton(page.filterSearchContractsStatus)
                .clickButton(page.getCheckboxInFilter(0))
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .isContainBeingExecuted());
    }

    @Test
    @Description("Проверка поиска по статусу контракта 'Исполнение прекращено'")
    public void checkSearchByExecutionTerminated(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByStatusContracts)
                .clickButton(page.filterSearchContractsStatus)
                .clickButton(page.getCheckboxInFilter(1))
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .isContainExecutionTerminated());
    }

    @Test
    @Description("Проверка поиска по статусу контракта 'Исполнение завершено'")
    public void checkSearchByExecutionCompleted(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByStatusContracts)
                .clickButton(page.filterSearchContractsStatus)
                .clickButton(page.getCheckboxInFilter(2))
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .isContainExecutionCompleted());
    }

    @Test
    @Description("Проверка даты публикации контракта")
    public void checkPublicationDateOfContract() throws ParseException {

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckPublicationDate)
                .waitFor(2000)
                .clickButton(page.filterPublicationDate)
                .clickButton(page.buttonClearFieldDateFrom)
                .typeDateFrom("01.01.2021")
                .clickButton(page.buttonSearch)
                .waitFor(2000)
                .checkDate("01.01.2021 00:00","09.01.2021 23:59"));
    }

    @Test
    @Description("Проверка даты начала исполнения контракта")
    public void checkContractExecutionStartDate() throws ParseException {

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonValidateSearchByContractExecutionStartDate)
                .waitFor(4000)
                .checkDate("13.01.2021 00:00","14.01.2021 23:59"));
    }

    @Test
    @Description("Проверка даты окончания исполнения контракта")
    public void checkContractExecutionEndDate() throws ParseException {

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonValidateSearchByContractExecutionEndDate)
                .waitFor(4000)
                .checkDate("13.01.2021 00:00","14.01.2021 23:59"));
    }

    @Test
    @Description("Проверка фактической даты исполнения контракта")
    public void checkContractActualExecutionDate() throws ParseException {

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonValidateSearchByContractActualExecutionDate)
                .waitFor(4000)
                .checkDate("13.01.2021 00:00","14.01.2021 23:59"));
    }

    @Test
    @Description("Проверка даты подписания контракта")
    public void checkContractDateOfSigning() throws ParseException {

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonValidateSearchByContractDateOfSigning)
                .waitFor(4000)
                .checkDate("13.01.2021 00:00","14.01.2021 23:59"));
    }

    @Test
    @Description("Проверка поиска по штрафу 'Просрочка исполнения поставщиком'")
    public void checkSearchByDelayInPerformanceBySupplier(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByMulct)
                .clickButton(page.buttonCheckSearchByMulct)
                .waitFor(5000)
                .scrollToElement(page.filterSearchByMulct)
                .clickButton(page.filterSearchByMulct)
                .waitFor(1000)
                .clickButton(page.getCheckboxInFilter(0))
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .switchToTab(1)
                .waitFor(1000)
                .clickButton(page.tabMulctContracts)
                .isContainCardContractSearchByDelayInPerformanceBySupplier());
    }

    @Test
    @Description("Проверка поиска по штрафу 'Просрочка исполнения заказчиком обязательств'")
    public void checkSearchByDelayInFulfillmentOfObligationsByCustomer(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByMulct)
                .clickButton(page.buttonCheckSearchByMulct)
                .waitFor(5000)
                .scrollToElement(page.filterSearchByMulct)
                .clickButton(page.filterSearchByMulct)
                .waitFor(1000)
                .clickButton(page.getCheckboxInFilter(1))
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .waitFor(1000)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isContainCardContractSearchByDelayInFulfillmentOfObligationsByCustomer());
    }

    @Test
    @Description("Проверка поиска по штрафу 'Ненадлежащее исполнение поставщиком'")
    public void checkSearchByInadequateExecutionBySupplier(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByMulct)
                .clickButton(page.buttonCheckSearchByMulct)
                .waitFor(5000)
                .scrollToElement(page.filterSearchByMulct)
                .clickButton(page.filterSearchByMulct)
                .waitFor(1000)
                .clickButton(page.getCheckboxInFilter(2))
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .waitFor(1000)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isContainCardContractSearchByInadequateExecutionBySupplier());
    }

    @Test
    @Description("Проверка поиска по штрафу 'Ненадлежащее исполнение заказчиком обязательств'")
    public void checkSearchByInadequateExecutionByCustomer(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByMulct)
                .clickButton(page.buttonCheckSearchByMulct)
                .waitFor(5000)
                .scrollToElement(page.filterSearchByMulct)
                .clickButton(page.filterSearchByMulct)
                .waitFor(1000)
                .clickButton(page.getCheckboxInFilter(3))
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .waitFor(1000)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isContainCardContractSearchByInadequateExecutionByCustomer());
    }

    @Test
    @Description("Проверка суммы штрафов контракта")
    public void checkSumMulctContract(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchBySumMulct)
                .clickButton(page.buttonCheckSearchBySumMulct)
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isSumMulct(10000,100000));
    }

    @Test
    @Description("Проверка наличия неоплаченных штрафов контракта")
    public void checkUnpaidMulctContract(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByUnpaidMulct)
                .clickButton(page.buttonCheckSearchByUnpaidMulct)
                .waitFor(4000)
                .scrollToElement(page.buttonDeleteAutoSearch)
                .clickButton(page.filterSearchByUnpaidMulct)
                .waitFor(1000)
                .clickButton(page.radiobuttonYesUnpaidMulct)
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isUnpaidMulct());
    }

    @Test
    @Description("Проверка отсутствия неоплаченных штрафов контракта")
    public void checkAbsenceUnpaidMulctContract(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .scrollToElement(page.buttonCheckSearchByUnpaidMulct)
                .clickButton(page.buttonCheckSearchByUnpaidMulct)
                .waitFor(5000)
                .scrollToElement(page.buttonDeleteAutoSearch)
                .clickButton(page.filterSearchByUnpaidMulct)
                .waitFor(1000)
                .clickButton(page.radiobuttonNoUnpaidMulct)
                .clickButton(page.buttonSearch)
                .waitFor(4000)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .switchToTab(1)
                .clickButton(page.tabMulctContracts)
                .isPaidMulct());
    }
}
