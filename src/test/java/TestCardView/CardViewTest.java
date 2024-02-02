package TestCardView;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAccordionSettings)
                .clickButton(page.radiobuttonCardView)
                .isDisplayedBlockOpenCards());
    }

    @Test
    @Description("Проверка выбранного табличного вида по умолчанию")
    public void checkDefaultTableView(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAccordionSettings)
                .isSelectedTableView());
    }

    @Test
    @Description("Проверка, что выбор карточного вида сохранился после переоткрытия окна настроек пользовательского вида")
    public void checkSelectedCardView(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAccordionSettings)
                .clickButton(page.radiobuttonCardView)
                .clickButton(page.buttonCloseWindowCustomView)
                .clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAccordionSettings)
                .isSelectedCardView());
    }

    @Test
    @Description("Проверка, что выбран чекбокс 'Раскрывать карточки'")
    public void checkSelectedCheckboxOpenCards(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAccordionSettings)
                .clickButton(page.radiobuttonCardView)
                .clickButton(page.checkboxOpenCards)
                .isSelectedCheckboxOpenCards());
    }

    @Test
    @Description("Проверка сохранения изменений в настройках пользовательского вида")
    public void checkSaveChangesInCustomViewSettings(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAccordionSettings)
                .clickButton(page.radiobuttonCardView)
                .clickButton(page.buttonSaveCustomView)
                .clickButton(page.buttonCloseWindowCustomView)
                .clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAccordionSettings)
                .waitFor(1000)
                .isSelectedCardView());
                page.clickButton(page.radiobuttonTableView)
                    .waitFor(500)
                    .clickButton(page.buttonSaveCustomView);
    }
}
