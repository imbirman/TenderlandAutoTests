package TestAuditor;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;

import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(page.DragAndDropFilter(page.filterOrganizationDetails)
                .waitFor(500)
                .typeSearchInclude("234703774440")
                .clickButton(page.buttonSearch)
                .waitFor(1000)
                .isContainOrganizationDetail());
    }

    @Test
    @Description("Проверка результатов поиска по учредителям")
    public void checkResultSearchByFounders(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByFounders)
                .waitFor(500)
                .typeSearchInclude("иванов")
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .clickButton(page.buttonOpenListFounders)
                .waitFor(1000)
                .isContainNameFounders());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'Действующая'")
    public void checkResultSearchByCurrentLegalStatus(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxCurrentLegalStatus)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .isContainCurrentLegalData());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'Недействующая'")
    public void checkResultSearchByInactiveLegalStatus(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInactiveLegalStatus)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInactiveLegalData());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе ликвидации'")
    public void checkResultSearchByInTheProcessOfLiquidationStatus(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInTheProcessOfLiquidationStatus)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInTheProcessOfLiquidation());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе банкротства'")
    public void checkResultSearchByInBankruptcyProcessStatus(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInBankruptcyProcessStatus)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInBankruptcyProcess());
    }

    @Test
    @Description("Проверка результатов поиска по юридическому статусу 'В процессе реорганизации'")
    public void checkResultSearchByInTheProcessOfReorganizationStatus(){
        assertTrue(page.DragAndDropFilter(page.filterSearchByLegalStatus)
                .waitFor(500)
                .clickButton(page.checkboxInTheProcessOfReorganizationStatus)
                .clickButton(page.buttonSearch)
                .waitFor(3000)
                .clickButton(page.NinthCellTableInResultSearch)
                .switchToTab(1)
                .isContainInTheProcessOfReorganization());
    }

}
