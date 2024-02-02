package TestCardView;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardViewTest  extends BaseTest {
    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    CardViewPage page = new CardViewPage();

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
    @Description("Проверка отображения блока для раскрытия карточки в настройках пользовательского вида")
    public void checkDisplayedBlockOpenCards(){
        page.clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAccordionSettings)
                .clickButton(page.radiobuttonCardView)
                .isDisplayedBlockOpenCards();
    }
}
