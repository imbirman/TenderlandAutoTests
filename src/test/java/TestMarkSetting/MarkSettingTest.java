package TestMarkSetting;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MarkSettingTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    MarkSettingPage page = new MarkSettingPage();

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
    @Description("Проверка метки тендера")
    public void checkMarkOfTender(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(500)
                .clickButton(page.contextMenuResultSearch)
                .waitFor(500)
                .clickButton(page.markContextMenu)
                .waitFor(500)
                .clickButton(page.redMarkContextMenu)
                .waitFor(500)
                .isMarkOfTender());
    }

    @Test
    @Description("Проверка метки тендера в карточном виде")
    public void checkMarkOfTenderCardView(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(500)
                .clickButton(page.buttonCardView)
                .clickButton(page.buttonSetMarkCardView)
                .clickButton(page.redMarkContextMenu)
                .isMarkOfTenderCardView());

    }

    @Test
    @Description("Проверка удаления метки тендера")
    public void checkDeletionMarkOfTender(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(500)
                .clickButton(page.contextMenuResultSearch)
                .clickButton(page.markContextMenu)
                .clickButton(page.redMarkContextMenu)
                .clickButton(page.contextMenuResultSearch)
                .moveToElement(page.markContextMenu)
                .clickButton(page.buttonDeleteMark)
                .isDeletionMarkOfTender());
    }

    @Test
    @Description("Проверка установки метки для всех выбранных тендеров")
    public void checkSetMarkAllSelectedTenders(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(500)
                .clickButton(page.buttonSelectAllTenders)
                .clickButton(page.buttonContextMenuForSelectedTenders)
                .moveToElement(page.markContextMenu)
                .clickButton(page.redMarkContextMenu)
                .waitFor(500)
                .isMarkAllSelectedTender());
    }

    @Test
    @Description("Проверка установки метки для всех выбранных тендеров карточного вида")
    public void checkSetMarkAllSelectedTendersCardView(){
        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckTenderNameAndNameDeletion)
                .waitFor(500)
                .clickButton(page.buttonCardView)
                .clickButton(page.buttonSelectAllTenders)
                .clickButton(page.buttonContextMenuForSelectedTenders)
                .moveToElement(page.markContextMenu)
                .clickButton(page.redMarkContextMenu)
                .waitFor(500)
                .isMarkAllSelectedTenderCardView());
    }
}
