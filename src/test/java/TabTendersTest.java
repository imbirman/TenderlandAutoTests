import com.codeborne.selenide.Selenide;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TabTendersTest extends BaseTest{

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";
    TabTendersPage tabTendersPage = new TabTendersPage();
    /**
     * Открытие сайта для входа
     */
    private void openURL(String url){
        Selenide.open(url);
    }

    /**
     * Ввод логина/пароля и вход на сайт
     */
    @Before
    public void beforeMethod(){
        openURL(BASE_URL);
        tabTendersPage.clickLogInButton()
                    .typeLogin(BASE_LOGIN)
                    .waitFor(200)
                    .typePassword(BASE_PASSWORD)
                    .clickConfirmLogInButton();
    }

    /**
     * Проверка результатов поиска для автопоиска 'Проверка поиска по реестровому номеру и региону'
     */
    @Test
    public void checkRegistryNumber(){
        tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch);
        tabTendersPage.clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion);
        tabTendersPage.waitFor(2000);
        Assert.assertEquals(tabTendersPage.getNumberOfRowResultSearch(),1);
        Assert.assertEquals(tabTendersPage.getRegistryNumber(), "200741742119000018");
    }

    /**
     * Проверка увеличения количества результатов поиска при добавлении значения региона
     */
    @Test
    public void checkNumberResultSearchAfterAddingRegionValue(){

        Assert.assertFalse(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                                        .waitFor(500)
                                        .clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion)
                                        .clickButton(tabTendersPage.filterRegionRoot)
                                        .waitFor(2000)
                                        .typeSearch("Москва")
                                        .waitFor(2000)
                                        .clickButton(tabTendersPage.getCheckboxInFilterRegion(3))
                                        .clickButton(tabTendersPage.buttonApply)
                                        .clickButton(tabTendersPage.buttonSearch)
                                        .waitFor(4000)
                                        .isEqualNumberOfRowResultSearch(1));
    }

    @Test
    @Title("Проверка названия тендера на включение в него ключевого слова")
    public void checkNameTenderToIncludeKeyword(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(2000)
                .isContainNameTender());
    }
}
