package TestMyTenders;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTendersTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitMyTenders";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";

    MyTendersPage page = new MyTendersPage();

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
                .clickButton(page.buttonTabMenuMyTenders);
    }

    @Test
    @Description("Проверка появления окна подтверждения удаления тендера")
    public void checkWindowApproveDeleteTender(){
        assertTrue(page.clickButton(page.buttonDeleteAddedTenderInListTenders)
                .isCheckVisibleWindowApproveDelete());
    }

    @Test
    @Description("Проверка кликабельности кнопки удаления в списке тендеров")
    public void checkClickableButtonDeleteTenderInListTenders(){
        assertTrue(page.waitFor(500)
                .isCheckClickableButtonDeleteTenderInListTenders());
    }

    @Test
    @Description("Проверка кликабельности кнопки выгрузки документации в списке тендеров")
    public void checkClickableButtonLoadDocumentationTenderInListTenders(){
        assertTrue(page.waitFor(500)
                .isCheckClickableButtonLoadDocumentationTenderInListTenders());
    }

    @Test
    @Description("Проверка отключения кнопки 'Удалить' контекстного меню столбца при единственном столбце")
    public void checkNotClickableButtonDeleteContextMenuColumn(){
        assertTrue(page.waitFor(500)
                .clickButton(page.contextMenuColumn)
                .isCheckDisableButtonDeleteContextMenuColumn());
    }

    @Test
    @Description("Проверка отображения карточки тендера")
    public void checkVisibleCard(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .isCheckVisibleCard());
    }

    @Test
    @Description("Проверка кликабельности кнопки смены метки тендера в карточке тендера")
    public void checkClickableButtonChangeTag(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .isCheckClickableButtonChangeTag());
    }

    @Test
    @Description("Проверка кликабельности кнопки ссылки на источник")
    public void checkClickableButtonLinkSource(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .isCheckClickableButtonLinkSource());
    }

    @Test
    @Description("Проверка кликабельности кнопки удаления тендера в карточке тендера")
    public void checkClickableButtonDeleteTenderInCard(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .isCheckClickableButtonDeleteTenderInCard());
    }

    @Test
    @Description("Проверка количества вкладок в карточке тендера")
    public void checkNumberTabInCardTender(){
        assertEquals(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .getNumberTabInCardTender(), 5);
    }

    @Test
    @Description("Проверка названия вкладок в карточке тендера")
    public void checkNameTabCards(){
        assertTrue(page.waitFor(500)
                .clickButton(page.openCardFirstTender)
                .waitFor(500)
                .isCorrectNameTabCards());
    }

    @Test
    @Description("Проверка кликабельности главной вкладки \"Карточки\"")
    public void checkClickableTabCards(){
        assertTrue(page.waitFor(500)
                .isCheckClickableTabCards());
    }

    @Test
    @Description("Проверка кликабельности главной вкладки \"Таблица\"")
    public void checkClickableTabTable(){
        assertTrue(page.waitFor(500)
                .isCheckClickableTabTable());
    }

    @Test
    @Description("Проверка кликабельности главной вкладки \"Календарь\"")
    public void checkClickableTabCalendar(){
        assertTrue(page.waitFor(500)
                .isCheckClickableTabCalendar());
    }

    @Test
    @Description("Проверка ответственного во вкладке \"Таблица\" после его смены во вкладке \"Карточки\"")
    public void checkResponsibleInTabTableAfterChangeResponsibleInTabCards(){
       assertEquals(page.waitFor(500)
               .clickButton(page.openCardFirstTender)
               .waitFor(500)
               .clickButton(page.buttonChangeResponsibleInCardTender)
               .waitFor(500)
               .clickButton(page.userTestInCardTender)
               .waitFor(500)
               .clickButton(page.buttonCloseCardTender)
               .waitFor(500)
               .clickButton(page.tabTable)
               .getResponsibleInTabTable(), "Тестовый Тест Тестович");
    }
}
