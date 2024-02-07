package TestCustomView;

import Base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import static org.junit.jupiter.api.Assertions.*;

import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomViewTest extends BaseTest {

    private final static String BASE_URL = "https://test.v2.tenderland.ru/Home/Landing";
    private final static String BASE_LOGIN = "AdminTestitTender";
    private final static String BASE_PASSWORD = "Hyqpmaz0";

    CustomViewPage page = new CustomViewPage();

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
    @Description("Проверка количества элементов для выбора в блоке 'Поля таблицы' на вкладке 'Тендеры' по умолчанию")
    public void checkNumberElementsTableFieldsInTabTenderDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .getNumberElementsTableFieldsForSelection(), 11);
    }

    @Test
    @Description("Проверка количества выбранных элементов в блоке 'Поля таблицы' на вкладке 'Тендеры' по умолчанию")
    public void checkNumberSelectedElementsTableFieldsInTabTenderDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .getNumberElementsTableFieldsSelected(), 13);
    }

    @Test
    @Description("Проверка количества элементов для выбора в блоке 'Поля таблицы' на вкладке 'Контракты' по умолчанию")
    public void checkNumberElementsTableFieldsForSelectionInTabContractsDefault(){
       assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonTabContracts)
                .getNumberElementsTableFieldsForSelection(), 13);
    }

    @Test
    @Description("Проверка количества элементов для выбора в блоке 'Поля таблицы' на вкладке 'Планы' по умолчанию")
    public void checkNumberElementsTableFieldsForSelectionInTabPlansDefault(){
       assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonTabPlans)
                .getNumberElementsTableFieldsForSelection(), 15);
    }

    @Test
    @Description("Проверка количества выбранных элементов в блоке 'Детализация' по умолчанию")
    public void checkNumberElementsDetailingFieldsDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonAccordionDetailing)
                .getNumberElementsDetailingFieldsForSelection(), 1);
    }

    @Test
    @Description("Проверка количества элементов для выбора в блоке 'Детализация' по умолчанию")
    public void checkNumberSelectedElementsDetailingFieldsDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonAccordionDetailing)
                .getNumberElementsDetailingFieldsSelected(), 4);
    }

    @Test
    @Description("Проверка значения поля 'Выбрать поле для сортировки' в блоке 'Настройки' по умолчанию")
    public void checkValueDefaultSortField(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonAccordionSettings)
                .getValueDefaultSortField(), "Дата публикации");
    }

    @Test
    @Description("Проверка значения сортировки 'По убыванию' в блоке 'Настройки' по умолчанию")
    public void checkSelectedRadiobuttonDescendingDefault(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonAccordionSettings)
                .checkSelectedRadiobuttonDescending());
    }

    @Test
    @Description("Проверка значения чекбокса 'Раскрывать детализации' в блоке 'Настройки' по умолчанию")
    public void checkCheckboxDiscloseDetailingDefault(){
        assertTrue(page.clickButton(page.buttonOpenWindowCustomView)
                .clickButton(page.buttonAddNewCustomView)
                .clickButton(page.buttonAccordionSettings)
                .checkCheckboxDiscloseDetailing());
    }

    @Test
    @Description("Проверка отображения количества выбранных полей по умолчанию")
    public void checkValueLabelSelectedFieldsDefault(){
        assertEquals(page.clickButton(page.buttonOpenWindowCustomView)
                .waitFor(500)
                .clickButton(page.buttonAddNewCustomView)
                .getValueLabelSelectedFields(),"Выбрано полей - 12");
    }
}
