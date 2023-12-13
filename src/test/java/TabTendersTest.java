import com.codeborne.selenide.Selenide;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.Before;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    /**
     * Проверка увеличения количества результатов поиска при добавлении значения региона
     */
    @Test
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

    /**
     * Проверка названия тендера на включение в него ключевого слова
     */
    @Test
    public void checkNameTenderToIncludeKeyword(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckTenderNameAndNameDeletion)
                .waitFor(5000)
                .isContainNameTender());
    }

    /**
     * Проверка исключения из названия тендера ключевого слова
     */
    @Test
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

    /**
     * Проверка транслитерации при поиске по названию тендера
     */
    @Test
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

    /**
     * Проверка поиска по дате публикации тендера
     */
    @Test
    public void checkSearchPublicationDateOfTender() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckPublicationDate)
                .waitFor(2000)
                .checkDate("09.01.2021 00:00","09.01.2021 23:59", 1));
    }

    /**
     * Проверка даты публикации тендера только с начальной датой
     */
    @Test
    public void checkPublicationDateOfTenderWithOnlyStartDate() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
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

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
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

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckStartSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("04.01.2021 00:00","04.01.2021 23:59", 2));
    }

    /**
     * Проверка поиска по дате окончания подачи заявок
     */
    @Test
    public void checkDateEndSubmissionOfApplication() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonCheckEndSubmissionOfApplicationDate)
                .waitFor(2000)
                .checkDate("03.01.2021 00:00","03.01.2021 23:59", 2));
    }

    /**
     * Проверка поиска по дате проведения тендера
     */
    @Test
    public void checkDateValidateSearchByTenderDate() throws ParseException {

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByTenderDate)
                .waitFor(2000)
                .checkDate("12.01.2021 00:00","12.01.2021 23:59", 3));
    }

    /**
     * Проверка поиска по категории
     */
    @Test
    public void checkSearchByCategoryName(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonValidateSearchByCategory)
                .waitFor(2000)
                .isContainCategoryName());
    }

    /**
     * Проверка цены тендера
     */
    @Test
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

    /**
     * Проверка поиска по типу тендера
     */
    @Test
    public void checkSearchByTenderType(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderType)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderType)
                .waitFor(3000)
                .isContainTenderType());
    }

    /**
     * Проверка поиска по площадке
     */
    @Test
    public void checkSearchByTenderStand(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderStand)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderStand)
                .waitFor(2000)
                .isContainTenderStand());
    }

    /**
     * Проверка поиска по модулю 'Государственные тендеры'
     */
    @Test
    public void checkSearchOnlyGovernmentTenders(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByTenderModule)
                .clickButton(tabTendersPage.buttonCheckSearchByTenderModule)
                .waitFor(2000)
                .isContainOnlyGovernmentTenders());
    }

    /**
     * Проверка поиска по модулям 'Государственные тендеры' и 'Коммерческие тендеры'
     */
    @Test
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

    /**
     * Проверка поиска по модулям 'Государственные тендеры' и 'Коммерческие тендеры' и 'СНГ'
     */
    @Test
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

    /**
     * Проверка поиска по всем модулям
     */
    @Test
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

    /**
     * Проверка поиска по участнику
     */
    @Test
    public void checkSearchParticipant(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.buttonCheckSearchByParticipant)
                .clickButton(tabTendersPage.buttonCheckSearchByParticipant)
                .waitFor(2000)
                .isContainParticipant());
    }

    /**
     * Проверка поиска по моим тендерам - новые тендеры
     */
    @Test
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

    /**
     * Проверка поиска по моим тендерам - подготовка заявки
     */
    @Test
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

    /**
     * Проверка поиска по моим тендерам - определение победителя
     */
    @Test
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

    /**
     * Проверка поиска по моим тендерам - заключение контракта
     */
    @Test
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

    /**
     * Проверка поиска по моим тендерам - исполнение контракта
     */
    @Test
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

    /**
     * Проверка поиска по моим тендерам - архив
     */
    @Test
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

    /**
     * Проверка поиска по всем тендерам фильтра 'Мои тендеры'
     */
    @Test
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

    /**
     * Проверка поиска по документации
     */
    @Test
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

    /**
     * Проверка поиска по извещению
     */
    @Test
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

    /**
     * Проверка поиска в блоке списка фильтров
     */
    @Test
    public void checkSearchInListTenders(){

        Assertions.assertTrue(tabTendersPage.typeSearchFilters("Название тендера")
                .isContainFiltersFromSearchField());
    }

    /**
     * Проверка результата поиска после скрытия фильтра 'Регион'
     */
    @Test
    public void checkSearchWithHideFilter(){

        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .clickButton(tabTendersPage.buttonAutoSearchRegistryNumberAndRegion)
                .clickButton(tabTendersPage.buttonHideFilter)
                .clickButton(tabTendersPage.buttonSearch)
                .waitFor(2000)
                .isContainWithoutHideFilter());
    }

    /**
     * Проверка на отображение ошибки при начальной дате большей конечной
     */
    @Test
    public void checkDisplayedErrorMessageWrongEnterDate(){
        Assertions.assertTrue(tabTendersPage.scrollToElement(tabTendersPage.filterDateDeterminationWinner)
                .waitFor(2000)
                .DragAndDropFilter(tabTendersPage.filterDatePublication)
                .waitFor(1000)
                .typeDateFrom("01.01.2021")
                .typeDateTo("31.12.2020")
                .isVisibleErrorInvalidEnterDate());
    }

    /**
     * Проверка текста ошибки при начальной дате большей конечной
     */
    @Test
    public void checkTextErrorMessageWrongEnterDate(){
        Assertions.assertTrue(tabTendersPage.scrollToElement(tabTendersPage.filterDateDeterminationWinner)
                .waitFor(2000)
                .DragAndDropFilter(tabTendersPage.filterDatePublication)
                .waitFor(1000)
                .typeDateFrom("01.01.2021")
                .typeDateTo("31.12.2020")
                .isTextErrorInvalidEnterDate());
    }

    /**
     * Проверка количества выбранных подкатегорий фильтра 'Категория' с закрытой главной категорией
     */
    @Test
    public void checkNumberSelectedCategoriesWithCloseMainCategory(){
        Assertions.assertEquals((tabTendersPage.DragAndDropFilter(tabTendersPage.filterCategory)
                .clickButton(tabTendersPage.checkboxFirstInFilter)
                .clickButton(tabTendersPage.buttonOpenTreeList)
                .getNumberSelectedCategories()), 11);
    }

    /**
     * Проверка выбранных подкатегорий фильтра 'Категория'
     */
    @Test
    public void checkContainSelectedCategory(){
        Assertions.assertTrue(tabTendersPage.DragAndDropFilter(tabTendersPage.filterCategory)
                .clickButton(tabTendersPage.checkboxFirstInFilter)
                .clickButton(tabTendersPage.buttonOpenTreeList)
                .isContainSelectedCategory());
    }

    /**
     * Проверка исключения из фильтра Заказчик
     */
    @Test
    public void checkExcludedFromFilterCustomer(){
        Assertions.assertTrue(tabTendersPage.clickButton(tabTendersPage.tabListAutoSearch)
                .scrollToElement(tabTendersPage.excludedElementCustomer)
                .clickButton(tabTendersPage.excludedElementCustomer)
                .isNotIncludeExcludedElement());
    }
}
