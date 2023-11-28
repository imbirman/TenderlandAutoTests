import com.codeborne.selenide.Selenide;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

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
        Assert.assertEquals(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(2000)
                .getRegistryNumber(), "200741742119000018");
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

    /**
     * Проверка названия тендера на включение в него ключевого слова
     */
    @Test
    public void checkNameTenderToIncludeKeyword(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .isContainNameTender());
    }

    /**
     * Проверка исключения из названия тендера ключевого слова
     */
    @Test
    public void checkDeletionNameTenderToIncludeKeyword(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .scrollToElement(tabTendersPage.filterNameTender)
                .clickButton(tabTendersPage.filterNameTender)
                .typeDeletion("Мусоровоз")
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainDeletionNameTender());
    }

    /**
     * Проверка транслитерации при поиске по названию тендера
     */
    @Test
    public void checkTransliterationNameTender(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .scrollToElement(tabTendersPage.filterNameTender)
                .clickButton(tabTendersPage.filterNameTender)
                .clearField(tabTendersPage.fieldNameTender)
                .typeNameTender("Муcор")
                .clickButton(tabTendersPage.checkBoxTransliteration)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainNameTender());
    }

    /**
     * Проверка поиска по дате публикации тендера
     */
    @Test
    public void checkSearchPublicationDateOfTender() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckPublicationDate)
                .waitFor(2000)
                .checkDate("09.01.2021 00:00","09.01.2021 23:59", 1));
    }

    /**
     * Проверка даты публикации тендера только с начальной датой
     */
    @Test
    public void checkPublicationDateOfTenderWithOnlyStartDate() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckPublicationDateWithOnlyStartDate)
                .clickButton(tabTendersPage.buttonCheckPublicationDateWithOnlyStartDate)
                .waitFor(3000)
                .checkDateWithOnlyStartDate("09.01.2021 00:00"));
    }

    /**
     * Проверка даты публикации тендера только с конечной датой
     */
    @Test
    public void checkPublicationDateOfTenderWithOnlyEndDate() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckPublicationDateWithOnlyEndDate)
                .clickButton(tabTendersPage.buttonCheckPublicationDateWithOnlyEndDate)
                .waitFor(2000)
                .checkDateWithOnlyEndDate("10.01.2021 00:00"));
    }

    /**
     * Проверка поиска по дате начала подачи заявок
     */
    @Test
    public void checkDateStartSubmissionOfApplication() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckStartSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("04.01.2021 00:00","04.01.2021 23:59", 2));
    }

    /**
     * Проверка поиска по дате окончания подачи заявок
     */
    @Test
    public void checkDateEndSubmissionOfApplication() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckEndSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("03.01.2021 00:00","03.01.2021 23:59", 2));
    }

    /**
     * Проверка поиска по дате проведения тендера
     */
    @Test
    public void checkDateValidateSearchByTenderDate() throws ParseException {

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByTenderDate)
                .waitFor(2000)
                .checkDate("12.01.2021 00:00","12.01.2021 23:59", 3));
    }

    /**
     * Проверка поиска по категории
     */
    @Test
    public void checkSearchByCategoryName(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByCategory)
                .waitFor(2000)
                .isContainCategoryName());
    }

    /**
     * Проверка цены тендера
     */
    @Test
    public void checkPriceTender(){

        Assert.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckSearchByPrice)
                .waitFor(3000)
                .scrollToElement(tabTendersPage.filterValidateSearchByTenderPrice)
                .clickButton(tabTendersPage.filterValidateSearchByTenderPrice)
                .clearField(tabTendersPage.fieldPriceFrom)
                .typePriceFrom("10000")
                .clearField(tabTendersPage.fieldPriceTo)
                .typePriceTo("100000")
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(4000)
                .checkPrice(10000,100000));
    }
}
