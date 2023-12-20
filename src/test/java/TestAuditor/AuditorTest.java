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
                .clickButton(page.buttonSearch)
                .waitFor(500)
                .clickButton(page.firstCellTableInResultSearch)
                .switchToTab(1)
                .scrollToElement(page.buttonOpenListContracts)
                .isClickableButtonOpenListContracts());
    }

}
