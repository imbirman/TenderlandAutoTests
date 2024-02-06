package TestAuditor;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;

import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class AuditorTest extends BaseTest {
    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitAuditor";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";
    AuditorPage page = new AuditorPage();

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
                .clickConfirmLogInButton()
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonTabMenuAuditor);
    }

    @Test
    @Description("Проверка результатов поиска по реквизитам организации")
    public void checkResultSearchByOrganizationDetails(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("234703774440")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .isContainOrganizationDetail());
    }

    @Test
    @Description("Проверка результатов поиска по учредителям")
    public void checkResultSearchByFounders(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByFounders)
                .waitFor(500)
                .typeSearchInclude("иванов")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .clickButton(page.buttonOpenListFounders)
                .waitFor(1000)
                .isContainNameFounders());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'Действующая'")
    public void checkResultSearchByCurrentLegalStatus(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxCurrentLegalStatus)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .isContainCurrentLegalData());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'Недействующая'")
    public void checkResultSearchByInactiveLegalStatus(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInactiveLegalStatus)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInactiveLegalData());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе ликвидации'")
    public void checkResultSearchByInTheProcessOfLiquidationStatus(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInTheProcessOfLiquidationStatus)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInTheProcessOfLiquidation());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе банкротства'")
    public void checkResultSearchByInBankruptcyProcessStatus(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInBankruptcyProcessStatus)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInBankruptcyProcess());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе реорганизации'")
    public void checkResultSearchByInTheProcessOfReorganizationStatus(){
        assertTrue(page.dragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInTheProcessOfReorganizationStatus)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInTheProcessOfReorganization());
    }

    @Test
    @Description("Проверка результатов поиска по дате регистрации")
    public void checkResultSearchByDateRegistration() throws ParseException {
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchDateRegistration)
                .waitFor(3000)
                .isCorrectDate("01.01.2022 00:00","08.01.2022 23:59"));
    }

    @Test
    @Description("Проверка результатов поиска по дате закрытия")
    public void checkResultSearchByDateClosing() throws ParseException {
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchDateClosing)
                .waitFor(3000)
                .isCorrectDate("01.01.2022 00:00","08.01.2022 23:59"));
    }

    @Test
    @Description("Проверка, что организация никогда не была в РНП")
    public void checkOrganizationNeverBeenInRNP(){
        assertTrue(page.scrollToElement(page.filterSearchByStatusOfBeingInRNP)
                .dragAndDropFilter(page.filterSearchByStatusOfBeingInRNP)
                .waitFor(500)
                .clickButton(page.radioButtonNeverBeenInRNP)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .scrollToElement(page.parameterTotalEntriesInRegistry)
                .isNeverBeenInRNP());
    }

    @Test
    @Description("Проверка, что организация была раньше в РНП")
    public void checkOrganizationFormerlyBeenInRNP(){
        assertTrue(page.scrollToElement(page.filterSearchByStatusOfBeingInRNP)
                .dragAndDropFilter(page.filterSearchByStatusOfBeingInRNP)
                .waitFor(500)
                .clickButton(page.radioButtonFormerlyInRNP)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .scrollToElement(page.parameterTotalEntriesInRegistry)
                .isFormerlyBeenInRNP());
    }

    @Test
    @Description("Проверка, что организация находится в РНП")
    public void checkOrganizationLocatedInRNP(){
        assertTrue(page.scrollToElement(page.filterSearchByStatusOfBeingInRNP)
                .dragAndDropFilter(page.filterSearchByStatusOfBeingInRNP)
                .waitFor(500)
                .clickButton(page.radioButtonLocatedInRNP)
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .clickButton(page.ninthCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .scrollToElement(page.parameterTotalEntriesInRegistry)
                .waitFor(5000)
                .isLocatedInRNP());
    }

    @Test
    @Description("Проверка количества элементов контекстного меню")
    public void checkCorrectNumberElementsContextMenu(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("234703774440")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .clickButton(page.contextMenu)
                .isCorrectNumberElementsContextMenu());
    }

    @Test
    @Description("Проверка названия элементов контекстного меню")
    public void checkCorrectNameElementsContextMenu(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("1047796171631")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .clickButton(page.contextMenu)
                .isCorrectNameElementsContextMenu());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка учредителей")
    public void checkClickableButtonOpenListFounders(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("7017311832")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .isClickableButtonOpenListFounders());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка тендеров")
    public void checkClickableButtonOpenListAllTenders(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListAllTenders)
                .isClickableButtonOpenListAllTenders());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка контрактов")
    public void checkClickableButtonOpenListContracts(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListContracts)
                .isClickableButtonOpenListContracts());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка жалоб ФАС")
    public void checkClickableButtonOpenListFASClaim(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListFASClaim)
                .isClickableButtonOpenListFASClaim());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка исполнительных производств")
    public void checkClickableButtonOpenListEnforcementProceedings(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListEnforcementProceedings)
                .isClickableButtonOpenListEnforcementProceedings());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка исполнительных производств")
    public void checkClickableButtonOpenListArbitrationCases(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListArbitrationCases)
                .isClickableButtonOpenListArbitrationCases());
    }

    @Test
    @Description("Проверка кликабельности кнопки открытия списка исполнительных производств по аффилированным лицам")
    public void checkClickableButtonOpenListArbitrationCasesOnAffiliates(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2901142220")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListArbitrationCasesOnAffiliates)
                .isClickableButtonOpenListArbitrationCasesOnAffiliates());
    }

    @Test
    @Description("Проверка названия окна списка учредителей")
    public void checkNameWindowFounders(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2801102311")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.buttonOpenListFounders)
                .getNameWindowBlock(page.nameWindowBlockFounders), "Учредители");
    }

    @Test
    @Description("Проверка списка учредителей")
    public void checkListFounders(){
        assertTrue(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("2801102311")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.buttonOpenListFounders)
                .isCorrectNameFounders());
    }

    @Test
    @Description("Проверка названия окна списка тендеров")
    public void checkNameWindowTenders(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("227712808600")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockTendersInfo)
                .clickButton(page.buttonOpenListAllTenders)
                .getNameWindowWitchOrganizationParticipated(), "Тендеры");
    }

    @Test
    @Description("Проверка названия окна списка жалоб ФАС")
    public void checkNameWindowFASClaim(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("5044116555")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .waitFor(500)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockArbitrationInfo)
                .clickButton(page.buttonOpenListFASClaim)
                .getNameWindowBlock(page.nameWindowInBlock), "Жалобы ФАС");
    }

    @Test
    @Description("Проверка названия окна списка исполнительных производств")
    public void checkNameWindowEnforcementProceeding(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("7017311832")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockArbitrationInfo)
                .clickButton(page.buttonOpenListEnforcementProceedings)
                .getNameWindowBlock(page.nameWindowInBlock), "Исполнительные производства");
    }

    @Test
    @Description("Проверка названия окна списка контрактов")
    public void checkNameWindowContracts(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("5044116555")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockArbitrationInfo)
                .clickButton(page.buttonOpenListContracts)
                .getNameWindowBlock(page.nameWindowInBlock), "Контракты");
    }

    @Test
    @Description("Проверка названия окна списка арбитражных дел")
    public void checkNameWindowArbitrationCases(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("5044116555")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockArbitrationInfo)
                .clickButton(page.buttonOpenListArbitrationCases)
                .getNameWindowBlock(page.nameWindowInBlock), "Суды");
    }

    @Test
    @Description("Проверка названия окна списка дел по аффилированным лицам")
    public void checkNameWindowArbitrationCasesOnAffiliates(){
        assertEquals(page.dragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("5044116555")
                .clickButton(page.openTabMenu)
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .waitFor(500)
                .clickButton(page.headerBlockArbitrationInfo)
                .clickButton(page.buttonOpenListArbitrationCasesOnAffiliates)
                .getNameWindowBlock(page.nameWindowInBlock), "Суды по аффилированным лицам");
    }

}
