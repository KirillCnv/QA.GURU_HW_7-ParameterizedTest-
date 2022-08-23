package guru.qa.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ExistTestPage {

    // elements
    public SelenideElement textInput = $("#pcode"),
                            priceList = $("#priceBody");


    // action
    public ExistTestPage openPage(){
        open("https://www.exist.ru/");

        return this;
    }

    public ExistTestPage openPageCatalog(){
        open("https://www.exist.ru/Catalog/");

        return this;
    }

    public ExistTestPage setTextInput(String value){
        textInput.setValue(value).pressEnter();

        return this;
    }

    public ExistTestPage checkResult(String value){
        priceList.shouldHave(text(value));

        return this;
    }

    public ExistTestPage findElement(String value){
        $$(".page-blocks").find(Condition.text(value)).click();
        return this;
    }
    public ExistTestPage examinationResult(List<String> elements){
        $$(".workpageheader").shouldHave(CollectionCondition.texts(elements));
        return this;
    }
}


