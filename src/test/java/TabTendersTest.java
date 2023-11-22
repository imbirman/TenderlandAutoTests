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

    @Before
    public void beforeMethod(){
        openURL(BASE_URL);
        tabTendersPage.clickLogInButton();
        tabTendersPage.typeLogin(BASE_LOGIN);
        tabTendersPage.waitFor(200);
        tabTendersPage.typePassword(BASE_PASSWORD);
        tabTendersPage.clickConfirmLogInButton();
    }

    @Test
    public void checkRegistryNumber(){
        tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch);
        tabTendersPage.clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion);
        tabTendersPage.waitFor(2000);
        Assert.assertEquals(java.util.Optional.ofNullable(tabTendersPage.getNumberOfRowResultSearch()),1);
        Assert.assertEquals(tabTendersPage.getRegistryNumber(), "200741742119000018");
    }
}
