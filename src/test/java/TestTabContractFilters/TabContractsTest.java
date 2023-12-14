package TestTabContractFilters;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabContractsTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitContract";
    private final static String BASE_PASSWORD = "Hyqpmaz0";
    TabContractsPage page = new TabContractsPage();
    /**
     * Открытие сайта для входа
     */
    private void openURL(String url){
        Selenide.open(url);
    }

    /**
     * Ввод логина/пароля и вход на сайт
     */
    @BeforeEach
    public void beforeMethod(){
        openURL(BASE_URL);
        page.clickLogInButton()
                .waitFor(500)
                .typeLogin(BASE_LOGIN)
                .waitFor(400)
                .typePassword(BASE_PASSWORD)
                .clickConfirmLogInButton();
    }

    /**
     * Проверка результата поиска контракта по продуктам
     */
    @Test
    public void checkSearchContractByProduct(){

        Assertions.assertTrue(page.clickButton(page.tabListAutoSearch)
                .waitFor(500)
                .clickButton(page.buttonCheckSearchByProduct)
                .waitFor(4500)
                .clickButton(page.tableCellToCheckForSwitchToNextTab)
                .waitFor(2000)
                .switchToTab(1)
                .clickButton(page.tabListProductsInCardContract)
                .isContainCardContractSearchWord());
    }

    /**
     * Проверка поиска по цене контракта
     */
    @Test
    public void checkPriceContract(){

        Assertions.assertTrue(page.clickButton(page.tabListAutoSearch)
                .clickButton(page.buttonCheckSearchByPrice)
                .waitFor(2000)
                .clickButton(page.filterValidateSearchByTenderPrice)
                .clearField(page.fieldPriceFrom)
                .typePriceFrom("10000")
                .clearField(page.fieldPriceTo)
                .typePriceTo("50000")
                .clickButton(page.buttonSearch)
                .waitFor(4500)
                .checkPrice(10000,50000));
    }
}
