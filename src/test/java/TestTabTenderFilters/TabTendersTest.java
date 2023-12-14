package TestTabTenderFilters;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import io.cucumber.java.hu.De;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class TabTendersTest extends BaseTest {

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
    @BeforeEach
    public void beforeMethod(){
        openURL(BASE_URL);
        tabTendersPage.clickLogInButton()
                    .waitFor(500)
                    .typeLogin(BASE_LOGIN)
                    .waitFor(400)
                    .typePassword(BASE_PASSWORD)
                    .clickConfirmLogInButton();
    }

    /**
     * Проверка результатов поиска для автопоиска 'Проверка поиска по реестровому номеру и региону'
     */
    @Test
    @Description("Проверка результатов поиска для автопоиска 'Проверка поиска по реестровому номеру и региону'")
    public void checkRegistryNumber(){

        Assertions.assertEquals(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion)
                .waitFor(4000)
                .getRegistryNumber(), "200741742119000018");
    }

    @Test
    @Description("Проверка увеличения количества результатов поиска при добавлении значения региона")
    public void checkNumberResultSearchAfterAddingRegionValue(){

        Assertions.assertFalse(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
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
    @Description("Проверка названия тендера на включение в него ключевого слова")
    public void checkNameTenderToIncludeKeyword(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .isContainNameTender());
    }

    @Test
    @Description("Проверка исключения из названия тендера ключевого слова")
    public void checkDeletionNameTenderToIncludeKeyword(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .scrollToElement(tabTendersPage.filterNameTender)
                .clickButton(tabTendersPage.filterNameTender)
                .typeDeletion("Мусоровоз")
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainDeletionNameTender());
    }

    @Test
    @Description("Проверка транслитерации при поиске по названию тендера")
    public void checkTransliterationNameTender(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
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

    @Test
    @Description("Проверка поиска по дате публикации тендера")
    public void checkSearchPublicationDateOfTender() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckPublicationDate)
                .waitFor(2000)
                .checkDate("09.01.2021 00:00","09.01.2021 23:59", 1));
    }

    @Test
    @Description("Проверка даты публикации тендера только с начальной датой")
    public void checkPublicationDateOfTenderWithOnlyStartDate() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckPublicationDateWithOnlyStartDate)
                .clickButton(tabTendersPage.buttonCheckPublicationDateWithOnlyStartDate)
                .waitFor(3000)
                .checkDateWithOnlyStartDate("09.01.2021 00:00"));
    }

    @Test
    @Description("Проверка даты публикации тендера только с конечной датой")
    public void checkPublicationDateOfTenderWithOnlyEndDate() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckPublicationDateWithOnlyEndDate)
                .clickButton(tabTendersPage.buttonCheckPublicationDateWithOnlyEndDate)
                .waitFor(2000)
                .checkDateWithOnlyEndDate("10.01.2021 00:00"));
    }

    @Test
    @Description("Проверка поиска по дате начала подачи заявок")
    public void checkDateStartSubmissionOfApplication() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckStartSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("04.01.2021 00:00","04.01.2021 23:59", 2));
    }

    @Test
    @Description("Проверка поиска по дате окончания подачи заявок")
    public void checkDateEndSubmissionOfApplication() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckEndSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("03.01.2021 00:00","03.01.2021 23:59", 2));
    }

    @Test
    @Description("Проверка поиска по дате проведения тендера")
    public void checkDateValidateSearchByTenderDate() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByTenderDate)
                .waitFor(2000)
                .checkDate("12.01.2021 00:00","12.01.2021 23:59", 3));
    }

    @Test
    @Description("Проверка поиска по категории")
    public void checkSearchByCategoryName(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByCategory)
                .waitFor(2000)
                .isContainCategoryName());
    }

    @Test
    @Description("Проверка цены тендера")
    public void checkPriceTender(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
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

    @Test
    @Description("Проверка поиска по типу тендера")
    public void checkSearchByTenderType(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderType)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderType)
                .waitFor(3000)
                .isContainTenderType());
    }

    @Test
    @Description("Проверка поиска по площадке")
    public void checkSearchByTenderStand(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderStand)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderStand)
                .waitFor(2000)
                .isContainTenderStand());
    }

    @Test
    @Description("роверка поиска по модулю 'Государственные тендеры'")
    public void checkSearchOnlyGovernmentTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderModule)
                .waitFor(2000)
                .isContainOnlyGovernmentTenders());
    }

    @Test
    @Description("Проверка поиска по модулям 'Государственные тендеры' и 'Коммерческие тендеры'")
    public void checkSearchOnlyGovernmentAndCommercialTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.filterSearchByTenderModule)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainOnlyGovernmentAndCommercialTenders());
    }

    @Test
    @Description("Проверка поиска по модулям 'Государственные тендеры' и 'Коммерческие тендеры' и 'СНГ'")
    public void checkSearchOnlyGovernmentAndCommercialAndCISTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.filterSearchByTenderModule)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainOnlyGovernmentAndCommercialAndCISTenders());
    }

    @Test
    @Description("Проверка поиска по всем модулям")
    public void checkSearchAllModulesTender(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.filterSearchByTenderModule)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.notSelectedCheckBoxFilter)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainAllModulesTenders());
    }

    @Test
    @Description("Проверка поиска по участнику")
    public void checkSearchParticipant(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByParticipant)
                .clickButton(tabTendersPage.buttonCheckSearchByParticipant)
                .waitFor(2000)
                .isContainParticipant());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - новые тендеры")
    public void checkSearchByNewTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(0))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainNewTenders());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - подготовка заявки")
    public void checkSearchByApplicationPreparation(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(1))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainApplicationPreparation());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - определение победителя")
    public void checkSearchByDeterminationWinner(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(2))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainDeterminationOfWinner());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - заключение контракта")
    public void checkSearchByConclusionContract(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(3))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainConclusionOfContract());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - исполнение контракта")
    public void checkSearchByExecutionContract(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(4))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainExecutionOfContract());
    }

    @Test
    @Description("Проверка поиска по моим тендерам - архив")
    public void checkSearchByArchiveTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.getCheckboxInFilter(5))
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(3000)
                .isContainArchiveTenders());
    }

    @Test
    @Description("Проверка поиска по всем тендерам фильтра 'Мои тендеры'")
    public void checkSearchByAllMineTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.buttonCheckSearchByMineTenders)
                .clickButton(tabTendersPage.filterSearchByMineTendersOrContractsStatus)
                .clickButton(tabTendersPage.checkboxSelectAllMyTenders)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(4000)
                .isContainAllMineTenders());
    }

    @Test
    @Description("Проверка поиска по документации")
    public void checkSearchByTextDocumentation(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByDocumentation)
                .clickButton(tabTendersPage.buttonCheckSearchByDocumentation)
                .waitFor(3000)
                .clickButton(tabTendersPage.cellTableToOpenDocumentation)
                .waitFor(1000)
                .switchToTab(1)
                .isContainSearchDocumentation());
    }

    @Test
    @Description("Проверка поиска по извещению")
    public void checkSearchByTextNotice(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByNotice)
                .clickButton(tabTendersPage.buttonCheckSearchByNotice)
                .waitFor(3000)
                .clickButton(tabTendersPage.cellTableToOpenDocumentationNotice)
                .waitFor(1000)
                .switchToTab(1)
                .isContainSearchWordIntoNoticeDocumentation());
    }

    @Test
    @Description("Проверка поиска в блоке списка фильтров")
    public void checkSearchInListTenders(){

        Assertions.assertTrue(tabTendersPage.typeSearchFilters("Название тендера")
                .isContainFiltersFromSearchField());
    }

    @Test
    @Description("Проверка результата поиска после скрытия фильтра 'Регион'")
    public void checkSearchWithHideFilter(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion)
                .clickButton(tabTendersPage.buttonHideFilter)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainWithoutHideFilter());
    }

    @Test
    @Description("Проверка на отображение ошибки при начальной дате большей конечной")
    public void checkDisplayedErrorMessageWrongEnterDate(){
        Assertions.assertTrue(tabTendersPage.scrollToElement(tabTendersPage.filterDateDeterminationWinner)
                .waitFor(2000)
                .DragAndDropFilter(tabTendersPage.filterDatePublication)
                .waitFor(1000)
                .typeDateFrom("01.01.2021")
                .typeDateTo("31.12.2020")
                .isVisibleErrorInvalidEnterDate());
    }

    @Test
    @Description("Проверка текста ошибки при начальной дате большей конечной")
    public void checkTextErrorMessageWrongEnterDate(){
        Assertions.assertTrue(tabTendersPage.scrollToElement(tabTendersPage.filterDateDeterminationWinner)
                .waitFor(2000)
                .DragAndDropFilter(tabTendersPage.filterDatePublication)
                .waitFor(1000)
                .typeDateFrom("01.01.2021")
                .typeDateTo("31.12.2020")
                .isTextErrorInvalidEnterDate());
    }

    @Test
    @Description("Проверка количества выбранных подкатегорий фильтра 'Категория' с закрытой главной категорией")
    public void checkNumberSelectedCategoriesWithCloseMainCategory(){
        Assertions.assertEquals((tabTendersPage.DragAndDropFilter(tabTendersPage.filterCategory)
                .clickButton(tabTendersPage.checkboxFirstInFilter)
                .clickButton(tabTendersPage.buttonOpenTreeList)
                .getNumberSelectedCategories()), 11);
    }

    @Test
    @Description("Проверка выбранных подкатегорий фильтра 'Категория'")
    public void checkContainSelectedCategory(){
        Assertions.assertTrue(tabTendersPage.DragAndDropFilter(tabTendersPage.filterCategory)
                .clickButton(tabTendersPage.checkboxFirstInFilter)
                .clickButton(tabTendersPage.buttonOpenTreeList)
                .isContainSelectedCategory());
    }

    @Test
    @Description("Проверка исключения из фильтра Заказчик")
    public void checkExcludedFromFilterCustomer(){
        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.excludedElementCustomer)
                .clickButton(tabTendersPage.excludedElementCustomer)
                .isNotIncludeExcludedElement());
    }
}
