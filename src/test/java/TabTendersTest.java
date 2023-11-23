import com.codeborne.selenide.Selenide;
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
        tabTendersPage.clickLogInButton();
        tabTendersPage.typeLogin(BASE_LOGIN);
        tabTendersPage.waitFor(200);
        tabTendersPage.typePassword(BASE_PASSWORD);
        tabTendersPage.clickConfirmLogInButton();
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

        tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch);
        tabTendersPage.waitFor(500);
        tabTendersPage.clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion);
        tabTendersPage.clickButton(tabTendersPage.filterRegionRoot);
        tabTendersPage.waitFor(2000);
        tabTendersPage.typeSearch("Москва");
        tabTendersPage.waitFor(2000);
        tabTendersPage.clickButton(tabTendersPage.getCheckboxInFilterRegion(3));
        tabTendersPage.clickButton(tabTendersPage.buttonApply);
        tabTendersPage.clickButton(tabTendersPage.buttonSearch);
        tabTendersPage.waitFor(4000);
        Assert.assertFalse(tabTendersPage.isEqualNumberOfRowResultSearch(1));
    }
}
