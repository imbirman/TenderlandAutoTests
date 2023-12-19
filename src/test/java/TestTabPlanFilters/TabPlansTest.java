package TestTabPlanFilters;

import Base.BaseTest;
import static org.junit.jupiter.api.Assertions.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabPlansTest  extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitPlan";
    private final static String BASE_PASSWORD = "Hyqpmaz0/";
    TabPlansPage page = new TabPlansPage();

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
    @Description("Проверка результатов поиска планов по названию")
    public void checkSearchByNamePlan(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByNamePlan)
                .waitFor(3000)
                .isContainNamePlan());
    }


    @Test
    @Description("Проверка поиска по типу плана")
    public void checkSearchByTypePlan(){

        assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByTypePlan)
                .waitFor(5000)
                .scrollToElement(page.buttonDeleteAutoSearch)
                .clickButton(page.filterSearchByTypePlan)
                .clickButton(page.getCheckboxInFilter(0))
                .clickButton(page.buttonSearch)
                .isContainTypePlan());
    }
}
