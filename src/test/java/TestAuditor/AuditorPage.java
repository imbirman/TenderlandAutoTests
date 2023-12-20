package TestAuditor;

import TestTabPlanFilters.TabPlansPage;
import TestTabTenderFilters.TabTendersPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import javax.annotation.Nonnull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class AuditorPage {

    private final SelenideElement logInButton = $x("//div[@id='login-button']"); /** Кнопка входа в систему */
    private final SelenideElement loginField = $x("//input[@type='text']"); /** Поле для ввода логина */
    private final SelenideElement passwordField = $x("//input[@type='password']"); /** Поле для ввода пароля */
    private final SelenideElement confirmLogInButton = $x("//div[@id='landing-popup-login-button']"); /* Кнопка "Войти в систему" */
    /** Кнопка "Искать" */
    protected SelenideElement buttonSearch = $x("//div[@id='search-filters-search-button']");

    /** Поле дерева фильтров */
    private final SelenideElement filterRoot = $x("//div[@class='dx-sortable tl-filter-content tl-filter-drop-area']");
    /** Поле поиска "Включаем в поиск" */
    private final SelenideElement fieldSearchInclude = $x("//textarea[@class='dx-texteditor-input dx-texteditor-input-auto-resize']");
    /** Статус организации */
    private final SelenideElement organizationStatus = $x("(//span[@class='header-info-block-value'])[1]");
    /** Пометка находится ли организация в настоящий момент в РНП */
    private final SelenideElement parameterLocatedInRNP = $x("//div[contains(text(), 'РНП')]//div[1]");


    /** В разделе РНП поле "Всего записей в реестре" */
    protected SelenideElement parameterTotalEntriesInRegistry = $x("//div[text()='Всего записей в реестре']/following::div[1]");

    /** Список ИНН в результатах поиска */
    private final ElementsCollection tableCellINN = $$x("(//div[@class='dx-datagrid-content']//tbody[@role='presentation']/tr/td[5])[1]");
    /** Список ФИО учредителей */
    private final ElementsCollection nameFounders = $$x("//div[@class='entity-organization-person']//div[@class='main-bold-text'][1]");
    /** Ячейка в результате поиска "Дата" */
    private final ElementsCollection tableCellDate = $$x("//div[@class='dx-datagrid-content']//tbody[@role='presentation']//td[10]");
    /** Элемент контекстного меню */
    private final ElementsCollection elementContextMenuCollection = $$x("//div[@class='dx-submenu']//div[@class='dx-item dx-menu-item']/div/div");

    /** Вкладка "Автопоиски" */
    protected SelenideElement tabListAutoSearch = $x("//div[@class='search-filters-tab list-autosearches']");
    /** Кнопка автопоиска "Проверка поиска по дате регистрации" */
    protected SelenideElement buttonAutoSearchDateRegistration = $x("//div[text()='Проверка поиска по дате регистрации']");
    /** Кнопка автопоиска "Проверка поиска по дате закрытия" */
    protected SelenideElement buttonAutoSearchDateClosing = $x("//div[text()='Проверка поиска по дате закрытия']");

    /** Кнопка открытия бокового меню */
    protected SelenideElement openTabMenu = $x("//i[@class='material-icons-round icon-28px icon-grey md-menu icon-grey-hover common-header-icon']");
    /** Кнопка открытия ревизора */
    protected SelenideElement buttonTabMenuAuditor = $x("//div[text()='Ревизор']");
    /** Кнопка для открытия списка учредителей */
    protected SelenideElement buttonOpenListFounders = $x("//div[@id='entity-persons-popover']");

    /** Фильтр "Реквизиты организации" в блоке фильтров */
    protected SelenideElement filterOrganizationDetails = $x("//span[text()='Реквизиты организации']");
    /** Фильтр "Поиск по учредителям" в блоке фильтров */
    protected SelenideElement filterSearchByFounders = $x("//span[text()='Поиск по учредителям']");
    /** Фильтр "Юридический статус" в блоке фильтров */
    protected SelenideElement filterSearchByLegalStatus = $x("//span[text()='Юридический статус']");
    /** Фильтр "Статус нахождения в РНП" в блоке фильтров */
    protected SelenideElement filterSearchByStatusOfBeingInRNP = $x("//span[text()='Статус нахождения в РНП']");

    /** Строка таблицы результатов поиска для открытия карточки организации */
    protected SelenideElement ninthCellTableInResultSearch = $x("(//div[@class='dx-datagrid-content']//tbody[@role='presentation']/tr)[9]");
    /** Пункт "Действующая" фильтра "Юридический статус" */
    protected SelenideElement checkboxCurrentLegalStatus = $x("(//div[@class='dx-widget dx-checkbox dx-list-select-checkbox']/div)[5]");
    /** Пункт "Недействующая" фильтра "Юридический статус" */
    protected SelenideElement checkboxInactiveLegalStatus = $x("(//div[@class='dx-widget dx-checkbox dx-list-select-checkbox']/div)[1]");
    /** Пункт "В процессе ликвидации" фильтра "Юридический статус" */
    protected SelenideElement checkboxInTheProcessOfLiquidationStatus = $x("(//div[@class='dx-widget dx-checkbox dx-list-select-checkbox']/div)[2]");
    /** Пункт "В процессе банкротства" фильтра "Юридический статус" */
    protected SelenideElement checkboxInBankruptcyProcessStatus = $x("(//div[@class='dx-widget dx-checkbox dx-list-select-checkbox']/div)[3]");
    /** Пункт "В процессе реорганизации" фильтра "Юридический статус" */
    protected SelenideElement checkboxInTheProcessOfReorganizationStatus = $x("(//div[@class='dx-widget dx-checkbox dx-list-select-checkbox']/div)[4]");
    /** Пункт "Никогда не находился в РНП" */
    protected SelenideElement radioButtonNeverBeenInRNP = $x("(//div[@class='dx-radiobutton-icon'])[1]");
    /** Ранее находился в РНП */
    protected SelenideElement radioButtonFormerlyInRNP = $x("(//div[@class='dx-radiobutton-icon'])[2]");
    /** Находится в РНП */
    protected SelenideElement radioButtonLocatedInRNP = $x("(//div[@class='dx-radiobutton-icon'])[3]");
    /** Контекстное меню в таблице результатов поиска */
    protected SelenideElement contextMenu = $x("//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@class='dx-link dx-icon-overflow dx-link-icon']");





    @Step("Ожидание {number}")
    public AuditorPage waitFor(long number){
        sleep(number);
        return new AuditorPage();
    }

    @Step("Переключиться на вкладку под номером {numberTab}")
    public AuditorPage switchToTab(int numberTab){
        switchTo().window(numberTab);
        return new AuditorPage();
    }

    @Step("Перетаскиваем фильтр в поле построения")
    public AuditorPage dragAndDropFilter(@Nonnull SelenideElement element){
        actions().clickAndHold(element).moveToElement(filterRoot).release().build().perform();
        return new AuditorPage();
    }

    @Step("Прокрутить до элемента")
    public AuditorPage scrollToElement(SelenideElement element){
        element.scrollIntoView(false);
        return new AuditorPage();
    }

    @Step("Ввести логин для авторизации")
    public AuditorPage typeLogin(String login){loginField.sendKeys(login); return new AuditorPage();}

    @Step("Ввести пароль для авторизации")
    public AuditorPage typePassword(String password){
        passwordField.sendKeys(password);
        return new AuditorPage();
    }

    @Step("Нажать кнопку для открытия окна авторизации")
    public AuditorPage clickLogInButton(){
        logInButton.click();
        return new AuditorPage();
    }

    @Step("Кликнуть на кнопку \"Войти в систему\"")
    public AuditorPage clickConfirmLogInButton(){
        confirmLogInButton.click();
        return new AuditorPage();
    }

    @Step("Нажать кнопку{button}")
    public AuditorPage clickButton(SelenideElement button){
        button.click();
        return new AuditorPage();
    }

    public AuditorPage typeSearchInclude(String search){
        fieldSearchInclude.sendKeys(search);
        return new AuditorPage();
    }

    @Step("Проверка, что ИНН организации соответствует поисковому запросу")
    public boolean isContainOrganizationDetail(){
        List<String> checkOrganizationDetail = tableCellINN.texts();
        checkOrganizationDetail.remove(checkOrganizationDetail.size()-1);
        boolean checkIsContainOrganizationDetail = true;
        for(String type : checkOrganizationDetail){
            if(!type.equals("234703774440")){
                checkIsContainOrganizationDetail = false;
                break;
            }
        }
        return checkIsContainOrganizationDetail;
    }

    @Step("Проверка, что наименование учредителя включает ключевое слово")
    public boolean isContainNameFounders(){
        List<String> checkNameFounders = nameFounders.texts();
        boolean checkIsContainNameFounders = false;
        for(String type : checkNameFounders){
            if(type.contains("иванов") || type.contains("Иванов") || type.contains("ИВАНОВ")){
                checkIsContainNameFounders = true;
                break;
            }
        }
        return checkIsContainNameFounders;
    }

    @Step("Проверка, что организация действующая")
    public boolean isContainCurrentLegalData(){
        return organizationStatus.getText().equals("Действующая");
    }

    @Step("Проверка, что организация недействующая")
    public boolean isContainInactiveLegalData(){
        return organizationStatus.getText().equals("Недействующая");
    }

    @Step("Проверка, что организация в процессе ликвидации")
    public boolean isContainInTheProcessOfLiquidation(){
        return organizationStatus.getText().equals("В процессе ликвидации");
    }

    @Step("Проверка, что организация в процессе банкротства")
    public boolean isContainInBankruptcyProcess(){
        return organizationStatus.getText().equals("В процессе банкротства");
    }

    @Step("Проверка, что организация в процессе реорганизации")
    public boolean isContainInTheProcessOfReorganization(){
        return organizationStatus.getText().equals("В процессе реорганизации");
    }

    @Step("Проверка, что дата публикации находится в заданном диапазоне")
    public boolean isCorrectDate(String startDate, String endDate) throws ParseException {
        boolean check = true;
        List<String> dateForCheck = tableCellDate.texts();
        dateForCheck.remove(dateForCheck.size()-1);
        for(String date : dateForCheck) {
            date = date.replace(" (МСК+0)", "");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date currentDate = dateFormat.parse(date);
            Date leftDate = dateFormat.parse(startDate);
            Date rightDate = dateFormat.parse(endDate);
            if(!(currentDate.getTime() >= leftDate.getTime() && currentDate.getTime() <= rightDate.getTime())){
                check = false;
                break;
            }
        }
        return check;
    }

    @Step("Проверка, что организация никогда не была в РНП")
    public boolean isNeverBeenInRNP(){
        int checkTotalEntriesInRegistry;
        String parameterToCheck = parameterTotalEntriesInRegistry.getText();
        if(parameterToCheck.equals("-")){ checkTotalEntriesInRegistry = 0;}else{
            checkTotalEntriesInRegistry = Integer.parseInt(parameterTotalEntriesInRegistry.getText());}
        return checkTotalEntriesInRegistry == 0 && parameterLocatedInRNP.getText().equals("Не находится в реестре недобросовестных поставщиков");
    }

    @Step("Проверка, что организация была раньше в РНП")
    public boolean isFormerlyBeenInRNP(){
        int checkTotalEntriesInRegistry;
        String parameterToCheck = parameterTotalEntriesInRegistry.getText();
        if(parameterToCheck.equals("-")){ checkTotalEntriesInRegistry = 0;}else{
            checkTotalEntriesInRegistry = Integer.parseInt(parameterToCheck);}
        return checkTotalEntriesInRegistry != 0 && parameterLocatedInRNP.getText().equals("Не находится в реестре недобросовестных поставщиков");
    }

    @Step("Проверка, что организация находится в РНП")
    public boolean isLocatedInRNP(){
        int checkTotalEntriesInRegistry;
        checkTotalEntriesInRegistry = Integer.parseInt(parameterTotalEntriesInRegistry.getText());
        return checkTotalEntriesInRegistry != 0 && parameterLocatedInRNP.getText().equals("Находится в реестре недобросовестных поставщиков");
    }

    @Step("Проверка количества элементов контекстного меню")
    public boolean isCorrectNumberElementsContextMenu(){
        return elementContextMenuCollection.size() >= 2;
    }
}
